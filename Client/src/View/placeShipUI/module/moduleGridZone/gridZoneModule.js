/**
 * Created by fabiodisabatino on 22/04/17.
 */
var moduleGridZone=(function () {


    var __isPlaceable= function ($cursor,actualWeight,shipWeight,shipDim,orientation){

        var $cursor=cursorModule.getNextCursor($cursor,orientation);
        for(var i=0;i<shipDim-1;i++){
            if ($cursor.hasClass("placed ui-state-highlight") || $cursor.attr("id")==undefined )
            {
                return false
            }

            $cursor=cursorModule.getNextCursor($cursor,orientation);
        }

        if(actualWeight+shipWeight<=100)
        {
            return true

        }
        else
        {
            return false
        }


    };

    var __toogleOrientation= function (orientation){

        if (orientation==0)
            return 90;
        else if (orientation==90)
            return 180;
        else if (orientation==180)
            return 270;
        else if (orientation==270)
            return 360;
    };

    //------------------------------------public method----------------------------------------//
    var init=function () {
        var heightText=$('.fleetWeight').height();
        var padding=(heightText-20)/20;
        $('.weightProgress').css("padding-top",padding);
    };

    var updateFleetWeight= function (actualWeight, shipWeight){

        $(".progress-bar")
            .css("width",actualWeight+shipWeight+"%")
            .attr("aria-valuenow",actualWeight+shipWeight)
            .text(actualWeight+shipWeight+"%");

    };

    var addClassPlaced= function ($cursor,shipDim,orientation) {
        $cursor.addClass( "placed ui-state-highlight " );
        $cursor=cursorModule.getNextCursor($cursor,orientation);

        for (var i=0;i<shipDim-2;i++)
        {
            $cursor.addClass( "placed ui-state-highlight noBorder-left" );
            $cursor=cursorModule.getNextCursor($cursor,orientation);
        }

        if (orientation==0)
        {
            $cursor.append('<span class="rot'+$cursor.attr("id")+' glyphicon glyphicon-repeat pull-right rotateIcon" aria-hidden="true" ></span>');
            $cursor.attr("data-dim",shipDim).attr("data-orientation",0);
        }
        $cursor.addClass("placed ui-state-highlight noBorder-left");




    };

    var addClassOver= function ($cursor, shipDim, orientation) {

        for (var i=0;i<shipDim;i++)
        {
            $cursor.addClass( "over" );
            $cursor=cursorModule.getNextCursor($cursor,orientation);
        }

    };

    var removeClassOver= function ($cursor,shipDim,orientation) {

            for (var i=0;i<shipDim;i++)
            {
                $cursor.removeClass("over");
                $cursor=cursorModule.getNextCursor($cursor,orientation);
            }



    };

    var removeClassPlaced= function ($cursor,shipDim,orientation) {

        for (var i =0;i<shipDim;i++)
        {
            console.log("remove placed at :"+$cursor.attr("id"));
            $cursor.removeClass("placed ui-state-highlight noBorder-left");
            $cursor=cursorModule.getPrevCursor($cursor,orientation);

        }
    };

    var setRotateShipEvent=function ($cursor,dim) {

        var $lastCursor=cursorModule.getLastCursor($cursor,dim);
        var id=$lastCursor.attr("id");
        $('.rot'+id).click(function () {

            var $cursor=$(this).parent();
            var shipDim= $cursor.attr("data-dim");
            var orientation= $cursor.attr("data-orientation");
            var toogledOrientation=__toogleOrientation($cursor.attr("data-orientation"));
            if(__isPlaceable($cursor,0,0,shipDim,toogledOrientation))
            {

                console.log("rotate from "+
                    orientation+ " at "+toogledOrientation);
                removeClassPlaced($cursor,shipDim,orientation);
                addClassPlaced($cursor,shipDim,toogledOrientation);
                if (toogledOrientation==360)
                {
                    $cursor.attr("data-orientation",0);
                }
                else
                    $cursor.attr("data-orientation",toogledOrientation);

            }
            else
            {
                alert("Attention, it's not possible to turn the ship...")
            }

        })
    };




    return {
        initModule:init,
        isPlaceable: __isPlaceable,
        updateWeight:updateFleetWeight,
        addClassPlaced:addClassPlaced,
        addClassOver:addClassOver,
        removeClassOver:removeClassOver,
        removeClassPlaced:removeClassPlaced,
        setRotateEvent:setRotateShipEvent

    }


})();