package com.sunmg.view;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunmg.animationproject.R;
import com.sunmg.utils.AnimationHelper;

/**
 * Created by sm on 2016/10/18.
 */

public class TransitionView extends RelativeLayout {

    private View v_spread; // 播放扩散动画的View
    private View v_line;
    private TextView tv_sign_up;
    private TextView tv_success;

    private OnAnimationEndListener mOnAnimationEndListener;

    public TransitionView(Context context) {
        this(context, null);
    }

    public TransitionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransitionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View mRootView = inflate(getContext(), R.layout.view_transtion, this);

        v_spread = mRootView.findViewById(R.id.v_spread);
        v_line = mRootView.findViewById(R.id.v_line);
        tv_sign_up = (TextView) mRootView.findViewById(R.id.tv_sign_up);
        tv_success = (TextView) mRootView.findViewById(R.id.tv_success);
    }

    /***
     * 开始播放动画
     */
    public void startAnimation() {
        this.setVisibility(VISIBLE);

        tv_sign_up.setTranslationX(0);
        tv_sign_up.setVisibility(INVISIBLE);
        tv_success.setVisibility(INVISIBLE);
        v_line.setVisibility(INVISIBLE);

        AnimationHelper.spreadAni(v_spread, getScale(), new AnimationHelper.SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startSignUpInAni();
            }
        });
    }

    private void startSignUpInAni() {
        AnimationHelper.signUpTextInAni(tv_sign_up, new AnimationHelper.SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //开启线条播放动画
                startLineAni();
            }
        });
    }

    private void startLineAni() {
        AnimationHelper.lineExpendAni(v_line, new AnimationHelper.SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //开启success文字入场动画
                startSuccessAni();
            }
        });
    }

    private void startSuccessAni() {
        AnimationHelper.successInAni(tv_success, tv_sign_up, new AnimationHelper.SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //回调
                if (mOnAnimationEndListener != null) {
                    mOnAnimationEndListener.onEnd();
                }
            }
        });
    }

    // 计算扩散动画最终放大比例
    private float getScale() {
        int orgWidth = v_spread.getMeasuredWidth();

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        // 扩散圆最终扩散半径
        float finalDiameter = (int) Math.sqrt(width * width + height * height);
        // 因为圆未居中,所以+1
        return finalDiameter / orgWidth + 1;
    }

    public void setOnAnimationEndListener(OnAnimationEndListener onAnimationEndListener) {
        mOnAnimationEndListener = onAnimationEndListener;
    }

    /***
     * 动画结束监听
     */
    public interface OnAnimationEndListener {
        void onEnd();
    }
}
