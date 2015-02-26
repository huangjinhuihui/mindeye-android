package com.wholeally.mindeye_android_new.custom.view;

import android.graphics.Path;
import android.graphics.PointF;
import android.view.View;

/**
 * Pie menu item
 */
public class PieItem {

	/**用来显示扇形中那个图片的view*/
    private View mView;
    /**扇形环的起始角度*/
    private int mStart;
    /**扇形环从起始位置开始旋转的度数*/
    private int mSweep;
    /**圆环内圆半径*/
    private int mInner;
    /**圆环外圆半径*/
    private int mOuter;
    /**true--按下，false--松开*/
    private boolean mPressed;
    /**扇形环的路径轨迹*/
    private Path mPath;
    private PointF mCenter = null;
    
    public PieItem(View view) {
        mView = view;
    }
    
    public void setPressed(boolean pressed) {
        mPressed = pressed;
        if (mView != null) {
            mView.setPressed(pressed);
        }
    }

    public boolean isPressed() {
        return mPressed;
    }

    public void setCenter(PointF center){
        mCenter = center;
    }

    public PointF getCenter(){
        return mCenter;
    }

    public void setGeometry(int start, int sweep, int inside, int outside, Path p) {
        mStart = start;
        mSweep = sweep;
        mInner = inside;
        mOuter = outside;
        mPath = p;
    }

    public int getStartAngle() {
        return mStart;
    }

    public int getSweep() {
        return mSweep;
    }

    public int getInnerRadius() {
        return mInner;
    }

    public int getOuterRadius() {
        return mOuter;
    }

    public View getView() {
        return mView;
    }

    public Path getPath() {
        return mPath;
    }

}
