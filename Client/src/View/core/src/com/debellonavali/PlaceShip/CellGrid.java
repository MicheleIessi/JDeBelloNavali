package com.debellonavali.PlaceShip;

import java.awt.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.debellonavali.PlaceShip.Stage.Layout1.GtStageDescendant.GridStage;

public class CellGrid extends Actor {
    //Variables for water animation
    private Animation water;
    private TextureRegion[] waterTextures;
    float waterStateTime = 0f;
    private CellState state;
    //CellGrid select overlay texture
    Texture selected = new Texture(Gdx.files.internal(ConstantsPlaceShips.SELECTED_TEXTURE_PATH));
    //Ship contained
    public Image shipImg;
    //Sizing for the cell
    private int x, y;
    public static int cellWidth =  (ConstantsPlaceShips.GRID_ZONE_WIDTH/13);

    private GridStage parentStage;


    public enum CellState {
        EMPTY, NOT_EMPTY
    }
    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public CellGrid(int x, int y, GridStage stage) {
        this.x = x;
        this.y = y;
        this.parentStage=stage;
        this.state=CellState.EMPTY;
        super.setName(x+","+y);

        setDimensions();
        setUpWaterAnimation();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawWater(batch);
        if(shipImg!=null) {
            //batch.draw(ship.getTexture(position), getX(), getY(), cellWidth, cellWidth);
        }
        if(state!=CellState.EMPTY ) {
            batch.draw(selected, getX(), getY(), cellWidth, cellWidth);
        }
    }

    public boolean hasShip() {
        return (shipImg != null);
    }


    public void dropShip(Payload ship){
        System.out.println(ship);
    }

    /**
     * Draws water "sprite"
     */
    public void drawWater(Batch batch) {
        waterStateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame =(TextureRegion)water.getKeyFrame(waterStateTime, true);
        for(float i = 0; i < CellGrid.cellWidth - currentFrame.getRegionWidth(); i+=currentFrame.getRegionWidth()) {
            for(float j = 0; j< CellGrid.cellWidth - currentFrame.getRegionHeight(); j+=currentFrame.getRegionHeight()) {
                batch.draw(currentFrame, getX()+i, getY()+j);
            }
        }
    }

    /**
     * Sets up water texture and animation
     */
    private void setUpWaterAnimation() {
        //Combines the water images into a TextureRegion array
        waterTextures = new TextureRegion[32];
        for(int i=0; i<32; i++) {
            //Uses entire texture as "region" since they aren't on combined sprite sheet
            waterTextures[i] = new TextureRegion(
                    new Texture(Gdx.files.internal(ConstantsPlaceShips.WATER_TEXTURE+i+".png")),0, 0, cellWidth-1,cellWidth-1);

        }
        //Sets up the background animation at 32 fps
        water = new Animation(1/32f, waterTextures);
    }

    /**
     * Sets the dimensions
     */
    private void setDimensions() {
        //Sets up actor on the stage
        super.setBounds((x*cellWidth), (y*cellWidth), cellWidth , cellWidth);
    }


    public String getPosition(){
        return ""+x+" "+y;
    }

    public void showPreview(int dim){

        this.parentStage.showPreview(x,y,dim);

    }
    public void hidePreview(int dim) {

        this.parentStage.hidePreview();
    }


}
