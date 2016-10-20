package com.sunmg.animationproject.animui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunmg.animationproject.R;
import com.sunmg.view.TransitionView;

public class LoginAnimActivity extends AppCompatActivity {


    private TextView tv_sign_up;
    private TransitionView ani_view;
    private RelativeLayout activity_login_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_anim);
        tv_sign_up
                = (TextView) findViewById(R.id.tv_sign_up);
        ani_view
                = (TransitionView) findViewById(R.id.ani_view);
        activity_login_anim
                = (RelativeLayout) findViewById(R.id.activity_login_anim);

        ani_view.setOnAnimationEndListener(new TransitionView.OnAnimationEndListener() {
            @Override
            public void onEnd() {
                getHomeActivity();
            }
        });
    }

    private void getHomeActivity() {
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }

    public void singUp(View view){
        ani_view.startAnimation();
    }

}
