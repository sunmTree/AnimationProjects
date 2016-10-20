package com.sunmg.animationproject;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sunmg.utils.AnimUtils;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView Sign_title;
    private EditText sign_name;
    private EditText sign_password;
    private LinearLayout input_parent;
    private Button sign_ok;
    private Button sign_register;
    private LinearLayout sign_btn_parent;
    private ImageView sign_img,flay;
    private RelativeLayout activity_start;
    private View point_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);

        initView();
    }

    private void login() {
        String loginName = sign_name.getText().toString().trim();
        String loginPwd = sign_password.getText().toString().trim();

        if (loginName == null || loginName.equals("")){
            Toast.makeText(this, getResources().getString(R.string.input_name), Toast.LENGTH_SHORT).show();
            return;
        }

        if (loginPwd == null || loginPwd.equals("")){
            Toast.makeText(this, getResources().getString(R.string.password), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initView() {

        Sign_title = (TextView) findViewById(R.id.Sign_title);
        sign_name = (EditText) findViewById(R.id.sign_name);
        sign_password = (EditText) findViewById(R.id.sign_password);
        input_parent = (LinearLayout) findViewById(R.id.input_parent);
        sign_ok = (Button) findViewById(R.id.sign_ok);
        sign_register = (Button) findViewById(R.id.sign_register);
        sign_btn_parent = (LinearLayout) findViewById(R.id.sign_btn_parent);
        sign_img = (ImageView) findViewById(R.id.sign_img);
        flay = (ImageView) findViewById(R.id.flay);
        activity_start = (RelativeLayout) findViewById(R.id.activity_start);
        point_v = findViewById(R.id.first_page);

        sign_ok.setOnClickListener(this);
        sign_register.setOnClickListener(this);

        // 开启动画
        AnimUtils.scaleToRotateToScale(point_v, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                point_v.setVisibility(View.GONE);
                setVisiAble();
                showUI();
            }
        });
    }

    private void setVisiAble() {
        Sign_title.setVisibility(View.VISIBLE);
        input_parent.setVisibility(View.VISIBLE);
        sign_btn_parent.setVisibility(View.VISIBLE);
        sign_img.setVisibility(View.VISIBLE);
    }

    /***
     * 显示控件
     */
    public void showUI(){
        // sign_title move out
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.translate_x);
        animator.setTarget(Sign_title);
        animator.start();

        AnimUtils.alphaAnim(sign_ok);
        AnimUtils.alphaAnim(sign_register);

        AnimUtils.alphaAndScale(sign_name);
        AnimUtils.alphaAndScale(sign_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_ok:
                login();
                break;
            case R.id.sign_register:
                break;
        }
    }
}
