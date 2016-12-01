package com.fuicuiedu.idedemo.interview_listview_20161201;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class MyAdapter extends BaseAdapter {

    private List<String> mDatas;
    private Context context;
    private LayoutInflater inflater;

    //构造方法---快捷键-----alt + insert ->cons....
    public MyAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = inflater.inflate(R.layout.layout_item,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();

        String data = mDatas.get(i);
        holder.mTv.setText(data);

        return view;
    }


    private class ViewHolder{
        TextView mTv;
        Button mBtn;

        public ViewHolder(View view) {
            mTv = (TextView) view.findViewById(R.id.item_tv);
            mBtn = (Button) view.findViewById(R.id.item_btn);
        }
    }
}
