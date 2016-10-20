package com.sunmg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunmg.animationproject.R;

import java.util.List;

/**
 * Created by sm on 2016/10/18.
 */

public class AnimRecyclerAdapter extends RecyclerView.Adapter<AnimRecyclerAdapter.MyViewHolder> {

    private List<String> itemList;
    private LayoutInflater mInflater;

    public AnimRecyclerAdapter(Context context , List<String> itemList) {
        this.itemList = itemList;
        mInflater = LayoutInflater.from(context);
    }

    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.one_string,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTv.setText(itemList.get(position));

        if (mListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v,holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.posiStr);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
