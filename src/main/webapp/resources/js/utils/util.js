/**
 * utils.js
 * sample: $('#form').serializeObject();
 * return Obj
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/**
 * parse form input、checkBox、radio、select... to JSON Type
 * sample: formToJSON(formId)
 * return JSON
 */
function formToJSON(fromId) {
    return JSON.stringify($('#' + fromId).serializeObject());
};

function isJSON(data) {
    var isJson = false;
    try {
        // this works with JSON string and JSON object, not sure about others
        var json = $.parseJSON(data);
        isJson = typeof json === 'object';
    } catch (ex) {
        console.error('data is not JSON');
    }
    return isJson;
};

function changeByteToImg(icon) {
    return "data:image/png;base64," + icon;
};

/**
 * Setting start、end DatePicker Range,
 * Have to import jquery-ui
 *
 * @param start
 * @param end
 */
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