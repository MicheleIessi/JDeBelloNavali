/**
 * Created by fabiodisabatino on 22/04/17.
 */
var moduleFlagBanner=(function () {


    //------------------------------------public method----------------------------------------//

    var init=function () {
        $('.navbar-brand').append(" <img class='flagCivilization' alt='Brand' src="+metaData.flagCivilitazion+">");
        $('.navbar-header').append("<p class='fleetName navbar-text'>"+metaData.fleetName+"</p>");
    }

    return{
        initModule:init
    }




})();