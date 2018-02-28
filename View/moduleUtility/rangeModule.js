/**
 * Created by fabiodisabatino on 18/07/17.
 */


var rangeModule=(function () {
    //------------------------------------private method----------------------------------------//




    var getStrategy=function (weaponName,$cursor) {

     weaponName=weaponName.toUpperCase();
        if (weaponName=="W1"){
            return getStrategyW1($cursor);
        }
        else if (weaponName=="W2"){
            return getStrategyW2($cursor);
        }
        else if (weaponName=="W3"){

            return getStrategyW3($cursor);
        }
        else if (weaponName=="W4"){

            return getStrategyW4($cursor);
        }


    };




    var getStrategyW1=function ($cursor) {


        return [$cursor]

    };

    var getStrategyW2=function ($cursor) {

        var squaresInvolved=new Array();
        squaresInvolved.push($cursor);
        for(var i=0;i<2;i++){
            $cursor=cursorModule.getNextCursor($cursor,0);
            squaresInvolved.push($cursor);
        }
        return squaresInvolved

    };


    var getStrategyW3=function ($cursor) {

        var squaresInvolved=new Array();
        squaresInvolved.push($cursor);
        var idSecondo=parseInt($cursor.attr("id"))-10;
        squaresInvolved.push($("#"+idSecondo));
        var idTerzo=parseInt($cursor.attr("id"))+1;
        squaresInvolved.push($("#"+idTerzo));
        var idQuart=idSecondo+1;
        squaresInvolved.push($("#"+idQuart));


        console.log(squaresInvolved);



        return squaresInvolved



    };

    var getStrategyW4=function () {

    };


    var getStrategyW5=function () {


    };










    //------------------------------------public method----------------------------------------//

    return{

        getSquareInvolved:getStrategy

    }






})();