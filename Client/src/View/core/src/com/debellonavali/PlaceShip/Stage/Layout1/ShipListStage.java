package com.debellonavali.PlaceShip.Stage.Layout1;


import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

import java.util.ArrayList;

public class ShipListStage extends zoneStage {

    private  TweenManager tweenManager;
    private DragAndDrop dragAndDrop;
    private TextureAtlas atlas;
    private Skin skin;
    private ArrayList<Table> ships;
    private LabelStyle shipLabelStyle;
    private BitmapFont shipFont;
    private Label shipLabel;

    public ShipListStage(zoneStage parent) {
        super(parent);
        zoneTable.pad(0);
        ships= new ArrayList<>();

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

        String packageCivil= ((PlaceShipStage)parent).getCivilization();
        Table temp;
        FileHandle [] files = Gdx.files.internal(ConstantsPlaceShips.FLEET_PICTURES_PATH+packageCivil+"/entire").list();
        int count =files.length;
        for(FileHandle file: files) {

            temp= new Table();
            Image ship= new Image( new Texture(file));
            ship.setName(file.nameWithoutExtension());
            temp.add(ship).width(200).height(200);
            temp.row();
            shipLabel = new Label(file.nameWithoutExtension().split("-")[0], shipLabelStyle);
            shipLabel.setAlignment(Align.center);
            temp.add(shipLabel).height(5).width(ConstantsPlaceShips.SHIP_ZONE_WIDTH);
            temp.setBounds(temp.getX(),temp.getY(),temp.getWidth(),temp.getHeight());
            ships.add(temp);

        }
        for (Table t:ships) {
            zoneTable.add(t).width(ConstantsPlaceShips.SHIP_ZONE_WIDTH).height(ConstantsPlaceShips.SHIP_ZONE_HEIGHT/count);
            zoneTable.row();
        }

        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.SHIP_ZONE_WIDTH).height(ConstantsPlaceShips.SHIP_ZONE_HEIGHT);
        tweenManager = new TweenManager();

    }

    public void setUpDragAndDrop() {


        Table playerGridTable=((PlaceShipStage)parent).getGtStage().getGridTable();

        DragAndDrop dragAndDrop = new DragAndDrop();

        for (Table t: ships) {
            for ( Cell c:t.getCells()
                 ) {
                //check the actor ain't the label
                if (c.getActor().getClass()!=Label.class){
                    dragAndDrop.addSource(new Source(c.getActor()){
                        public Payload dragStart (InputEvent event, float x, float y, int pointer) {
                            Payload payload = new Payload();
                            payload.setObject(c.getActor().getName());
                            payload.setDragActor(new Image(((Image) c.getActor()).getDrawable()));
                            return payload;
                        }
                    });
                }


            }

        }

        ArrayList<ArrayList<CellGrid>> gridArray=((PlaceShipStage)parent).getGtStage().getGridArray();

        for (ArrayList<CellGrid> cells:gridArray
             ) {
            for (CellGrid cell: cells){
                dragAndDrop.addTarget(new Target(cell) {
                    public boolean drag (Source source, Payload payload, float x, float y, int pointer) {

                        return true;
                    }

                    public void reset (Source source, Payload payload) {
                        getActor().setColor(Color.WHITE);
                    }

                    public void drop (Source source, Payload payload, float x, float y, int pointer) {
                        System.out.println("Accepted: " + payload.getObject());
                         CellGrid cellTarget=(CellGrid)getActor();
                         cellTarget.dropShip(payload);
                         System.out.println(cellTarget.getPosition());

                    }
                });
            }

        }

       /* dragAndDrop.addTarget(new Target(invalidTargetImage) {
            public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.RED);
                return false;
            }

            public void reset (Source source, Payload payload) {
                getActor().setColor(Color.WHITE);
            }

            public void drop (Source source, Payload payload, float x, float y, int pointer) {
                ((CellGrid) getActor()).setSelected(true);
            }
        });*/
    }

}
