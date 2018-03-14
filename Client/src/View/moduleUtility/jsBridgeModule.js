/**
 * Created by fabiodisabatino on 27/02/18.
 */
var jsBridgeModule=(function () {

    //------------------------------------private method----------------------------------------//

    var __pubsub;

    __sendToJava=function (packet) {

        javaBridge.receive(packet);
    };

    __receiveFromJava=function (packet) {


        __pubsub.publish(packet["task"],packet);
    };


    var __init=function (pubSub) {

    __pubsub=pubSub;

    };

    //------------------------------------public method----------------------------------------//

    return{

        initModule:__init,
        send:__sendToJava,
        receive:__receiveFromJava

    }







})();