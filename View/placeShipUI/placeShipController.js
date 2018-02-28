/**
 * Created by fabiodisabatino on 08/04/17.
 */
var placeShipController={

    data:{},

    //chiamata al server per addShipToField
    addShip:function (dim,position) {
        var data={
            "task":"addShipToField",
            "ship":{
                "dim":dim,
                "position":position
            }
        };
        jsBridgeModule.sendToJava(data)
    },

    //non ancora progettata
    removeShip:function (dim,position) {

        cosole.log('Removing ship'+dim+' in position'+position);
    }






}