/**
 * Created by Hom on 7/3/15.
 */

$(document).ready(function () {
    var lastsel3;
    var dailyReport = '';
    jQuery("#dailyReport").jqGrid({
        datatype: "json",
        autowidth: true,
        autoheight: true,
        colNames: ['ID', '工作項目', '專案', '追蹤號碼', '分類', '工作時數', '單位', '預計完成日期', '實際完成日期', '目前狀況', '完成率', '備註'],
        colModel: [
            {name: 'pid', index: 'pid', width: 140, sorttype: "int", editable: true},
            {
                name: 'workDescription',
                index: 'workDescription',
                width: 180,
                editable: true,
                editoptions: {size: "20", maxlength: "30"}
            },
            {
                name: 'projectName',
                index: 'projectName',
                width: 130,
                editable: true,
                edittype: "select",
                editoptions: {value: "PROJ15PJ000066:AGBS-TW;PROJ15PJ000067:AGBS-CN;PROJ15PJ000068:AGBS-Global;PROJ15PJ000069:Cognos;PROJ15PJ000070:Others"}
            },
            {
                name: 'trackerNo', index: 'trackerNo', width: 80, editable: true,
                editoptions: {size: "20", maxlength: "30"}
            },
            {
                name: 'workType',
                index: 'workType',
                width: 100,
                editable: true,
                edittype: "select",
                editoptions: {value: "WORK15WT000071:CR;WORK15WT000072:BUG;WORK15WT000073:OTHER;WORK15WT000074:TASK;WORK15WT000075:SUPPORT"}
            },
            {name: 'workTime', index: 'workTime', width: 80, editable: true},
            {
                name: 'workTimeType',
                index: 'workTimeType',
                width: 80,
                editable: true,
                edittype: "select",
                editoptions: {value: "TIME15TT000076:D;TIME15TT000077:H;TIME15TT000078:M"}
            },
            {
                name: 'estimateCompleteDate',
                index: 'estimateCompleteDate',
                width: 130,
                sorttype: 'date',
                editable: true,
                editoptions: {
                    dataInit: function (el) {
                        setTimeout(function () {
                            $(el).datepicker();
                        }, 200);
                    }
                }
            },
            {
                name: 'actualCompleteDate',
                index: 'actualCompleteDate',
                width: 130,
                sorttype: 'date',
                editable: true,
                editoptions: {
                    dataInit: function (el) {
                        setTimeout(function () {
                            $(el).datepicker();
                        }, 200);
                    }
                }
            },
            {
                name: 'currentStatus',
                index: 'currentStatus',
                width: 160,
                editable: true,
                edittype: "select",
                editoptions: {value: "WKST15WS000079:New;WKST15WS000080:In-Progress;WKST15WS000081:Pending;WKST15WS000082:Done;WKST15WS000083:Cancel"}
            },
            {name: 'completePercent', index: 'completePercent', width: 100, editable: true},
            {
                name: 'remark',
                index: 'remark',
                width: 200,
                sortable: false,
                editable: true,
                edittype: "textarea",
                editoptions: {rows: "2", cols: "80"}
            }
        ],
        onSelectRow: function (id) {
            console.log("id:" + id);
            console.log("lastsel3:" + lastsel3);
            console.log("Test:" + (id !== lastsel3));
            if (id !== lastsel3) {
                jQuery('#dailyReport').jqGrid('restoreRow', lastsel3);
                jQuery('#dailyReport').jqGrid('editRow', id, true, pickdates);
                jQuery("#dailyReport").jqGrid('bindKeys', {
                    "onSpace": function (id) {
                        $('#dailyReport').jqGrid('delRowData', id);
                    }
                });
                lastsel3 = id;
            }
        },
        caption: "Daily Report"
    });
    $.ajax({
        url: "./rest/dailyreports",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("success " + data);
            dailyReport = data;
            $.each(dailyReport, function (i, dailyReport) {
                console.log(dailyReport);
                jQuery("#dailyReport").jqGrid('addRowData', dailyReport.pid, dailyReport);
            });
        },
        error: function () {
            console.log(" error");
        }
    });
});

function pickdates(id) {
    jQuery("#" + id + "_sdate", "#dailyReport").datepicker({dateFormat: "yy-mm-dd"});
}