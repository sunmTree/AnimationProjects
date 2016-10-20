package com.sunmg.animationproject.animui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.sunmg.animationproject.R;

public class GroupAnimActivity extends AppCompatActivity implements View.OnClickListener {


    private Button layout_animator_addbutton;
    private Button layout_animator_resetbutton;
    private GridLayout layout_animator_gridview;
    private LayoutTransition mTransition;

    private static int bottomNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_anim);

        initView();

    }

    private void initView() {
        layout_animator_addbutton = (Button) findViewById(R.id.layout_animator_addbutton);
        layout_animator_resetbutton = (Button) findViewById(R.id.layout_animator_resetbutton);
        layout_animator_gridview = (GridLayout) findViewById(R.id.layout_animator_gridview);

        layout_animator_addbutton.setOnClickListener(this);
        layout_animator_resetbutton.setOnClickListener(this);

        mTransition = new LayoutTransition();
        layout_animator_gridview.setLayoutTransition(mTransition);
        mTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
        mTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
        //设置每个动画的持续时间
        mTransition.setDuration(300);
        //初始化自定义的动画效果
        customLayoutTransition();
    }

    private void customLayoutTransition() {
        // 设置被添加的button的动画效果
        ObjectAnimator objAppearing = ObjectAnimator.ofFloat(null, "RotationY", 90.0f, 0.0f)
                .setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, objAppearing);
        objAppearing.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setRotationY(0.0f);
            }
        });

        // 设置其他的button的动画效果
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder mHolderSX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f, 1.0f);
        PropertyValuesHolder mHolderSY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f, 1.0f);

        ObjectAnimator objChangeAnim = ObjectAnimator.ofPropertyValuesHolder(this, pvhLeft, pvhTop,
                pvhRight, pvhBottom, mHolderSX, mHolderSY).setDuration(mTransition
                .getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, objChangeAnim);
        objChangeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });


/**
 * Delete Button
 * LayoutTransition.DISAPPEARING
 * 当删除一个Button时，设置该Button的动画效果
 */
        ObjectAnimator mObjectAnimatorDisAppearing = ObjectAnimator.ofFloat(null, "rotationX", 0.0f, 90.0f)
                .setDuration(mTransition.getDuration(LayoutTransition.DISAPPEARING));
        mTransition.setAnimator(LayoutTransition.DISAPPEARING, mObjectAnimatorDisAppearing);
        mObjectAnimatorDisAppearing.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                super.onAnimationEnd(animation);
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setRotationX(0.0f);
            }
        });

        /**
         * Delete Button
         * LayoutTransition.CHANGE_DISAPPEARING
         * 当删除一个Button时，设置其它Button的动画效果
         */
        //Keyframe 对象中包含了一个时间/属性值的键值对，用于定义某个时刻的动画状态。
        Keyframe mKeyframeStart = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe mKeyframeMiddle = Keyframe.ofFloat(0.5f, 180.0f);
        Keyframe mKeyframeEndBefore = Keyframe.ofFloat(0.999f, 360.0f);
        Keyframe mKeyframeEnd = Keyframe.ofFloat(1.0f, 0.0f);

        PropertyValuesHolder mPropertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotation",
                mKeyframeStart, mKeyframeMiddle, mKeyframeEndBefore, mKeyframeEnd);
        ObjectAnimator mObjectAnimatorChangeDisAppearing = ObjectAnimator.ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight, pvhBottom, mPropertyValuesHolder)
                .setDuration(mTransition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mObjectAnimatorChangeDisAppearing);
        mObjectAnimatorChangeDisAppearing.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                super.onAnimationEnd(animation);
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setRotation(0.0f);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_animator_addbutton:
                addButton();
                break;
            case R.id.layout_animator_resetbutton:
                resetButton();
                break;
            default:
                break;
        }
    }

    private void resetButton() {
        layout_animator_gridview.removeAllViews();
        bottomNum = 1;
    }

    private void addButton() {
        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        button.setLayoutParams(params);
        button.setText(String.valueOf(bottomNum++));
        button.setTextColor(Color.rgb(0, 0, 0));
        if (bottomNum % 2 == 1)
            button.setBackgroundColor(Color.rgb(45, 119, 78));
        else
            button.setBackgroundColor(Color.rgb(225, 24, 1));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_animator_gridview.removeView(v);
            }
        });
        layout_animator_gridview.addView(button, Math.min(1, layout_animator_gridview.getChildCount()));
    }
}
