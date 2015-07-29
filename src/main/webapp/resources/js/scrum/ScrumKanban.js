/**
 * ScrumKanban.js
 */
var statusObj = {
    "todo": "todo",
    "inProgress": "inProgress",
    "demoAndFeedBack": "demoAndFeedBack",
    "completed": "completed"
};

var bgcolor = {
    "Height": "task-red",
    "Medium": "task-yellow",
    "Low": "task-green"
};

$(document).ready(function () {

    // USE HTML 5 Local Storage
    if (typeof(Storage) !== "undefined") {
        // Code for localStorage/sessionStorage.
        console.log("Code for localStorage/sessionStorage.");
    } else {
        // Sorry! No Web Storage support..
        console.log("Sorry, your browser does not support Web Storage...");
    }

    // 初始化 StoryDialog
    newAddStoryDialog();
    newAddStoryItemDialog();
    initDatepicker();
    findAllStory();
    finAllParticipant();
    findParticipants();

    // jquery-ui accordion
    $('#storyAccordion').accordion({
        activate: function (event, ui) {
            var storyId = ui.newHeader.attr("id");
            findStoryItem(storyId);
        }
    });

    $(".fbbox").hover(function () {
        $(this).stop().animate({right: "0"}, "medium");
    }, function () {
        $(this).stop().animate({right: "-165"}, "medium");
    }, 300);

    // Story/Story item name abbreviate
    $('div#storyAccordion [class^=abbriv]').abbreviate({length: 5});

    $('#btnOpenDialog').click(addDeleteStoryDialog);
});

/**
 *  Initialize datepickers
 */
function initDatepicker() {
    /***** for Story Dialog *****/
    setDatePickerRange("startDate", "endDate");

    /***** for Story Item Dialog *****/
    setDatePickerRange("item_startDate", "item_endDate");
};

function findAllStory() {
    console.log("findAllStory");
    $.ajax({
        url: "./rest/stories",
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("search success");
            addStory(data);
        },
        error: function () {
            console.log("search error");
        }
    });
}

function finAllParticipant() {
    // find all participant
    $.ajax({
        url: "./rest/participants",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (participants) {
            //console.log(participants);
            $.each(participants, function (i, participant) {
                var participantString =
                    "<div title='" + participant.participantId + "' id='" + participant.participantId + "'>" +
                    "<img class='task-avatar' src='" + changeByteToImg(participant.picData) + "' />" + participant.participantId + "</div>";

                $('.fbbox').append(participantString);

                // set pic to localStorage
                localStorage.setItem(participant.participantId, changeByteToImg(participant.picData));
            });
        }
    });
}

// Ajax-style Modal panel
//$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

$(function () {
    $(".sortable").sortable({
        placeholder: "ui-state-highlight",
        connectWith: ".connectedSortable"
    });

    $(".sortable").disableSelection();

    $('#location1').dblclick(function () {
        newAddStoryDialog();
        $('#storyDialog').dialog("open");
    });
});

/**
 * 為了讓Add、Edit共用同一個Dialog, 所以建了兩個new Dialog的方法
 * 兩者會有不同的Title跟Button
 * */
function newAddStoryDialog() {
    $('#storyDialog').dialog({
        autoOpen: false,
        modal: true,
        width: 900,
        title: "Create New Story",
        buttons: {
            "Create": createStory,
            Cancel: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $('#storyForm')[0].reset();
        }
    });

    $('#storyForm select[name=status]').empty().append('<option value="New">New</option>option>');
};


/**
 * Edit Story Dialog
 * */
function newEditStoryDialog() {
    $('#storyDialog').dialog({
        autoOpen: false,
        modal: true,
        width: 900,
        title: "Edit Story",
        buttons: {
            "Save": saveStory,
            "Delete": addDeleteStoryDialog,
            Cancel: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $('#storyForm')[0].reset();
        }
    });

    $('#storyForm select[name=status]').empty().
        append('<option value="New">New</option>option><option value="Closed">Closed</option>option>');
};

/**
 * add stories panel
 * @param storys a list of stories
 */
function addStory(storys) {
    $.each(storys, function (i, story) {
        var storyName = "<h3 class='abbriv' id='" + story.storyId + "' ondblclick=editStory('" + story.storyId + "')>"
            + story.storyName + "</h3>";
        var storyDesc = "<div><p>" + story.storyDescription + "</p></div>";
        $('#storyAccordion').append(storyName + storyDesc);
    });
    $('#storyAccordion').accordion("refresh");

    $.each(storys[storys.length - 1].storyItemVOList, function (i, storyItem) {
        for (var key in statusObj) {
            if (key.toUpperCase() == storyItem.storyItemstatus.toUpperCase()) {
                addStoryItem(statusObj[key], storyItem, storys[storys.length - 1].storyId);
                break;
            }
        }
    });
};
/**
 *  Create a new story
 */
function createStory() {
    console.log(formToJSON('storyForm'));
    $.ajax({
        url: "./rest/storyCreate",
        type: "POST",
        data: formToJSON('storyForm'),
        dataType: "json",
        contentType: "application/json",
        success: function (story) {
            console.log("create Story Success.");
            var storyName = "<h3 id=" + story.storyId + " ondblclick=editStory('" + story.storyId + "')>" + story.storyName + "</h3>";
            var storyDesc = "<div><p>" + story.storyDescription + "</p></div>";
            $('#storyAccordion').append(storyName + storyDesc);
            $('#storyAccordion').accordion("refresh");

            $('#storyDialog').dialog("close");
        },
        error: function (xhr) {
            console.log("create story error");
        }
    });
};

/**
 * Save Story Dialog
 * transfer storyForm to JSON By formToJSON and Use restful url: '/rest/save' to Save Story
 * */
function saveStory() {
    var saveStory = formToJSON('storyForm');
    console.log(saveStory);
    $.ajax({
        url: "./rest/save",
        type: "POST",
        data: saveStory,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("Save Success.");
        },
        error: function (xhr) {
            console.log("Save Fail.");
        }
    });
};

function findStoryItem(storyId) {
    cleanPanel();
    $.ajax({
        url: "./rest/storyItem/" + storyId,
        type: "POST",
        dataType: "json",
        success: function (storyItems) {
            $.each(storyItems, function (i, storyItem) {
                for (var key in statusObj) {
                    if (key.toUpperCase() == storyItem.storyItemstatus.toUpperCase()) {
                        addStoryItem(statusObj[key], storyItem, storyId);
                        break;
                    }
                }
            });
        },
        error: function () {
            console.log("find story item error");
        }
    });
};

function cleanPanel(status) {
    for (var key in statusObj) {
        $('#' + key).empty();
    }
};

function addStoryItem(status, storyItem, storyId) {
    var participantId = storyItem.participantByParticipantId;
    var bgc;
    if (storyItem.priority != null) {
        bgc = $(bgcolor).attr(storyItem.priority);
    } else {
        bgc = 'task-yellow';
    }
    var dragString = "<div class='task bg " + bgc + "' id='" + storyId + '-' + storyItem.storyItemId + "' draggable='true' ondragstart='drag(event)' ondblclick='editStoryItem(" + storyItem.storyItemId + ")'>";
    dragString += "<div class='task-avatarList'><div class='task-avatarWrapper'><img class='task-avatar' src='" +
    localStorage.getItem(participantId) + "' />" + "</div></div><div class='task-avatarPlaceholder'></div>" + storyItem.storyItemSeq + "." + storyItem.storyItemName +
    "</div>";
    $('#' + status).append(dragString);
};

function editStory(storyId) {
    $.ajax({
        url: "./rest/story/" + storyId,
        type: "POST",
        dataType: "json",
        success: function (story) {
            for (var key in story) {
                $.each($('#storyForm').find('input, select, textarea'), function (i) {
                    if ($(this).attr('name') == key) {
                        $(this).val(story[key]);
                    }
                });
            }
            newEditStoryDialog();
            $('#storyDialog').dialog('open');
        },
        error: function () {
            console.error("save error");
        }
    });
};

/**
 * Call restful web service(url:./rest/storyItem/save) for saving story Item status
 * @param storyId
 * @param storyItemId
 * @param status
 * */
function saveStoryItemState(storyId, storyItemId, status) {
    var storyStatus = JSON.stringify({storyId: storyId, storyItemId: storyItemId, status: status});
    $.ajax({
        url: "./rest/storyItem/status/save",
        type: "POST",
        data: storyStatus,
        dataType: "json",
        contentType: "application/json",
        success: function () {
            console.log("Save status - Success.");
            findStoryItem(storyId);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown);
            console.log("Save status - Fail.");
        }

    });
};

function allowDrop(ev) {
    ev.preventDefault();
};

/**
 * handling the drop event and also call the method to chagne StoryItem status
 * @param ev drop event
 * */
function drop(ev) {
    //console.log("drop");
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");

    var dataSplit = data.split("-");
    var storyId = dataSplit[0];
    var storyItemId = dataSplit[1];
    var status = $(ev.target).children().attr('id');

    //convert the status to the correct format for DB
    for (var key in statusObj) {
        if (statusObj[key] == status) {
            status = key;
        }
    }

    //ev.target.appendChild(document.getElementById(data));
    //$(ev.target).children().append(document.getElementById(data));

    saveStoryItemState(storyId, storyItemId, status);
};

function drag(ev) {
    //console.log("drag");
    //console.log( ev.currentTarget.id);
    ev.dataTransfer.setData("text", ev.currentTarget.id);
};

$(function () {
    $('#location2,#location3,#location4,#location5').dblclick(function () {
        var status = $(this).children('ul').attr('id');
        $('#storyItemstatus').val(status);
        //findParticipants();
        newAddStoryItemDialog();
        $('#storyItemDialog').dialog("open");
    });
});

function findParticipants() {
    console.log("findParticipants");
    $.ajax({
        url: "./rest/participants",
        success: function (data) {
            $("#participantByParticipantId").empty();
            $.each(data, function (i, participant) {
                $("#participantByParticipantId").append($("<option></option>").attr("value", participant.participantId).text(participant.participantId));
                $('#participantByParticipantId').filterByText($('#participant_input'), true);
            });
        },
        error: function (xhr) {
            console.log("findParticipants error");
        }
    });
};

jQuery.fn.filterByText = function (textbox, selectSingleMatch) {
    return this.each(function () {
        var select = this;
        var options = [];
        $(select).find('option').each(function () {
            options.push({value: $(this).val(), text: $(this).text()});

        });
        $(select).data('options', options);
        $(textbox).bind('change keyup', function () {
            var options = $(select).empty().scrollTop(0).data('options');
            var search = $.trim($(this).val());
            var regex = new RegExp(search, 'gi');
            $.each(options, function (i) {
                var option = options[i];
                if (option.text.match(regex) !== null) {
                    $(select).append(
                        $('<option>').text(option.text).val(option.value)
                    );
                }
            });
            if (selectSingleMatch === true &&
                $(select).children().length === 1) {
                $(select).children().get(0).selected = true;
            }
        });
    });
};

/**
 * call restful web service to create storyItem
 */
function createStoryItem() {
    console.log($('#storyByStoryId').val($('.ui-accordion-header-active').attr('id')));
    $('#storyByStoryId').val($('.ui-accordion-header-active').attr('id'));
    var storyItem = formToJSON('storyItemForm');
    console.log(storyItem);
    $.ajax({
        url: "./rest/storyItem/create",
        type: "POST",
        data: storyItem,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("create StoryItem Success.");
            console.log("storyid : " + data.storyByStoryId);
            findStoryItem(data.storyByStoryId);
            $('#storyItemDialog').dialog("close");
        },
        error: function (xhr) {
            console.log("create storyItem error");
        }
    });
};
/**
 * set new StoryItem Dialog
 */
function newAddStoryItemDialog() {
    $('#storyItemDialog').dialog({
        autoOpen: false,
        modal: true,
        width: 900,
        title: "Create New StoryItem",
        buttons: {
            "Create": createStoryItem,
            Cancel: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $('#storyItemForm')[0].reset();
        }
    });
};

/**
 * set Edit StoryItem Dialog
 * @param storyItemId storyItemId
 */
function newEditStoryItemDialog(storyItemId) {
    $('#storyItemDialog').dialog({
        autoOpen: false,
        modal: true,
        width: 900,
        title: "Edit StoryItem: " + storyItemId,
        buttons: {
            "Save": saveStoryItem,
            "Delete": addDeleteStoryItemDialog,
            Cancel: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $('#storyItemForm')[0].reset();
        }
    });
};

/**
 * Open Edit StoryItem Dialog
 * @param storyItemId storyItemId
 */
function editStoryItem(storyItemId) {
    $.ajax({
        url: "./rest/storyItem/storyItemId/" + storyItemId,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        success: function (storyItem) {
            for (var key in storyItem) {
                $.each($('#storyItemForm').find('input, select, textarea'), function (i) {
                    if ($(this).attr('name') == key) {
                        console.log("key: " + key + " value: " + storyItem[key]);
                        $(this).val(storyItem[key]);
                    }
                });
            }
            newEditStoryItemDialog(storyItemId);
        },
        error: function () {
            console.error("save error");
        }
    });
};
/**
 * call restful web service to edit StoryItem
 */
function saveStoryItem() {
    var saveStoryItem = formToJSON('storyItemForm');
    $.ajax({
        url: "./rest/storyItem/save",
        type: "POST",
        data: saveStoryItem,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("save success");
            findStoryItem(data.storyByStoryId);
            $('#storyItemDialog').dialog("close");
        },
        error: function () {
            console.error("save error");
        }
    });
};
/**
 * Delete Story and story items
 */
function deleteStory() {
    console.log("[Enter] deleteStory");
    var story = formToJSON('storyForm');
    var locationArray = ["location2", "location3", "location4", "location5"];
    $.ajax({
        url: "./rest/story/delete",
        type: "POST",
        data: story,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.info("delete success");
            //remove story
            $('#' + data.storyId).next().remove();
            $('#' + data.storyId).remove();
            //remove story items
            $.each(locationArray, function (index, location) {
                console.log($('#' + location + ' div[id^="' + data.storyId + '-"]'));
                $('#' + location + ' div[id^="' + data.storyId + '-"]').remove();
            });

            $('#storyDialog').dialog("close");

        },
        error: function () {
            console.error("delete: error occurs");
            alert("Delete ERROR!!");
            $('#storyDialog').dialog("close");
        }
    });
}
/**
 * Delete Story item
 */
function deleteStoryItem() {
    var storyItem = formToJSON('storyItemForm');
    console.log("[Enter] deleteStoryItem");
    $.ajax({
        url: "./rest/storyItem/delete",
        type: "POST",
        data: storyItem,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.info("delete success");
            $('#' + data.storyByStoryId + '-' + data.storyItemId).remove();
            $('#storyItemDialog').dialog("close");
        },
        error: function () {
            console.error("delete: error occurs");
            alert("Delete ERROR!!");
            $('#storyItemDialog').dialog("close");
        }
    });
}

function addDeleteStoryDialog() {
    $("#dialog-confirm").html("Delete this Story");

    // Define the Dialog and its properties.
    $("#dialog-confirm").dialog({
        resizable: false,
        modal: true,
        title: "Delete this Story",
        height: 250,
        width: 400,
        buttons: {
            "Confirm": function () {
                $(this).dialog('close');
                deleteStory();
            },
            "Cancel": function () {
                $(this).dialog('close');
            }
        }
    });
}

function addDeleteStoryItemDialog() {
    $("#dialog-confirm").html("Delete this Story Item");

    // Define the Dialog and its properties.
    $("#dialog-confirm").dialog({
        resizable: false,
        modal: true,
        title: "Delete this Story Item",
        height: 250,
        width: 400,
        buttons: {
            "Confirm": function () {
                $(this).dialog('close');
                deleteStoryItem();
            },
            "Cancel": function () {
                $(this).dialog('close');
            }
        }
    });
}
