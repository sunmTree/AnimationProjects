package com.sunmg.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by sm on 2016/10/19.
 */

public class AnimUtils {

    private static long time = 2500;

    public static void alphaAnim(View target){
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(target,"alpha",0.0f,0.4f,0.6f,1.0f);
        alphaAnim.setDuration(time);
        alphaAnim.start();
    }

    public static void alphaAndScale(View target){
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(target,"alpha",0.0f,0.6f,0.9f,1.0f);
        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(target,"ScaleY",0.0f,0.6f,1.1f,1.0f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim,scaleAnim);
        set.setDuration(time);
        set.start();
    }

    public static void scaleToRotateToScale(final View target, final Animator.AnimatorListener listener){
        ObjectAnimator scaleAnimX = ObjectAnimator.ofFloat(target,"ScaleX",30.0f).setDuration(1000);
        ObjectAnimator scaleAnimY = ObjectAnimator.ofFloat(target,"ScaleY",30.0f).setDuration(1000);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(target,"rotationY",0.0f,180.0f).setDuration(1000);
        final ObjectAnimator scale2AnimX = ObjectAnimator.ofFloat(target,"ScaleX",200.0f).setDuration(1000);
        final ObjectAnimator scale2AnimY = ObjectAnimator.ofFloat(target,"ScaleY",200.0f).setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleAnimX,scaleAnimY,rotationY);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                showRotate(target,rotate,listener);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(scale2AnimX,scale2AnimY);
                set.start();
                set.addListener(listener);
            }
        });
    }

}
