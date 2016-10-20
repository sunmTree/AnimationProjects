package com.sunmg.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sunmg.animationproject.R;
import com.sunmg.bean.ViewPoint;

/**
 * Created by sm on 2016/10/19.
 */

public class PointScaleView extends ViewGroup {

    private Context mContext;
    private ViewPoint touchPoint;

    public PointScaleView(Context context) {
        this(context,null);
    }

    public PointScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        View view = new View(mContext);
        if (touchPoint == null){
            return;
        }

        LayoutParams params = new LayoutParams(20, 20);
        view.setLayoutParams(params);
        view.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_bg));

        view.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchPoint = new ViewPoint(event.getX(),event.getY());
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
