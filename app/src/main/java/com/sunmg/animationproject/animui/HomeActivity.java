package com.sunmg.animationproject.animui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunmg.animationproject.R;
import com.sunmg.utils.DensityUtils;

public class HomeActivity extends AppCompatActivity {


    private ImageView iv_menu;
    private ImageView iv_me;
    private RelativeLayout rl_title;
    private RelativeLayout activity_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        iv_me = (ImageView) findViewById(R.id.iv_me);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        activity_home = (RelativeLayout) findViewById(R.id.activity_home);

        rl_title.post(new Runnable() {
            @Override
            public void run() {
                startAni();
            }
        });

    }

    private void startAni() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(rl_title.getHeight(), DensityUtils.dp2px(this, 50));
        valueAnimator.setInterpolator(new AccelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rl_title.getLayoutParams().height = (int) animation.getAnimatedValue();
                rl_title.requestLayout();
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimator,
                ObjectAnimator.ofFloat(iv_me, "Alpha", 0.1f, 1.0f),
                ObjectAnimator.ofFloat(iv_menu, "Alpha", 0.1f, 1.0f));
        animatorSet.setDuration(1200);
        animatorSet.start();
    }
}
