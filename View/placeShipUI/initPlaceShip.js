/**
 * Created by fabiodisabatino on 11/03/17.
 */
(function (metaData) {
    $(document).ready(function () {

        $('.contentMain').matchHeight({
            property: 'height',
            target: $('.bodySize')
        });

        jsBridgeModule.initModule();
        moduleFlagBanner.initModule();
        moduleGridZone.initModule();
        moduleShipList.initModule();
        moduleTimerZone.initModule();




    });





}());


