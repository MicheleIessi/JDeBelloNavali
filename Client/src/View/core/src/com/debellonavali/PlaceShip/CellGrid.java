package com.debellonavali.PlaceShip;

import java.awt.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

public class CellGrid extends Actor {
    //Variables for water animation
    private Animation water;
    private TextureRegion[] waterTextures;
    float waterStateTime = 0f;
    //CellGrid select overlay texture
    Texture selected = new Texture(Gdx.files.internal(ConstantsPlaceShips.SELECTED_TEXTURE_PATH));
    //Ship contained
    public Image shipImg;
    //Sizing for the cell
    private int x, y;
    public static int cellWidth =  (ConstantsPlaceShips.GRID_ZONE_WIDTH/13);
    //Position relative to ship (zero indexed)
    private int position;


    private boolean isSelected = false;

    public enum CellState {
        WATER, DAMAGED, HEALTHY
    }
    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    CellState state = CellState.WATER;

    public CellGrid(int x, int y) {
        this.x = x;
        this.y = y;
        setDimensions();
        //Used to detect actor based on x,y string
        super.setName(x+","+y);
        setUpWaterAnimation();
        setUpListener();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawWater(batch);
        if(shipImg!=null) {
            //batch.draw(ship.getTexture(position), getX(), getY(), cellWidth, cellWidth);
        }
        if(isSelected ) {
            batch.draw(selected, getX(), getY(), cellWidth, cellWidth);
        }
    }

    public boolean hasShip() {
        return (shipImg != null);
    }

    /**
     * Damages this cell
     * @return true if the cell became damaged
     */
    public boolean damage() {
        if(state == CellState.HEALTHY) {
            state = CellState.DAMAGED;
            return true;
        }
        return false;
    }

    /**
     * Sets up listener
     */
    private void setUpListener() {
        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isSelected = !isSelected;
                return true;
            }
        });

    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public void updateDimensions() {
        super.setWidth(cellWidth);
        super.setHeight(cellWidth);
    }

    public String getPosition(){
        return ""+x+" "+y;
    }
}
