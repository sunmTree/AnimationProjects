package com.sunmg.animationproject.animui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.sunmg.animationproject.R;
import com.sunmg.view.PointScaleView;

public class SelfViewActivity extends AppCompatActivity {


    private PointScaleView point_self;
    private RelativeLayout activity_self_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_view);
        point_self
                = (PointScaleView) findViewById(R.id.point_self);
        activity_self_view
                = (RelativeLayout) findViewById(R.id.activity_self_view);

    }
}
