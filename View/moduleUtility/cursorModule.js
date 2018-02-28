/**
 * Created by fabiodisabatino on 06/07/17.
 */


var cursorModule=(function () {

    //------------------------------------private method----------------------------------------//

    __nextCursor=function ($cursor,orientation) {
        if (orientation==0 )
        {
            return $cursor.next();

        }
        else if (orientation == 90){

            var idCursor= $cursor.attr("id");
            var idnext=idCursor-10;
            if (idnext<10)
            {
                idnext="0"+idnext;
            }
            return $("#"+idnext);
        }
        else if(orientation==180){
            return $cursor.next();

        }
        else if (orientation==270){
            var idCursor= $cursor.attr("id");
            var idnext=parseInt(idCursor)+10;
            return $("#"+idnext);
        }
        else if(orientation==360)
        {
            return $cursor.prev();
        }




    };

    __prevCursor=function ($cursor, orientation) {

        if (orientation==0 || orientation==360)
        {
            return $cursor.prev();
        }
        else if( orientation==90){
            var idCursor= $cursor.attr("id");
            var idnext=idCursor-10;
            if (idnext<10)
            {
                idnext="0"+idnext;
            }
            return $("#"+idnext);

        }
        else if (orientation==180){
            return $cursor.next();

        }
        else if (orientation==270){
            var idCursor= $cursor.attr("id");
            var idnext=parseInt(idCursor)+10;
            return $("#"+idnext);
        }


    };


    __getLast=function ($cursor,dim) {

        for(var i=0;i<dim-1;i++){

            $cursor=__nextCursor($cursor,0);

        }
        return $cursor;
    };




    var __init=function () {

    };

    //------------------------------------public method----------------------------------------//

    return{

        initModule:__init,
        getNextCursor:__nextCursor,
        getPrevCursor:__prevCursor,
        getLastCursor:__getLast
    }







})();