package com.debellonavali.Tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorAccessor implements TweenAccessor<Actor> {

    public static final int RGB = 0;
    public static final int ALPHA = 1;
    public static final int X = 2;
    public static final int Y = 3;
    public static final int WIDTH = 4;
    public static final int HEIGHT = 5;

    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case RGB:
                returnValues[0] = target.getColor().r;
                returnValues[1] = target.getColor().g;
                returnValues[2] = target.getColor().b;
                return 3;
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            case X:
                returnValues[0] = target.getX();
                return 1;
            case Y:
                returnValues[0] = target.getY();
                return 1;
            case WIDTH:
                returnValues[0] = target.getWidth();
                return 1;
            case HEIGHT:
                returnValues[0] = target.getHeight();
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case RGB:
                target.setColor(newValues[0], newValues[1], newValues[2], target.getColor().a);
                break;
            case ALPHA:
                target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
                break;
            case X:
                target.setX(newValues[0]);
                break;
            case Y:
                target.setY(newValues[0]);
                break;
            case WIDTH:
                target.setWidth(newValues[0]);
                break;
            case HEIGHT:
                target.setHeight(newValues[0]);
                break;
            default:
                assert false;
        }
    }
}
