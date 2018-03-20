/**
 * Created by fabiodisabatino on 22/04/17.
 */

const HORIZONTAL    = 0;
const VERTICAL      = 1;

var moduleShipList = (function () {

    //------------------------------------private method----------------------------------------//

    var __initDragable = function () {
        $(".shipImg").draggable({
            addClasses: false,
            revert: true
        });

        $(".colGrid").droppable({

            over: function (event, ui) {
                var cursor = $(this);
                var id = parseInt(cursor.attr('id'));
                var dim = $(ui.draggable).attr("data-dim");
                cursor.addClass("over");
                moduleGridZone.addClassOver(cursor, dim, 0);

            },
            out: function (event, ui) {
                var cursor = $(this);
                var id = parseInt(cursor.attr('id'));
                var dim = $(ui.draggable).attr("data-dim");
                if (cursor.prev().hasClass("over")) {
                    //spostamento a sx
                    for (var i = 1; i < dim; i++) {
                        cursor = cursor.next();
                    }
                    cursor.removeClass("over");
                }
                else {
                    //spostamento a dx
                    cursor.removeClass("over");
                    for (i = 1; i < dim; i++) {
                        cursor.next().removeClass("over");
                        cursor = cursor.next();
                    }
                }


            },
            drop: function (event, ui) {

                var id = parseInt($(ui).attr('id'));
                var dim = $(ui.draggable).attr("data-dim");
                var position = parseInt($(this).attr('id'));
                var actualWeight = parseInt($('.progress-bar').attr("aria-valuenow"));
                var shipWeight = parseInt($(ui.draggable).parent().next().attr("data-weight"));

                var cursor = $(this);

                moduleGridZone.removeClassOver(cursor, dim, 0);

                if (moduleGridZone.isPlaceable(cursor, actualWeight, shipWeight, dim, HORIZONTAL)) {

                    //Place the ship in horizontal position
                    moduleGridZone.addClassPlaced(cursor, dim, 0);
                    //set rotate event on rotateIcon
                    //moduleGridZone.appendRotaionIcon(cursor, dim);
                    moduleGridZone.setRotateEvent(cursor, dim);
                    //update fleet weight
                    moduleGridZone.updateWeight(actualWeight, shipWeight);
                    //call the controller and add the ship
                    placeShipController.addShip(dim, position, "horizontal");

                }
                else {
                    alert("Attention ship too heavy or position not valid")
                }

            }
        });

    };


    //------------------------------------public method----------------------------------------//
    var init = function () {
        var numberShip = metaData.numberShip;
        var divider = 100 / numberShip;
        //chiedere al net Comunicator di farsi dare questi template
        $.get('../placeShipUI/module/moduleShipList/template/shipCard.tpl')
            .success(function (template) {
                for (var i = 0; i < numberShip; i++) {
                    var data = {
                        shipName: metaData.ship[i].substring(2),
                        shipImg: metaData.shipImg[i],
                        shipWeightImg: metaData.shipWeightImg[i],
                        shipWeight: metaData.shipWeight[i],
                        shipDim: metaData.ship[i].substring(0, 1)

                    };
                    var tpl = Mustache.to_html(template, data);
                    $(".listShip").append(tpl);
                }
                $(".shipCard").css("height", divider + "%");

                __initDragable();

            })
    };


    //return an object with only public method
    return {
        initModule: init
    }


})();