package com.debellonavali.PlaceShip;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Ship extends Actor {

    private Texture entireImg;
    private String name;
    private int dim;



    public Ship(String nameDim, Texture texture) {
        entireImg= texture;
        this.name=nameDim.split("-")[0];
        this.dim= Integer.parseInt( nameDim.split("-")[1]);

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {

    }


}
