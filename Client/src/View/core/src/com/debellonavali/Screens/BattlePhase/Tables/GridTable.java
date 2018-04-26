package com.debellonavali.Screens.BattlePhase.Tables;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.debellonavali.Constants;
import com.debellonavali.Tween.ActorAccessor;

public abstract class GridTable extends Table {

    public void toggleDimension(boolean resized, TweenManager tweenManager) {

        float new_pos_x = resized ? Constants.GRID_MINIMIZED_POS_X : Constants.GRID_MAXIMIZED_POS_X;
        float new_pos_y = resized ? Constants.GRID_MINIMIZED_POS_Y : Constants.GRID_MAXIMIZED_POS_Y;
        float new_width = resized ? Constants.GRID_MINIMIZED_WIDTH : Constants.GRID_MAXIMIZED_WIDTH;
        float new_height = resized ? Constants.GRID_MINIMIZED_HEIGHT : Constants.GRID_MAXIMIZED_HEIGHT;
        float new_padding = resized ? Constants.GRID_MINIMIZED_PADDING : Constants.GRID_MAXIMIZED_PADDING;

        Timeline.createParallel().beginParallel()
                .push(Tween.to(this, ActorAccessor.WIDTH, .3f).target(new_width))
                .push(Tween.to(this, ActorAccessor.HEIGHT, .3f).target(new_height))
                .push(Tween.to(this, ActorAccessor.X, .3f).target(new_pos_x))
                .push(Tween.to(this, ActorAccessor.Y, .3f).target(new_pos_y))
                .end().start(tweenManager);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                pad(new_padding);
            }
        }, .3f);

    }


}
