/**
 * Created by fabiodisabatino on 08/04/17.
 */
var placeShipController= (function () {

    var __pubsub;
    var __shipPlaced={
        "task":"placeShips",
        "data":[]
    };

    //------------------------------------private method----------------------------------------//



    //chiamata al server per addShipToField
    var __addShip=function (dim,position,orientation) {
        __shipPlaced.data.push({
                "dim":dim,
                "position":position,
                "orientation":orientation
            }
        );

    };


    var __placeShips=function () {
        console.log("sending ships placed...");
        jsBridgeModule.send(__shipPlaced);
    };

    //TODO: funzione da progettare
    var __removeShip=function (dim,position) {
        cosole.log('Removing ship'+dim+' in position'+position);
    };



    //------------------------------------public method----------------------------------------//
        var init=function (pubSub) {

            __pubsub=pubSub;

            //add a listener for the ShipsPlaced event
            __pubsub.subscribe("shipsPlaced",function (data) {
                console.log(data);
            });
        };


        //return an object with only public method
        return {
            initModule:init,
            addShip:__addShip,
            placeShips:__placeShips
        }


    })();
