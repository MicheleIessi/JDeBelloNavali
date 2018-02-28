/**
 * Created by fabiodisabatino on 27/02/18.
 */
var jsBridgeModule=(function () {

    //------------------------------------private method----------------------------------------//

    __sendToJava=function () {

        javaBridge.provaJava({msg:"ciao da JS!!"});
    };

    __prova=function (msg) {

        console.log(msg)
    };






    var __init=function () {

        $('.random').click(function(){
            __sendToJava();
        })

    };

    //------------------------------------public method----------------------------------------//

    return{

        initModule:__init,
        sendToJava:__sendToJava,
        provaJS:__prova


    }







})();