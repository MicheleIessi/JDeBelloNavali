package com.debellonavali.PlaceShip;

import java.awt.*;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.debellonavali.PlaceShip.Stage.Layout1.GtStageDescendant.GridStage;
import com.sun.javafx.collections.MappingChange;

public class CellGrid extends Actor {
    private Batch batch;
    //Variables for water animation
    private Animation water;
    private TextureRegion[] waterTextures;
    float waterStateTime = 0f;
    private CellState state;
    private previewStyle style;
    //CellGrid select overlay texture
    private boolean showPreview=false;

    private Image notEmptyTexture ;
    private Image emptyTexture ;
    private Image emptyInvalidTexture ;

    //Ship contained
    public Image shipImg;
    //Sizing for the cell
    private int x, y;
    public static int cellWidth =  (ConstantsPlaceShips.GRID_ZONE_WIDTH/13);

    private GridStage parentStage;



    public enum CellState {
        EMPTY, NOT_EMPTY
    }
    public enum previewStyle{
        VALID,NOT_VALID
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public previewStyle getStyle() {
        return style;
    }

    public void setStyle(previewStyle style) {
        this.style = style;
    }

    public CellGrid(int x, int y, GridStage stage) {
        this.x = x;
        this.y = y;
        this.parentStage=stage;
        this.state=CellState.EMPTY;
        super.setName(x+","+y);

        notEmptyTexture= new Image (new Texture(Gdx.files.internal(ConstantsPlaceShips.NOT_EMPTY_TEXTURE_PATH)));
        emptyTexture= new Image(new Texture(Gdx.files.internal(ConstantsPlaceShips.EMPTY_TEXTURE_PATH)));
        emptyInvalidTexture= new Image(new Texture(Gdx.files.internal(ConstantsPlaceShips.EMPTY_INVALID_TEXTURE_PATH)));
        setDimensions();
        setUpWaterAnimation();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawWater(batch);
        if(shipImg!=null) {
            shipImg.getDrawable().draw(batch, getX(),getY(),cellWidth,cellWidth);
        }
        if(showPreview) {
            if (state==CellState.EMPTY ){
                if (style==previewStyle.VALID)
                    emptyTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);
                else
                    emptyInvalidTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);
            }
            else
                notEmptyTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);

        }


    }

    public boolean hasShip() {
        return (shipImg != null);
    }


    public void dropShip(Map pack,int part){
        System.out.println(pack);
        shipImg= new Image(new Texture(Gdx.files.internal(
                ConstantsPlaceShips.FLEET_PICTURES_PATH+pack.get("civil")+"/divided/"+pack.get("name")+"/"+part+".png")));

        setState(CellState.NOT_EMPTY);


    }

    /**
     * Draws water "sprite"
     */
    public void drawWater(Batch batch) {
        waterStateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame =(TextureRegion)water.getKeyFrame(waterStateTime, true);
        for(float i = 0; i < CellGrid.cellWidth - currentFrame.getRegionWidth(); i+=currentFrame.getRegionWidth()) {
            for(float j = 0; j< CellGrid.cellWidth - currentFrame.getRegionHeight(); j+=currentFrame.getRegionHeight()) {
                batch.draw(currentFrame, getX()+i, getY()+j,cellWidth-1,cellWidth-1);
            }
        }
    }

    /**
     * Sets up water texture and animation
     */
    private void setUpWaterAnimation() {
        //Combines the water images into a TextureRegion array
        waterTextures = new TextureRegion[25];
        for(int i=0; i<25; i++) {
            //Uses entire texture as "region" since they aren't on combined sprite sheet
            waterTextures[i] = new TextureRegion(
                    new Texture(Gdx.files.internal(ConstantsPlaceShips.WATER_TEXTURE+i+".png")),0, 0, cellWidth-1,cellWidth-1);

        }
        //Sets up the background animation at 32 fps
        water = new Animation(1/2f, waterTextures);
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


    public int getXCoordinate() {
        return x;
    }


    public int getYCoordinate() {
        return y;
    }

    public void showPreview(previewStyle s){

       this.showPreview=true;
       this.setStyle(s);


    }

    public void hidePreview(){
        this.showPreview=false;
        this.setStyle(previewStyle.NOT_VALID);
    }
}
