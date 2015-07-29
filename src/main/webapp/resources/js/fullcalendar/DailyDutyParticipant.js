/**
 * Created by Hom on 4/16/15.
 */
var currentMousePos = {
    x: -1,
    y: -1
};

$(document).ready(function () {
    initDatepicker();
    $.ajax({
        url: "./rest/search/dutyList",
        type: "POST",
        dataType: "json",
        success: function (dutyList) {
            console.log(dutyList);
            generateDutyList(dutyList);
        },
        error: function () {
            console.error("error");
        }
    });
    jQuery(document).on("mousemove", function (event) {
        currentMousePos.x = event.pageX;
        currentMousePos.y = event.pageY;
    });

    $('#calendarTrash').css({
        top: $(document).height(),
        left: $(window).width() - $('#calendarTrash').width - 20,
        opacity: 1
    });

    $(window).bind('scroll resize', function () {
        var $this = $(this);

        $('#calendarTrash').stop().animate({
            top: $this.scrollTop() + $this.height() - $('#calendarTrash').height() - 20,
            left: $this.scrollLeft() + $this.width() - $('#calendarTrash').width() - 20
        }, 800);
    }).scroll();
});

/**
 *  Initialize datepickers
 */
function initDatepicker() {
    /***** for Daily Duty Dialog *****/
    setDatePickerRange("startDate", "endDate");
};

function setDatePickerRange(start, end) {
    $('#' + start).datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        onClose: function (selectedDate) {
            $('#' + end).datepicker("option", "minDate", selectedDate);
        }
    });
    $('#' + end).datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        onClose: function (selectedDate) {
            $('#' + start).datepicker("option", "maxDate", selectedDate);
        }
    });
};

function generateDutyList(dutyList) {
    var date = new Date();
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek'
        },
        defaultDate: date,
        selectable: true,
        selectHelper: true,
        select: function (start, end) {
            var participantOnDuty;
            var participantId = prompt('Assign a task to:');
            if (participantId) {
                participantOnDuty = {
                    title: participantId,
                    start: start.format("YYYY-MM-DD 00:00:00"),
                    end: end.format("YYYY-MM-DD 00:00:00")
                };
                createParticipantOnDuty(participantOnDuty);
            }
            $('#calendar').fullCalendar('unselect');
        },
        editable: true,
        events: dutyList,
        eventClick: function (calEvent, jsEvent, view) {
            console.log("id: " + calEvent.id);
            console.log("participant id : " + calEvent.title);
            console.log("start: " + calEvent.start);
            console.log("end: " + calEvent.end);

            //findParticipants(calEvent.title);
            //$('#startDate').val(calEvent.start.format("YYYY-MM-DD"));
            //$('#endDate').val(calEvent.end.format("YYYY-MM-DD"));
            //editDailyDutyParticipantDialog();
        },
        eventDragStop: function (event, jsEvent, ui, view) {
            console.log('eventDragStop');
            if (isElemOverDiv()) {
                console.log('event id = ' + event.id);
                deleteParticipantOnDuty(event.id);
                $('#calendar').fullCalendar('removeEvents', event.id);
            }
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
}

function findParticipants(participantId) {
    console.log("findParticipants");
    $.ajax({
        url: "./rest/participants",
        type: "GET",
        dataType: "json",
        success: function (data) {
            $("#participantByParticipantId").empty();
            $.each(data, function (i, participant) {
                $("#participantByParticipantId").append($("<option></option>").attr("value", participant.participantId).text(participant.participantId));
                if (participant.participantId == participantId) {
                    $('select[name=participantByParticipantId]').val(participantId).attr("selected", true);
                }
            });
        },
        error: function (xhr) {
            console.log("findParticipants error");
        }
    });
};

function createParticipantOnDuty(participantOnDuty) {
    $.ajax({
        url: "./rest/create/dailyduty",
        type: "POST",
        data: JSON.stringify(participantOnDuty),
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            $('#calendar').fullCalendar('renderEvent', data);
            console.log("create success");
        },
        error: function () {
            console.error("error");
        }
    });
}

function deleteParticipantOnDuty(dailyDutyParticipantId) {
    $.ajax({
        url: "./rest/delete/dailyduty/" + dailyDutyParticipantId,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("delete success");
        },
        error: function () {
            console.error("error");
        }
    });
}