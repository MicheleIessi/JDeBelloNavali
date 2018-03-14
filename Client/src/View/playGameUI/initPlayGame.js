/**
 * Created by Faith on 25/03/17.
 */
(function (metaData) {
    $(document).ready(function () {

        $('.mainPage').matchHeight({
            property: 'height',
            target: $("body")
        });
        setTimerZone();
        cursorModule.initModule();
        moduleShipListPlayGame.initModule();
        moduleUserField.initModule();

    });


}());

var setTimerZone=function () {

    clock = new FlipClock($('.timer'), 180, {
        //autoStart:false,
        countdown:true,
        // Create a minute counter
        clockFace: 'MinuteCounter',

        // The onStart callback
        onStart: function() {
            // Do something
        },

        // The onStop callback
        onStop: function() {
            // Do something
        },

        // The onReset callback
        onReset: function() {
            // Do something
        }
    });
    $('.flip-clock-label').remove();
    $('.minutes').remove();

};