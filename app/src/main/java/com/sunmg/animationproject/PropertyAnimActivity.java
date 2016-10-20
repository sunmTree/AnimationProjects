package com.sunmg.animationproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunmg.adapter.AnimRecyclerAdapter;
import com.sunmg.animationproject.animui.GroupAnimActivity;
import com.sunmg.animationproject.animui.ValueAnimationAct;
import com.sunmg.bean.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PropertyAnimActivity extends AppCompatActivity implements AnimRecyclerAdapter.OnItemClickListener {


    private RecyclerView property_list;

    private List<String> itemList;
    private AnimRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        property_list = (RecyclerView) findViewById(R.id.property_list);
        initData();

        property_list.setLayoutManager(new LinearLayoutManager(this));
        property_list.setItemAnimator(new DefaultItemAnimator());
        property_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new AnimRecyclerAdapter(this,itemList);
        property_list.setAdapter(mAdapter);
        mAdapter.setListener(this);
    }

    private void initData() {
        itemList = new ArrayList<>();
        itemList.add("BaseAnim");
        itemList.add("GroupAnim");
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(PropertyAnimActivity.this, ValueAnimationAct.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(PropertyAnimActivity.this, GroupAnimActivity.class);
                startActivity(intent);
                break;
        }
    }
}
