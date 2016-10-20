package com.sunmg.animationproject.animui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.sunmg.animationproject.R;

public class ValueAnimationAct extends AppCompatActivity {


    private Button valueAnim;
    private Button linear;
    private Button non_linear;
    private Button anim_set,traAndSca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animation);

        initView();
        Resources resources = this.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        final int widthPixels = displayMetrics.widthPixels;

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", 0.0f, widthPixels/2);
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(2000).start();
            }
        });

        non_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PropertyValuesHolder a1 = PropertyValuesHolder.ofFloat("alpha",0f,1f);
                PropertyValuesHolder a2 = PropertyValuesHolder.ofFloat("Rotation",0.0f,360.0f);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, a2);
                animator.setInterpolator(new AnticipateInterpolator());
                animator.setDuration(2000).start();
            }
        });

        valueAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ValueAnimator animator = ValueAnimator.ofFloat(0,200.0f);
                animator.setTarget(v);
                animator.setDuration(2000).start();
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        v.setRotationX(value);
                    }
                });
            }
        });

        anim_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator a1 = ObjectAnimator.ofFloat(v,"alpha",1.0f,0.0f);
                ObjectAnimator a2 = ObjectAnimator.ofFloat(v,"translationX",0f,200.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(2000);
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.playTogether(a1,a2);
                animatorSet.start();
            }
        });

        final AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.multi_anim);
        animatorSet.setTarget(traAndSca);
        traAndSca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v,"ScaleX",0.5f,0.9f,1.1f,1.3f,1.0f);
//                ObjectAnimator object = ObjectAnimator.ofFloat(v,"ScaleY",0.5f,0.9f,1.1f,1.3f,1.0f);
//
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.playTogether(objectAnimator,object);
//                animatorSet.setDuration(2000).start();
                animatorSet.start();
            }
        });

    }

    private void initView() {
        linear = (Button) findViewById(R.id.linear);
        non_linear = (Button) findViewById(R.id.non_linear);
        valueAnim = (Button) findViewById(R.id.valueAnim);
        anim_set = (Button) findViewById(R.id.anim_set);
        traAndSca = (Button) findViewById(R.id.trans_scale);
    }
}
