package com.sunmg.animationproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

import com.sunmg.adapter.AnimRecyclerAdapter;
import com.sunmg.bean.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AnimRecyclerAdapter.OnItemClickListener {


    private RecyclerView anim_recycleList;

    private List<String> itemList;
    private AnimRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        anim_recycleList = (RecyclerView) findViewById(R.id.anim_recycleList);
        anim_recycleList.setLayoutManager(new LinearLayoutManager(this));
        anim_recycleList.setItemAnimator(new DefaultItemAnimator());
        anim_recycleList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new AnimRecyclerAdapter(this,itemList);
        anim_recycleList.setAdapter(mAdapter);
        mAdapter.setListener(this);

        setUpWindowAnimations();

    }

    private void setUpWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }

    private void initData() {
        itemList = new ArrayList<>();
        itemList.add("Tween Animation");
        itemList.add("Property Animation");
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position){
            case 0:       // Tween animation　
                break;
            case 1:       // property animation
                Intent intent = new Intent(MainActivity.this, PropertyAnimActivity.class);
                startActivity(intent);
                break;
        }
    }
}
