/**
 * Created by fabiodisabatino on 22/04/17.
 */

var HORIZONTAL = 0;
var VERTICAL = 1;

var moduleGridZone = (function () {

    /**
     * Checks if the ship is placeable on the battlefield.
     * @param cursor The tile which the ship is being placed on
     * @param actualWeight The current weight of ships on the battlefield
     * @param shipWeight The weight of the ship being placed
     * @param shipDim The dimension (in tiles) of the ship
     * @param orientation The orientation of the ship
     * @returns {boolean} true if the ship is placeable, false otherwise
     * @private
     */
    var __isPlaceable = function (cursor, actualWeight, shipWeight, shipDim, orientation) {

        for (var i = 0; i < shipDim; i++) {
            cursor = cursorModule.getNextCursor(cursor, orientation);
            if (cursor.hasClass("placed ui-state-highlight") || cursor.attr("id") == undefined) {
                return false;
            }
        }
        return (actualWeight + shipWeight) <= 100;
    };

    /**
     * Returns the opposite orientation with respect to the one passed as parameter.
     * @param orientation The orientation to be toggled
     * @returns {number} The new orientation
     * @private
     */
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

    /**
     * Updates CSS properties related to fleet weight
     * @param actualWeight The combined weight of ships on the battlefield
     * @param shipWeight The weight of the ship being placed
     */
    var updateFleetWeight = function (actualWeight, shipWeight) {

        $(".progress-bar")
            .css("width", actualWeight + shipWeight + "%")
            .attr("aria-valuenow", actualWeight + shipWeight)
            .text(actualWeight + shipWeight + "%");

    };

    /**
     * Adds CSS classes to visualize the placement
     * @param cursor The tile which the ship is being placed on
     * @param shipDim The dimension (in tiles) of the ship
     * @param orientation The orientation of the ship
     */
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

    /**
     * Dual of method addClassPlaced
     * @param cursor The tile which the ship is being placed on
     * @param shipDim The dimension (in tiles) of the ship
     * @param orientation The orientation of the ship
     */
    var removeClassPlaced = function (cursor, shipDim, orientation) {

        for (var i = 0; i < shipDim; i++) {
            console.log("remove placed at :" + cursor.attr("id"));
            cursor.removeClass("placed ui-state-highlight noBorder-left noBorder-top");
            cursor = cursorModule.getNextCursor(cursor, orientation);
        }

    };

    /**
     * Adds CSS classes to visualize the dragging of the ship on the battlefield
     * @param cursor The tile which the ship is being dragged on
     * @param shipDim The dimension (in tiles) of the ship
     * @param orientation The orientation of the ship
     */
    var addClassOver = function (cursor, shipDim, orientation) {
        for (var i = 0; i < shipDim; i++) {
            cursor.addClass("over");
            cursor = cursorModule.getNextCursor(cursor, orientation);
        }
    };

    /**
     * Dual of method addClassOver
     * @param cursor The tile which the ship is being dragged on
     * @param shipDim The dimension (in tiles) of the ship
     * @param orientation The orientation of the ship
     */
    var removeClassOver = function (cursor, shipDim, orientation) {
        for (var i = 0; i < shipDim; i++) {
            cursor.removeClass("over");
            cursor = cursorModule.getNextCursor(cursor, orientation);
        }
    };

    /**
     * Adds rotation functionality to the placed ship
     * @param cursor The left-uppermost tile which the ship has been placed on
     * @param dim The dimension (in tiles) of the ship
     */
    var setRotateShipEvent = function (cursor, dim) {

        var id = cursor.attr("id");
        $('.rot' + id).click(function () {
            var cell = $(this).parent();
            var shipDim = cell.attr("data-dim");
            var orientation = cell.attr("data-orientation");
            var toggledOrientation = __toogleOrientation(cell.attr("data-orientation"));
            console.log("trying to rotate on cell " + cell.attr("id") + ", dimension = " + dim + ", new orientation = " + toggledOrientation);
            if (__isPlaceable(cursor, 0, 0, shipDim, toggledOrientation)) {

                console.log("rotate from " + orientation + " to " + toggledOrientation);
                removeClassPlaced(cell, dim, orientation);
                addClassPlaced(cell, dim, toggledOrientation);
                cell.attr("data-orientation", toggledOrientation);

            }
            else {
                console.log("Impossible rotation");
            }
        });
    };

    /**
     * Appends a rotation icon to the tile
     * @param cursor The tile which the icon must be appended to
     * @param shipDim The dimension of the ship
     */
    var appendRotationIcon = function (cursor, shipDim) {
        var cursorID = cursor.attr("id");
        if ($("#" + cursor.attr("id")).children().length == 0) {
            spanName = "rot" + cursorID;
            cursor.append('<span class = ' + spanName + '></span>');
            $('.' + spanName)
                .addClass("glyphicon")
                .addClass("glyphicon-repeat")
                .addClass("pull-left")
                .addClass("rotateIcon");
            $('.rot').attr("aria-hidden", true);
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
        setRotateEvent: setRotateShipEvent
    }


})();