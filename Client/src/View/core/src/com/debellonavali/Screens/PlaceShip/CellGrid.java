package com.debellonavali.Screens.PlaceShip;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.debellonavali.Screens.BattlePhase.Stages.ConstantsBattlePhase;
import com.debellonavali.Screens.zoneStage;

public class CellGrid extends Actor {
    private Batch batch;
    //Variables for water animation
    private Animation water;
    private Animation fire;
    private TextureRegion[] waterTextures;
    private TextureRegion[] fireTextures;

    float waterStateTime = 0f;
    float fireStateTime=0f;
    private CellState state;
    private previewStylePlacement stylePlacement;
    private previewStyleBattle styleBattle;
    //CellGrid select overlay texture
    private boolean showPreview=false;
    private boolean hitPreview =false;

    private Image notEmptyTexture ;
    private Image emptyTexture ;
    private Image emptyInvalidTexture ;

    private Image alreadyHitTexture ;
    private Image waterHoleTexture ;

    //Ship contained
    public Image shipImg;
    //Sizing for the cell
    private int x, y;
    public  int cellWidth;

    private zoneStage parentStage;



    public enum CellState {
        EMPTY, NOT_EMPTY,HIT
    }
    public enum previewStylePlacement {
        VALID,NOT_VALID,
    }
    public enum previewStyleBattle{
        ALREADY_HIT,HIT,WATER_HOLE
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public previewStylePlacement getStylePlacement() {
        return stylePlacement;
    }

    public void setStylePlacement(previewStylePlacement style) {
        this.stylePlacement = style;
    }

    public void setStyleBattle(previewStyleBattle style){
        this.styleBattle=style;
    }

    public CellGrid(int x, int y, zoneStage stage, int gridWidth) {
        this.x = x;
        this.y = y;
        this.parentStage=stage;
        this.state=CellState.EMPTY;
        super.setName(x+","+y);

        cellWidth=gridWidth/8;

        notEmptyTexture= new Image (new Texture(Gdx.files.internal(ConstantsPlaceShips.NOT_EMPTY_TEXTURE_PATH)));
        emptyTexture= new Image(new Texture(Gdx.files.internal(ConstantsPlaceShips.EMPTY_TEXTURE_PATH)));
        emptyInvalidTexture= new Image(new Texture(Gdx.files.internal(ConstantsPlaceShips.EMPTY_INVALID_TEXTURE_PATH)));

        alreadyHitTexture= new Image(new Texture(Gdx.files.internal(ConstantsBattlePhase.ALREADY_HIT_TEXTURE_PATH)));
        waterHoleTexture= new Image(new Texture(Gdx.files.internal(ConstantsBattlePhase.WATER_HOLE_TEXTURE_PATH)));

        setDimensions();
        setUpWaterAnimation();
        setUpFireAnimation();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawWater(batch);
        if(shipImg!=null) {
            shipImg.getDrawable().draw(batch, getX(),getY(),cellWidth,cellWidth);
        }
        if(showPreview) {
            if (state==CellState.EMPTY ){
                if (stylePlacement ==previewStylePlacement.VALID)
                    emptyTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);
                else
                    emptyInvalidTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);
            }
            else
                notEmptyTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);

        }

        if(hitPreview){

            if (styleBattle ==previewStyleBattle.HIT)
                drawFire(batch);

            else if (styleBattle ==previewStyleBattle.ALREADY_HIT)
                alreadyHitTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);

                else if (styleBattle== previewStyleBattle.WATER_HOLE)
                waterHoleTexture.getDrawable().draw(batch,getX(),getY(),cellWidth,cellWidth);








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

    public void dropShip(String civil,String name,int part){

        shipImg= new Image(new Texture(Gdx.files.internal(
                ConstantsPlaceShips.FLEET_PICTURES_PATH+civil+"/divided/"+name+"/"+part+".png")));

        setState(CellState.NOT_EMPTY);


    }

    /**
     * Draws water "sprite"
     */
    public void drawWater(Batch batch) {
        waterStateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame =(TextureRegion)water.getKeyFrame(waterStateTime, true);
        for(float i = 0; i < cellWidth - currentFrame.getRegionWidth(); i+=currentFrame.getRegionWidth()) {
            for(float j = 0; j< cellWidth - currentFrame.getRegionHeight(); j+=currentFrame.getRegionHeight()) {
                batch.draw(currentFrame, getX()+i, getY()+j,cellWidth-1,cellWidth-1);
            }
        }
    }

    /**
     * Draws fire "sprite"
     */
    public void drawFire(Batch batch){
        fireStateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame =(TextureRegion)fire.getKeyFrame(fireStateTime, true);
        for(float i = 0; i < cellWidth - currentFrame.getRegionWidth(); i+=currentFrame.getRegionWidth()) {
            for(float j = 0; j< cellWidth - currentFrame.getRegionHeight(); j+=currentFrame.getRegionHeight()) {
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
     * Sets up water texture and animation
     */
    private void setUpFireAnimation() {
        //Combines the water images into a TextureRegion array
        fireTextures = new TextureRegion[10];
        for(int i=0; i<10; i++) {
            //Uses entire texture as "region" since they aren't on combined sprite sheet
            fireTextures[i] = new TextureRegion(
                    new Texture(Gdx.files.internal(ConstantsBattlePhase.FIRE_TEXTURE+i+".png")),0, 0, cellWidth-1,cellWidth-1);

        }
        //Sets up the background animation at 32 fps
        fire = new Animation(1/2f, fireTextures);
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

    public void showPreviewPlacement(previewStylePlacement s){
       this.showPreview=true;
       this.setStylePlacement(s);

    }

    public void hidePreviewPlacement(){
        this.showPreview=false;
        this.setStylePlacement(previewStylePlacement.NOT_VALID);
    }

    public void showPreviewBattle(previewStyleBattle s){
        this.hitPreview =true;
        this.setStyleBattle(s);
    }

    public void hidePreviewBattle( )
    {
        this.hitPreview =false;

    }
}
