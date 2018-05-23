package com.debellonavali.Screens.Util;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class DragNDrop extends DragAndDrop {

    private static DragNDrop instance = null;

    private DragNDrop(){
        super();
    }

    public static DragNDrop getInstance() {
        if (instance == null) {
            instance = new DragNDrop();
        }
        return instance;
    }




}
