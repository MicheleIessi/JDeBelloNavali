/**
 * Created by fabiodisabatino on 22/04/17.
 */
var moduleTimerZone=(function () {

    //------------------------------------private method----------------------------------------//


    //------------------------------------public method----------------------------------------//
    var init=function () {
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

    return {
        initModule:init
    }

})();