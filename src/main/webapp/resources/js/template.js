    /**
       * init Template Dialog
       */
    function initTemplate(templateId, templateTitle, formId) {
        $(templateId).dialog({
            autoOpen: true,
            modal: true,
            width: 900,
            title: templateTitle,
            close: function () {
                $(formId)[0].reset();
            },
            open: function(event, ui) { $(".ui-dialog-titlebar-close}", ui.dialog || ui).hide(); }
        });
    };

    function initDatePicker(datePickerId) {
        $(datePickerId).datepicker({
            dateFormat: 'yy-mm-dd',
            buttonImage: '/dts/resources/images/Calender-icon.png',
            showOn: 'button',
            buttonImageOnly: true,
            buttonText: ''
        });
    };
		
		