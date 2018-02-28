/**
 * Created by fabiodisabatino on 27/02/18.
 */
var jsBridgeModule=(function () {

    //------------------------------------private method----------------------------------------//

    __sendToJava=function (data) {

        javaBridge.receive(data);
    };

    __receiveFromJava=function (data) {

        console.log(data);
    }


    var __init=function () {



    };

    //------------------------------------public method----------------------------------------//

    return{

        initModule:__init,
        sendToJava:__sendToJava,
        receiveFromJava:__receiveFromJava

    }







})();