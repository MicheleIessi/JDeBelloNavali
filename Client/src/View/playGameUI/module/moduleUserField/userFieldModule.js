/**
 * Created by fabiodisabatino on 06/07/17.
 */


var moduleUserField=(function () {



    //------------------------------------private method----------------------------------------//


    var __drawShip=function (dim,position,orientation) {
        $userField=$(".userField");
        var row=position.x;
        var col=position.y;
        var $row=$userField.find("#"+row);
        var $cursor=$row.find("#"+row+col);

        for(var i=0;i<dim;i++){
            $cursor.addClass("shipHealty");
            if (orientation==0 || orientation==180)
            {
                $cursor.addClass("shipHorizontal");
                if(i==0)
                {
                    $cursor.addClass("shipHorizontalFirst")
                }
                else if (i==dim-1)
                {
                    $cursor.addClass("shipHorizontalLast")
                }
            }
            else
            {
                $cursor.addClass("shipVertical");
                if (orientation==90)
                {
                    if (i==0)
                    {
                        $cursor.addClass("shipVerticalFirst");
                    }
                    else if( i==dim-1)
                    {
                        $cursor.addClass("shipVerticalLast");
                    }
                }
                else
                {
                    if (i==0)
                    {
                        $cursor.addClass("shipVerticalLast");
                    }
                    else if( i==dim-1)
                    {
                        $cursor.addClass("shipVerticalFirst");
                    }
                }
            }
            $cursor= cursorModule.getNextCursor($cursor,orientation);



        }

    };

    var __init=function () {

        for (var i=0;i<GeneralShip.Ship.length;i++){

            var dim=GeneralShip.Ship[i].Dimension;
            var shipsX=GeneralShip.Ship[i].Istance;

            for  (var j=0;j<shipsX.length;j++){

                var shipX=shipsX[j];
                var position= shipX.Position;
                var orientation=shipX.Orientation;
                __drawShip(dim,position,orientation);
            }


        }

    };





    //------------------------------------public method----------------------------------------//

    return{

        initModule:__init
    }


})();
