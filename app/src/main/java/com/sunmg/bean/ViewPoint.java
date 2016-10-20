package com.sunmg.bean;

/**
 * Created by sm on 2016/10/19.
 */

public class ViewPoint {

    private float pointX;
    private float pointY;

    public ViewPoint() {
    }

    public ViewPoint(float pointX, float pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public float getPointX() {
        return pointX;
    }

    public void setPointX(float pointX) {
        this.pointX = pointX;
    }

    public float getPointY() {
        return pointY;
    }

    public void setPointY(float pointY) {
        this.pointY = pointY;
    }
}
