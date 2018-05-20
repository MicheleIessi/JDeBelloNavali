package com.debellonavali.PlaceShip.Stage.Layout1;


import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;

import com.debellonavali.PlaceShip.CellGrid;
import com.debellonavali.PlaceShip.ConstantsPlaceShips;

import com.debellonavali.PlaceShip.Stage.PlaceShipStage;
import com.debellonavali.PlaceShip.Stage.zoneStage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShipListStage extends zoneStage {


    private DragAndDrop dragAndDrop;

    private ArrayList<Table> ships;
    private LabelStyle shipLabelStyle;
    private BitmapFont shipFont;
    private Label shipLabel;
    private String civilization;


    public ShipListStage(zoneStage parent) {
        super(parent);
        zoneTable.pad(0);
        ships= new ArrayList<>();
        dragAndDrop = new DragAndDrop();
        initFonts();

    }

    private void initFonts() {
        FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal(ConstantsPlaceShips.SHIP_NAME_FONT));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size=50;
        parameter.color=Color.BLACK;
        shipFont= generator.generateFont(parameter);

        shipLabelStyle = new LabelStyle();
        shipLabelStyle.font = shipFont;
        shipLabelStyle.fontColor = new Color(.1f, .1f, .1f, 1);


    }

    @Override
    public void setUpLayout() {

        civilization= ((PlaceShipStage)parent).getCivilization();
        Table temp;
        FileHandle [] files = Gdx.files.internal(ConstantsPlaceShips.FLEET_PICTURES_PATH+civilization+"/entire").list();
        int count =files.length;


        for(FileHandle file: files) {


            temp= new Table();
            System.out.println(file.nameWithoutExtension());
            final  Map<String, String> map= packSihpInfo(file.nameWithoutExtension());

            Image ship= new Image( new Texture(file));

            temp.add(ship).width(200).height(200);
            temp.row();
            shipLabel = new Label(map.get("name"), shipLabelStyle);
            shipLabel.setAlignment(Align.center);
            temp.add(shipLabel).height(5).width(ConstantsPlaceShips.SHIP_ZONE_WIDTH);
            temp.setBounds(temp.getX(),temp.getY(),temp.getWidth(),temp.getHeight());
            ships.add(temp);



            dragAndDrop.addSource(new Source(ship){
                public Payload dragStart (InputEvent event, float x, float y, int pointer) {
                    Payload payload = new Payload();

                    payload.setObject(map);
                    Image toDrag=new Image( ship.getDrawable());
                    toDrag.setSize(70,70);
                    payload.setDragActor(toDrag);
                    return payload;
                }
            });

        }
        for (Table t:ships) {
            zoneTable.add(t).width(ConstantsPlaceShips.SHIP_ZONE_WIDTH).height(ConstantsPlaceShips.SHIP_ZONE_HEIGHT/count);
            zoneTable.row();
        }

        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.SHIP_ZONE_WIDTH).height(ConstantsPlaceShips.SHIP_ZONE_HEIGHT);



    }

    public DragAndDrop getDragAndDrop() {
        return this.dragAndDrop;
    }


    private Map packSihpInfo(String fileName){
        String [] shipInfo=fileName.split("-");
        Map<String, String> map = new HashMap<>();

        map.put("civil",civilization);
        map.put("name",shipInfo[0]);
        map.put("dim",shipInfo[1]);
        map.put("weight",shipInfo[2]);




        return map;
    }


}
