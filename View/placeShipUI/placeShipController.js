/**
 * Created by fabiodisabatino on 08/04/17.
 */
var placeShipController= (function () {

    var __pubsub;

    //------------------------------------private method----------------------------------------//




    //chiamata al server per addShipToField
    var __addShip=function (dim,position) {
        var packet={
            "task":"addShipToField",
            "data":{
                "dim":dim,
                "position":position
            }
        };

        jsBridgeModule.sendToJava(packet)
    };

    //TODO: funzione da progettare
    var __removeShip=function (dim,position) {
        cosole.log('Removing ship'+dim+' in position'+position);
    };





    //------------------------------------public method----------------------------------------//
        var init=function (pubSub) {

            __pubsub=pubSub;

            __pubsub.subscribe("ShipPlaced",function (data) {

                console.log(data);
            });
        };


        //return an object with only public method
        return {
            initModule:init,
            addShipToField:__addShip
        }


    })();
