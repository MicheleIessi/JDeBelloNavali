package com.debellonavali.PlaceShip;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.debellonavali.PlaceShip.Ship;


import com.badlogic.gdx.scenes.scene2d.Actor;

public class Cell extends Actor {
    //Variables for water animation
    private Animation water;
    private TextureRegion[] waterTextures;
    float waterStateTime = 0f;
    //Cell select overlay texture
    Texture selected = new Texture(Gdx.files.internal(ConstantsPlaceShips.SELECTED_TEXTURE_PATH));
    //Ship contained
    public Ship ship;
    //Sizing for the cell
    private int x, y;
    public static int cellWidth = Gdx.graphics.getHeight()/5;
    //Position relative to ship (zero indexed)
    private int position;
    private boolean isSelected = false;

    public enum CellState {
        WATER, DAMAGED, HEALTHY
    }
    public CellState getState() {
        return state;
    }
    CellState state = CellState.WATER;

    public Cell(int x, int y) {
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
        if(ship!=null) {
            //batch.draw(ship.getTexture(position), getX(), getY(), cellWidth, cellWidth);
        }
        if(isSelected) {
            batch.draw(selected, getX(), getY(), cellWidth, cellWidth);
        }
    }

    public boolean hasShip() {
        return (ship != null);
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

    /**
     * Draws water "sprite"
     */
    public void drawWater(Batch batch) {
        waterStateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame =(TextureRegion)water.getKeyFrame(waterStateTime, true);
        for(float i = 0; i < Cell.cellWidth - currentFrame.getRegionWidth(); i+=currentFrame.getRegionWidth()) {
            for(float j=0; j< Cell.cellWidth - currentFrame.getRegionHeight(); j+=currentFrame.getRegionHeight()) {
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
            waterTextures[i] = new TextureRegion(new Texture(Gdx.files.internal(ConstantsPlaceShips.WATER_TEXTURE+i+".png")), 0, 0, 32, 32);
        }
        //Sets up the background animation at 32 fps
        water = new Animation(1/32f, waterTextures);
    }

    /**
     * Sets the dimensions
     */
    private void setDimensions() {
        //Sets up actor on the stage
        super.setBounds(x*cellWidth, y*cellWidth, cellWidth, cellWidth);
    }

    public void updateDimensions() {
        super.setWidth(cellWidth);
        super.setHeight(cellWidth);
    }

}
