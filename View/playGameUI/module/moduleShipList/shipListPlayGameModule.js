/**
 * Created by Silvia on 11/05/2017.
 */

var moduleShipListPlayGame=(function () {

    var __$squaresInvolvedPreview=[];

    //------------------------------------private method----------------------------------------//

    var __templateBox="BoxShipList";
    var __templateWeapon='weaponShipList';
    var __positionAttack="";
    var __idShipUsed="";
    var __idWeaponUsed="";

    var __getTemplate=function (template) {

        return $.get('module/moduleShipList/'+template+'.tpl')
    };

    var __initDragable=function () {


        $(".imgWeapon").draggable({
            addClasses: false,
            revert:true
        });

        $(".cellEnemy").droppable({
            over:function (event,ui) {
                $(".cellEnemy").removeClass("overAttack");
                var $cursor=$(this);
                var weaponName=$(ui.draggable).attr("data-name");
                __$squaresInvolvedPreview=rangeModule.getSquareInvolved(weaponName,$cursor);
                for (var i=0;i<__$squaresInvolvedPreview.length;i++){
                    $square=__$squaresInvolvedPreview[i];
                    console.log("add :"+$square.attr("id"));
                    $square.addClass("overAttack");

                }

            },
            out:function (event,ui) {

            },
            drop:function (event,ui) {

                $(".cellEnemy").removeClass("overAttack");
                alert("wait response to the attack...");
                /*
                  far partire chiamata ajax al server attack(shipID, weaponID, position) e
                  aspettare array di square hit

                  __viewReady(__idShipUsed,__idWeaponUsed,__positionAttack);
                 */




            }


        })
    };

    var __init= function(){
            var numberShip= GeneralShip.numberOfShip;
            var divider= 100/numberShip;

            $.when(__getTemplate(__templateBox),__getTemplate(__templateWeapon)).
                done(function(tplBox,tplWeapon){
                    for (var i=0;i<GeneralShip.Ship.length;i++)
                    {

                            var shipXGeneral=GeneralShip.Ship[i];

                            var shipsX=shipXGeneral.Istance;

                            for (var j=0;j<shipsX.length;j++){

                                var shipX=shipsX[j];
                                var ship={
                                    shipName:shipXGeneral.ShipName,
                                    shipImg:shipXGeneral.Img,
                                    shipID:shipX.ShipID
                                };

                                var templateBox= Mustache.to_html(tplBox[0], ship);
                                $(".fleetList").append(templateBox);
                                $(".cardShip").css("height", divider + "%");
                                var shipXWeapon=shipX.Weapon;


                                for (var y=0;y<shipXWeapon.length;y++)
                                {
                                    var weapon={
                                        WeaponName:shipXWeapon[y].WeaponName,
                                        TimeReload:shipXWeapon[y].TimeReload,
                                        Ammo:shipXWeapon[y].Ammo,
                                        Img:shipXWeapon[y].Img
                                    };


                                    var templateWeapon=Mustache.to_html(tplWeapon[0],weapon);
                                    $("#"+ship.shipID).find(".weaponShip").append(templateWeapon);
                                }

                                var dividerWeapon=100/shipXWeapon.length;
                                $(".rowWeapon").css("height",dividerWeapon+"%");

                                var height=parseInt($(".weaponItem").css("height"));
                                var marginTop= (height-20)/2;

                                $(".weaponItemRow").css("margin-top",marginTop);


                            }





                    }

                        //init dragable
                        __initDragable();
                });








    };

    var __viewReady= function () {

        return $.get();

    };

    //------------------------------------public method----------------------------------------//

    return{

        initModule:__init
    }


})();
