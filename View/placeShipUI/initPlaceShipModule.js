/**
 * Created by fabiodisabatino on 11/03/17.
 */
(function (metaData) {
    $(document).ready(function () {

        $('.contentMain').matchHeight({
            property: 'height',
            target: $('.bodySize')
        });

        var pubsub=amplify;

        jsBridgeModule.initModule(pubsub);
        placeShipController.initModule(pubsub);
        moduleFlagBanner.initModule();
        moduleGridZone.initModule();
        moduleShipList.initModule();
        moduleTimerZone.initModule();




    });





}());


