/**
 * Created by fabiodisabatino on 22/04/17.
 */

var HORIZONTAL = 0;
var VERTICAL = 1;

var moduleGridZone = (function () {

    var __isPlaceable = function (cursor, actualWeight, shipWeight, shipDim, orientation) {
        console.log("PLACEABILITY ON " + cursor.attr("id") + " with dimension " + shipDim);
        for (var i = 0; i < shipDim; i++) {
            cursor = cursorModule.getNextCursor(cursor, orientation);
            if (cursor.hasClass("placed ui-state-highlight") || cursor.attr("id") == undefined) {
                return false
            }
            //cursor = cursorModule.getNextCursor(cursor, orientation);
            console.log("NEXT CURSOR AFTER ORIENTATION: " + cursor.attr("id"));
        }

        if (actualWeight + shipWeight <= 100) {
            return true

        }
        else {
            return false
        }


    };

    var __toogleOrientation = function (orientation) {

        if (orientation == HORIZONTAL)
            return VERTICAL;
        else if (orientation == VERTICAL)
            return HORIZONTAL;
    };

    //------------------------------------public method----------------------------------------//
    var init = function () {
        var heightText = $('.fleetWeight').height();
        var padding = (heightText - 20) / 20;
        $('.weightProgress').css("padding-top", padding);
    };

    var updateFleetWeight = function (actualWeight, shipWeight) {

        $(".progress-bar")
            .css("width", actualWeight + shipWeight + "%")
            .attr("aria-valuenow", actualWeight + shipWeight)
            .text(actualWeight + shipWeight + "%");

    };

    var addClassPlaced = function (cursor, shipDim, orientation) {
        cursor.addClass("placed");
        cursor.addClass("ui-state-highlight");
        var orientationClass = (orientation == HORIZONTAL) ? "noBorder-left" : "noBorder-top";
        appendRotationIcon(cursor, shipDim);
        cursor = cursorModule.getNextCursor(cursor, orientation);

        for (var i = 0; i < shipDim - 1; i++) {
            cursor.addClass("placed");
            cursor.addClass("ui-state-highlight");
            cursor.addClass(orientationClass);
            cursor = cursorModule.getNextCursor(cursor, orientation);
        }


    };

    var addClassOver = function (cursor, shipDim, orientation) {
        console.log("dimensione = " + shipDim);
        for (var i = 0; i < shipDim; i++) {
            cursor.addClass("over");
            cursor = cursorModule.getNextCursor(cursor, orientation);
        }
    };

    var removeClassOver = function (cursor, shipDim, orientation) {

        for (var i = 0; i < shipDim; i++) {
            cursor.removeClass("over");
            cursor = cursorModule.getNextCursor(cursor, orientation);
        }

    };

    var removeClassPlaced = function (cursor, shipDim, orientation) {

        for (var i = 0; i < shipDim; i++) {
            console.log("remove placed at :" + cursor.attr("id"));
            cursor.removeClass("placed ui-state-highlight noBorder-left noBorder-top");
            cursor = cursorModule.getNextCursor(cursor, orientation);
        }

    };

    var setRotateShipEvent = function (cursor, dim) {

        var id = cursor.attr("id");
        $('.rot' + id).click(function () {
            console.log("Trying to rotate");
            var cell = $(this).parent();
            var shipDim = cell.attr("data-dim");
            var orientation = cell.attr("data-orientation");
            var toggledOrientation = __toogleOrientation(cell.attr("data-orientation"));
            console.log("trying to rotate on cell " + cell.attr("id") + ", dimension = " + dim + ", new orientation = " + toggledOrientation);
            if (__isPlaceable(cursor, 0, 0, dim, toggledOrientation)) {

                console.log("rotate from " + orientation + " to " + toggledOrientation);
                removeClassPlaced(cell, dim, orientation);
                addClassPlaced(cell, dim, toggledOrientation);
                cell.attr("data-orientation", toggledOrientation);

            }
            else {
                alert("Attention, it's not possible to turn the ship...")
            }
        });
    };

    var appendRotationIcon = function (cursor, shipDim) {
        if ($("#" + cursor.attr("id")).children().length == 0) {
            cursor.append('<span class="rot' + cursor.attr("id") + ' glyphicon glyphicon-repeat pull-left rotateIcon" aria-hidden="true" ></span>');
            cursor.attr("data-dim", shipDim).attr("data-orientation", 0);
        }
    };


    return {
        initModule: init,
        isPlaceable: __isPlaceable,
        updateWeight: updateFleetWeight,
        addClassPlaced: addClassPlaced,
        addClassOver: addClassOver,
        removeClassOver: removeClassOver,
        removeClassPlaced: removeClassPlaced,
        setRotateEvent: setRotateShipEvent,
        appendRotaionIcon: appendRotationIcon
    }


})();