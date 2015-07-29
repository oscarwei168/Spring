/*
 * Copyright (c) 2015. Acer co., consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * Created by Frank.T.Lin on 2015/4/17.
 */
$(function () {
    $(document).ajaxStart(function () {
        console.log("ajaxStart");
        $.blockUI({
            css: {
                border: 'none',
                padding: '15px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity: .5,
                color: '#fff'
            }
        });
    });

    $(document).ajaxSend(function (event, xhr, settings) {
        //console.log("ajaxSend");
    });

    $(document).ajaxSuccess(function (event, xhr, settings) {
        //console.log("ajaxSuccess");
    });

    $(document).ajaxError(function (event, xhr, settings, exception) {
        //console.log("ajaxError");
    });

    $(document).ajaxComplete(function (event, xhr, settings) {
        //console.log("ajaxComplete");
    });

    $(document).ajaxStop(function () {
        //console.log("ajaxStop");
        $.unblockUI();
    });
});



