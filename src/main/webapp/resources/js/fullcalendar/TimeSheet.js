
/**
 * Created by Hom on 4/16/15.
 */
var currentMousePos = {
    x: -1,
    y: -1
};

/**
 * This document on load function. Load timesheet data with the login account and init dialogs.
 */
$(document).ready(function () {
    console.log("***** TimeSheet.js *****");
    //Load account timesheet with ajax
    $.ajax({
        url: "./rest/timesheet/accountTimeSheet",
        type: "GET",
        dataType: "json",
        success: function (events) {
            loadAccountTimeSheet(events);
        },
        error: function (error) {
            console.log("error");
            loadAccountTimeSheet();
        }
    });
    //trash
    $(window).bind('scroll resize', function () {
        var $this = $(this);

        $('#calendarTrash').stop().animate({
            top: $this.scrollTop() + $this.height() - $('#calendarTrash').height() - 20,
            left: $this.scrollLeft() + $this.width() - $('#calendarTrash').width() - 20
        }, 800);
    }).scroll();
    $('#calendarTrash').css({
        top: $(document).height(),
        left: $(window).width() - $('#calendarTrash').width - 20,
            opacity: 1
    });
    jQuery(document).on("mousemove", function (event) {
        currentMousePos.x = event.pageX;
        currentMousePos.y = event.pageY;
    });

    //init timesheet dialog
    initTimeSheetDialog();
    //init Dts message dialog
    initMessageDialog();
    initConfirmMessageDialog();
});

/**
 * load account timesheet data in full calendar
 */
function loadAccountTimeSheet(events) {
    var date = new Date();
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            //right: 'agendaWeek'
        },
        defaultDate: date,
        allDayDefault: false,
        defaultView: 'agendaWeek',
        editable: true,
        selectable: true,
        selectHelper: true,
        select: function (start, end) {
            console.log("select start:" + start + " end:" + end);
            createTempTimeSheetData(createTimesheetVOWithDateRange(start, end));
            $('#timesheetDialog').dialog("open");
        },
        //axisFormat: 'HH:mm',
        timeFormat: {
            agenda: 'HH:mm'
        },
        minTime: "08:00:00",
        maxTime: "20:00:00",
        events: events,
        eventClick: function (event, jsEvent, view) {
            console.log("id: " + event.id);
            console.log("pid: " + event.pid);
            console.log("title : " + event.title);

            loadTimeSheetData(event);
            $('#timesheetDialog').dialog("open");
        },
        eventDragStop: function (event, jsEvent, ui, view) {
            console.log('eventDragStop');
            console.log('id:' + event.id + ' pid = ' + event.pid);
            if (isElemOverDiv()) {
                //console.log('pid = ' + event.pid);
                deleteTimesheet(event.pid);
                $('#calendar').fullCalendar('removeEvents', event.id);
            }
        },
        eventResize: function( event, delta, revertFunc, jsEvent, ui, view ) {
            console.log("eventResize");
        }
    });

    function isElemOverDiv() {
        console.log("isElemOverDiv");
        var trashEl = $('#calendarTrash');

        var ofs = trashEl.offset();

        var x1 = ofs.left;
        var x2 = ofs.left + trashEl.outerWidth(true);
        var y1 = ofs.top;
        var y2 = ofs.top + trashEl.outerHeight(true);

        if (currentMousePos.x >= x1 && currentMousePos.x <= x2 &&
            currentMousePos.y >= y1 && currentMousePos.y <= y2) {
            return true;
        }
        return false;
    }

    //Change calendar view to agendaWeek, default is month
    //$('#calendar').fullCalendar('changeView', 'agendaWeek');
}

/**
 * Init Date Time Picker.
 */
function initDatepicker() {
    setDatePickerRange("timesheet_startDateTime", "timesheet_endDateTime");
};

/*
 * setup Date Time Picker
 */
function setDatePickerRange(start, end) {
    $('#' + start).datetimepicker({
        lang: 'zh-TW',
        format: 'Y/m/d H:i',
        allowTimes:[
          '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00',
          '13:00', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00',
          '16:30', '17:00', '17:30', '18:00'
         ]
    });
    $('#' + end).datetimepicker({
        lang: 'zh-TW',
        format: 'Y/m/d H:i',
        allowTimes:[
          '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00',
          '13:00', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00',
          '16:30', '17:00', '17:30', '18:00'
         ]
    });
};

/**
 * init Time Sheet Dialog
 */
function initTimeSheetDialog() {
    initDatepicker();
    findDutyAgents();
    $('#timesheetDialog').dialog({
        autoOpen: false,
        modal: true,
        closeOnEscape: true,
        width: 650,
        title: "Time Sheet",
        buttons: {
            "Save": function () {
                createTimeSheetData();
            },
            "Delete": function() {
                deleteTimesheetData();
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $('#timesheetForm')[0].reset();
        }
    });

    $("#timesheet_optionType").on("change", changeOptionType);
};

/*
 * Load TimeSheet Data in dialog
 */
function loadTimeSheetData(timesheet) {
    $('#event_id').val(timesheet.id);
    $('#timesheet_pid').val(timesheet.pid);
    $('#timesheet_title').val(timesheet.title);
    $('#timesheet_description').val(timesheet.description);
    $('#timesheet_startDateTime').val(timesheet.startDateTime);
    $('#timesheet_endDateTime').val(timesheet.endDateTime);
    $('#timesheet_optionType').val(timesheet.optionType);
    $('#timesheet_dutyAgent').val(timesheet.dutyAgent);
    console.log(timesheet.pid + ":" + timesheet.description + ":" + timesheet.startDateTime + ":" + timesheet.endDateTime + ":" + timesheet.optionType + ":" + timesheet.dutyAgent);
    checkTimesheetButtons();
    //$("#timesheet_optionType").on("change", changeOptionType);
    changeOptionType();
}

function changeOptionType() {
    var optionType = $('#timesheet_optionType').val();
    console.log("optionType:" + optionType);
    if (optionType != "LEAVE") {
        $('#timesheet_dutyAgent_tr').hide();
    } else {
        $('#timesheet_dutyAgent_tr').show();
    }
}

function checkTimesheetButtons() {
    //If no pid then hide Delete button
    if (!$('#timesheet_pid').val() || $('#timesheet_optionType').val() == "LEAVE") {
        $('.ui-dialog-buttonset button:contains("Delete")').button().hide();
    } else {
        $('.ui-dialog-buttonset button:contains("Delete")').button().show();
    }
    if ($('#timesheet_pid').val() && $('#timesheet_optionType').val() == "LEAVE") {
        $('.ui-dialog-buttonset button:contains("Save")').button().hide();
    } else {
        $('.ui-dialog-buttonset button:contains("Save")').button().show();
    }
}

function retriveTimesheetVO() {
    var timesheetVO;
    timesheetVO = {
        pid: $('#timesheet_pid').val(),
        title: $('#timesheet_title').val(),
        description: $('#timesheet_description').val(),
        startDateTime: $('#timesheet_startDateTime').val(),
        endDateTime: $('#timesheet_endDateTime').val(),
        optionType: $('#timesheet_optionType').val(),
        dutyAgent: $('#timesheet_dutyAgent').val(),
        start: $('#timesheet_startDateTime').val(),
        end: $('#timesheet_endDateTime').val(),
        startDateTime: $('#timesheet_startDateTime').val(),
        endDateTime: $('#timesheet_endDateTime').val()
    };
    console.log("start:" + $('#timesheet_startDateTime').val());
    console.log("start time:" + timesheetVO.startDateTime);
    return timesheetVO;
}

/**
 * Create JSON type TimesheetVO with start, end.
 */
function createTimesheetVOWithDateRange(start, end) {
    var timesheetVO;
    timesheetVO = {
        start: start.format("YYYY/MM/DD HH:mm"),
        end: end.format("YYYY/MM/DD HH:mm"),
        startDateTime: start.format("YYYY/MM/DD HH:mm"),
        endDateTime: end.format("YYYY/MM/DD HH:mm")
    };
    return timesheetVO;
}

/*
 * Create Temp TimeSheet with ajax, it will get a TimesheetVO JSON object from server.
 * And
 */
function createTempTimeSheetData(timesheetVO) {
    $.ajax({
        url: "./rest/timesheet/tempTimeSheet",
        type: "POST",
        data: JSON.stringify(timesheetVO),
        dataType: "json",
        contentType: "application/json",
        success: function (timesheet) {
            //$('#calendar').fullCalendar('renderEvent', data);
            console.log("create success:" + timesheet);
            //console.log("start:" + timesheet.start + " end:" + timesheet.end);
            //console.log(timesheet.pid + ":" + timesheet.description + ":" + timesheet.startDateTime + ":" + timesheet.endDateTime + ":" + timesheet.optionType + ":" + timesheet.dutyAgent);
            loadTimeSheetData(timesheet);
            //$('#calendar').fullCalendar('renderEvent', timesheet);
        },
        error: function () {
            console.error("error");
        }
    });
}

/*
 * For Dialog create button to create TimeSheet Data by ajax.
 */
function createTimeSheetData() {
    $.ajax({
        url: "./rest/timesheet/timeSheet",
        type: "POST",
        data: JSON.stringify(retriveTimesheetVO()),
        dataType: "json",
        contentType: "application/json",
        success: function (timesheet) {
            //console.log(timesheet.pid + ":" + timesheet.description + ":" + timesheet.startDateTime + ":" + timesheet.endDateTime + ":" + timesheet.optionType + ":" + timesheet.dutyAgent);
            console.log("ajax status:" + timesheet.status + " -> " + timesheet.messageType + " : " + timesheet.message);

            loadTimeSheetData(timesheet);
            if (timesheet.status == "Success") {
                console.log("Sucess ..........");
                $('#calendar').fullCalendar('renderEvent', timesheet);
                $('#timesheetDialog').dialog("close");
            } else {
                console.log("NOT Sucess ..........");
                timesheet.title = "DTS Timesheet Create"
                loadMessageData(timesheet);
                $('#messageDialog').dialog("open");
            }
        },
        error: function () {
            console.error("error");
        }
    });
}

/**
 * Delete timesheet data
 */
function deleteTimesheetData() {
    //open confirm dialog
    var baseJsonVO = {
        messageType: "Info",
        message: "Are you sure to delete it?",
        title: "DTS Confirm [deleteTimesheetConfirm]"
    };
    loadConfirmData(baseJsonVO);
    $('#confirmDialog').dialog("option", "title", "DTS Delete Confirm [deleteTimesheetConfirm]");
    $('#confirmDialog').dialog("open");

}

/**
 * Check isConfirm and pid not empty, if true then call deleteTimesheet(pid)
 */
function deleteTimesheetConfirm(isConfirm) {
    console.log("deleteTimesheetConfirm:" + isConfirm);
    if (isConfirm == true) {
        var pid = $('#timesheet_pid').val();
        console.log("pid:" + pid);
        if (pid && pid.length > 0) {
            deleteTimesheet(pid);
        } else {

        }
    }
}

function deleteTimesheet(pid) {
    $.ajax({
        url: "./rest/timesheet/" + pid,
        type: "DELETE",
        dataType: "json",
        contentType: "application/json",
        success: function (timesheet) {
            //console.log("delete success");
            console.log("ajax status:" + timesheet.status + " -> " + timesheet.messageType + " : " + timesheet.message);
            if (timesheet.status == "Success") {
                console.log("Sucess .......... event id:" + $('#event_id').val());
                $('#calendar').fullCalendar('removeEvents', $('#event_id').val());
                $('#timesheetDialog').dialog("close");
            } else {
                console.log("NOT Sucess ..........");
                timesheet.title = "DTS Timesheet Delete"
                loadMessageData(timesheet);
                $('#messageDialog').dialog("open");
            }
        },
        error: function () {
            console.error("error");
        }
    });
}

/**
 * Find available duty agent list
 */
function findDutyAgents() {
    console.log("findDutyAgents");
    $.ajax({
        url: "./rest/timesheet/dutyAgents",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (dutyAgents) {
            $("#timesheet_dutyAgent").empty();
            $.each(dutyAgents, function (i, dutyAgent) {
                //console.log("dutyAgent:" + dutyAgent.loginId);
                $("#timesheet_dutyAgent").append($("<option></option>").attr("value", dutyAgent.loginId).text(dutyAgent.loginId));
            });
        },
        error: function (xhr) {
            console.log("findParticipants error");
        }
    });
};

/**
 * init DTS Message Dialog
 */
function initMessageDialog() {
    $('#messageDialog').dialog({
        autoOpen: false,
        modal: true,
        closeOnEscape: true,
        width: 450,
        title: "Time Sheet Message",
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
};

/**
 * Handle confirm process. The title include function name and isConfirm mean which button be clicked.
 */
function confirmHandler(title, isConfirm) {
    //parse title to extract call function include square bracket
    var func = new RegExp(/\[[^]*\]/).exec(title);
    //remove square bracket
    func = new RegExp(/[^\[][^]*[^\]]/).exec(func);
    console.log("FUNC:" + func + " isConfirm:" + isConfirm);
    var fn = window[func];
    if (typeof fn == "function") {
        fn(isConfirm);
    }
}

/**
 * init DTS Confirm Dialog
 */
function initConfirmMessageDialog() {
    $('#confirmDialog').dialog({
        autoOpen: false,
        modal: true,
        closeOnEscape: true,
        width: 450,
        title: "Time Sheet Confirm",
        buttons: {
            "Yes": function () {
                $(this).dialog("close");
                confirmHandler($(this).dialog("option", "title"), true);
            },
            "No": function () {
                $(this).dialog("close");
                confirmHandler($(this).dialog("option", "title"), false);
            }
        },
        close: function () {
            //confirmHandler($(this).dialog("option", "title"), false);
        }
    });
};

/*
 * Load DTS message in dialog
 */
function loadMessageData(baseJsonVO) {
//    $('#messageImageSpan').removeClass();
//    $('#messageImageSpan').addClass('dts-message-' +  baseJsonVO.messageType);
//    //$('#imageSpan').addClass('dts-message-Warn');
//    $('#messageMessageSpan').text(baseJsonVO.message);
//    console.log($('#messageImageSpan').attr("class"));
    loadBaseMessageData(baseJsonVO, "message");
}

/*
 * Load Confirm message in dialog
 */
function loadConfirmData(baseJsonVO) {
//    $('#messageImageSpan').removeClass();
//    $('#messageImageSpan').addClass('dts-message-' +  baseJsonVO.messageType);
//    //$('#imageSpan').addClass('dts-message-Warn');
//    $('#messageMessageSpan').text(baseJsonVO.message);
//    console.log($('#messageImageSpan').attr("class"));
    loadBaseMessageData(baseJsonVO, "confirm");
}

/*
 * Load TimeSheet Data in dialog
 */
function loadBaseMessageData(baseJsonVO, dialogPrefix) {
    var imageSpan = '#' + dialogPrefix + 'ImageSpan';
    var messageSpan = '#' + dialogPrefix + 'MessageSpan';
    var dialogDiv = '#' + dialogPrefix + 'Dialog';
    $(imageSpan).removeClass();
    $(imageSpan).addClass('dts-message-' +  baseJsonVO.messageType);
    //$(imageSpan).addClass('dts-message-Warn');
    $(messageSpan).text(baseJsonVO.message);
    if (baseJsonVO.title) {
        $(dialogDiv).dialog("option", "title", baseJsonVO.title);
    } else {
        $(dialogDiv).dialog("option", "title", "DTS Dialog");
    }
    console.log($(imageSpan).attr("class"));
}

