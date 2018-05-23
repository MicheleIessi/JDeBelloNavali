package com.debellonavali.Screens.BattlePhase.Stages.Layout1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.debellonavali.Classes.Model.Ship;
import com.debellonavali.Classes.Model.Weapon;
import com.debellonavali.Screens.BattlePhase.Stages.ConstantsBattlePhase;
import com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips;
import com.debellonavali.Screens.Util.DragNDrop;
import com.debellonavali.Screens.zoneStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FleetListStage extends zoneStage {


    private DragNDrop dragAndDrop;

    private ArrayList<Table> ships;

    public FleetListStage(zoneStage parent){
        super(parent);
        zoneTable.pad(0);
        zoneTable.setDebug(true);
        ships=new ArrayList<>();
        dragAndDrop= DragNDrop.getInstance();

    }



    @Override
    public void setUpLayout() {


        super.setZoneTableDimension(ConstantsBattlePhase.FLEETLIST_TABLE_WIDTH,ConstantsBattlePhase.FLEETLIST_TABLE_HEIGHT);
    }

    public void setFleet(HashMap<Integer,Ship> fleet) {

        //Define all the variable
        Table shipItem;
        Table shipInfo;
        Table weaponsItems;
        Label shipLabel;
        BitmapFont shipFont;
        Label.LabelStyle shipLabelStyle;

        Image shipImg;
        Texture shipTex;

        FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.SHIP_NAME_FONT));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        parameter.color=Color.BLACK;
        shipFont= generator.generateFont(parameter);

        shipLabelStyle = new Label.LabelStyle();
        shipLabelStyle.font = shipFont;
        shipLabelStyle.fontColor = new Color(.1f, .1f, .1f, 1);


        int shipCardHeight=ConstantsBattlePhase.FLEETLIST_TABLE_HEIGHT/fleet.entrySet().size();

        //TODO: come ottengo questa informazione??
        String civil="RomanFleet";

        //Add each ship placed with its info
        for(Map.Entry<Integer,Ship> entry : fleet.entrySet()) {
            int shipId = entry.getKey();
            Ship ship = entry.getValue();


            shipItem= new Table();
            shipItem.pad(0);
            shipInfo= new Table();
            shipInfo.pad(0);
            weaponsItems= new Table();
            weaponsItems.pad(0);

            shipTex= new Texture(Gdx.files.internal(
                    ConstantsPlaceShips.FLEET_PICTURES_PATH+civil+"/entire/"+ship.getShipName()+"-"+ship.getDimension()+"-"+ship.getShipWeight()+".png"));
            shipImg=new Image(shipTex);
            shipInfo.add(shipImg).expand().left();
            shipInfo.row();
            shipLabel = new Label(ship.getShipName(), shipLabelStyle);
            shipLabel.setAlignment(Align.left);
            shipInfo.add(shipLabel);
            shipItem.add(shipInfo).width(ConstantsBattlePhase.FLEETLIST_TABLE_WIDTH/2).height(shipCardHeight);

            int weaponCardHeight=shipCardHeight/ship.getWeapons().size();

            //add weapons
            for (Map.Entry<Integer,Weapon> weaponEntry: ship.getWeapons().entrySet()){

                Table temp= new Table();

                Weapon w= weaponEntry.getValue();

                Image weaponImage= new Image(new Texture(
                        Gdx.files.internal(ConstantsPlaceShips.WEAPON_PICTURE_PATH+civil+"/"+w.getWeaponName()+".png")));

                temp.add(new Label(w.getWeaponName(),shipLabelStyle));
                temp.add(weaponImage).width(50).height(50);
                weaponsItems.add(temp).width(ConstantsBattlePhase.FLEETLIST_TABLE_WIDTH/2).height(weaponCardHeight);

                //Set this weapon as draggable
                setUpDraggable(shipId,w,weaponImage);

                weaponsItems.row();

            }

            shipItem.add(weaponsItems);
            ships.add(shipItem);




        }

        for (Table t: ships){
            zoneTable.add(t).width(ConstantsBattlePhase.FLEETLIST_TABLE_WIDTH).height(ConstantsBattlePhase.FLEETLIST_TABLE_HEIGHT/ships.size());
            zoneTable.row();
        }
    }


    private void setUpDraggable(int shipID,Weapon weapon,Image weaponImage){

        dragAndDrop.addSource(new DragAndDrop.Source(weaponImage){
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                HashMap<String,Integer> infoPack= new HashMap<>();

                infoPack.put("shipID",shipID);
                infoPack.put("weaponID", weapon.getWeaponID());

                payload.setObject(infoPack);
                Image toDrag=new Image( weaponImage.getDrawable());
                toDrag.setSize(70,70);
                payload.setDragActor(toDrag);
                return payload;
            }
        });
    }



}
