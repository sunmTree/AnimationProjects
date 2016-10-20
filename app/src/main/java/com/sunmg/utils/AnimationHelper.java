package com.sunmg.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by sm on 2016/10/18.
 */

public class AnimationHelper {

    /***
     * 扩散动画
     * @param target 对象
     * @param endScale 最终缩放大小
     * @param listener 动画监听
     */
    public static void spreadAni(View target, float endScale, SimpleAnimatorListener listener) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(target, "ScaleX", endScale);
        scaleXAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(target, "ScaleY", endScale);
        scaleYAnimator.setInterpolator(new AccelerateInterpolator());

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleXAnimator).with(scaleYAnimator);
        animSet.setDuration(500);
        animSet.start();

        animSet.addListener(listener);
    }

    /***
     * 线条延长动画
     * @param target
     * @param listener
     */
    public static void lineExpendAni(View target, SimpleAnimatorListener listener){
        target.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(target,"ScaleX",0,0.6f,0.9f,1.0f);
        animator.setDuration(1500);
        animator.setInterpolator(new DecelerateInterpolator());
        target.setPivotX(0);
        animator.start();

        animator.addListener(listener);
    }

    /***
     * sign up 文字入场动画
     * @param target
     * @param listener
     */
    public static void signUpTextInAni(View target, SimpleAnimatorListener listener){
        target.setVisibility(View.VISIBLE);

        int targetHeight = target.getMeasuredHeightAndState();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, "ScaleX", 0.75f, 0.87f, 1f, 1.1f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, "ScaleY", 0.75f, 0.87f, 1f, 1.1f, 1.0f);
        ObjectAnimator translationAni = ObjectAnimator.ofFloat(target, "TranslationY", targetHeight * 0.8f, -targetHeight * 0.2f, 0);
        ObjectAnimator alphaAni = ObjectAnimator.ofFloat(target, "Alpha", 0.5f, 1.0f);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(scaleX, scaleY, translationAni, alphaAni);
        animatorSet.setDuration(2000);
        animatorSet.start();

        animatorSet.addListener(listener);
    }

    public static void successInAni(View success, View signUpView, SimpleAnimatorListener endListener){
        success.setVisibility(View.VISIBLE);

        int durationTime = 1000;

        int width = success.getMeasuredWidth();

        ObjectAnimator successTranslationAni = ObjectAnimator.ofFloat(success, "TranslationX", width * -1.2f, width * -0.2f, 0);
        ObjectAnimator successAlphaAni = ObjectAnimator.ofFloat(success, "Alpha", 0.1f, 1.0f, 1.0f);

        ObjectAnimator signUpTranslationAni = ObjectAnimator.ofFloat(signUpView, "TranslationX",  0, width * 0.75f, width * 1.5f);
        signUpTranslationAni.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator signUpAlphaAni = ObjectAnimator.ofFloat(signUpView, "Alpha", 1.0f, 1.0f, 0.4f, 0.0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(successTranslationAni, successAlphaAni, signUpTranslationAni, signUpAlphaAni);
        animatorSet.setDuration(durationTime);
        animatorSet.start();

        animatorSet.addListener(endListener);
    }


    public static abstract class SimpleAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }
    }


}
