package com.fuicuiedu.idedemo.interview_listview_20161201;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private List<String> datas;
    private ArrayAdapter<String> mAdapter;
    private MyAdapter myAdapter;
    private Handler handler;
    private int visblelastIndex;//最后一条数据下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        mLv = (ListView) findViewById(R.id.main_lv);

        datas = new ArrayList<>();

        //fori
        for (int i = 0; i < 50; i++) {
            datas.add("第" + i + "条数据");
        }

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);

        myAdapter = new MyAdapter(this,datas);

        mLv.setOnScrollListener(listener);

        mLv.setAdapter(myAdapter);

    }


    //当用户滑动listView到底部时自动加载
//    //手指按下移动的状态
//    SCROLL_STATE_TOUCH_SCROLL;//触摸滑动
//    //惯性滚动状态
//    SCROLL_STATE_FLING;//滑翔
//    //静止状态
//    SCROLL_STATE_IDLE;//静止
//
//    目的：当用户滑动listView到底部时自动加载
//    实现：判断是否是静止状态，是否滑动到了底部（最后数据）
    private AbsListView.OnScrollListener listener = new AbsListView.OnScrollListener() {
        //滚动状态发生变化
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
            //数据最后一条下标
            int lastIndex = myAdapter.getCount() - 1;
            //判断是否是静止状态，是否滑动到了底部（最后数据）
            if (i == SCROLL_STATE_IDLE && visblelastIndex == lastIndex){
                loadMoreData();
                myAdapter.notifyDataSetChanged();
            }
        }

        //当listveiw被滚动时调用的方法
        //i:firstVisbleItem;//第一个可见的item
        //i1:visiblewItemCount;//可见的item数量
        //i2:totalItemCount;//所有数据条目
        @Override
        public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            //可见的最后一个item下标
            visblelastIndex = i + i1 - 1;

            Log.e("=======================","==========================");//单行复制，ctrl + d
            Log.e("firstVisbleItem = " , i + "");
            Log.e("visiblewItemCount = " , i1 + "");
            Log.e("totalItemCount = " , i2 + "");
            Log.e("=======================","==========================");
        }
    };

    //加上加载更多的View
    private void addMoreView(ListView listView){
        //拿到加载更多的View
        View view = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.view_more,null);
        //给ListView添加footer布局
        listView.addFooterView(view);

        final Button moreBtn = (Button) view.findViewById(R.id.view_more_btn);
        final ProgressBar morePrb = (ProgressBar) view.findViewById(R.id.view_more_prb);

        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //视图操作，显示和隐藏
                moreBtn.setVisibility(View.INVISIBLE);
                morePrb.setVisibility(View.VISIBLE);
                //模拟网络加载数据,用handler发送一个延迟的任务
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载更多
                        loadMoreData();
                        moreBtn.setVisibility(View.VISIBLE);
                        morePrb.setVisibility(View.INVISIBLE);
                        //刷新数据
                        myAdapter.notifyDataSetChanged();
                    }
                },2000);
            }
        });
    }

    //加载更多的方法
    private void loadMoreData(){
        for (int i = 0; i < 5; i++) {
            datas.add("第" + i + "条数据（新）");
        }
    }


    //动态设置listview高度
    public void setListViewHeight(ListView listView){
            //获取lisview对应的Adapter
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null){
                return;
            }
            int totalHeight = 0;
            for (int i = 0 ;i < listAdapter.getCount(); i++) {
                //listAdapter。getCount（）返回数据项的数目
                View listItem = listAdapter.getView(i,null,listView);
                //测试子项View的宽高，执行完这个方法后，子项的宽高就有了
                listItem.measure(0,0);
                //统计所有子项的高度
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            //listView.getDividerHeight()获取子项间分隔符占用的高度
            int dividerHeight = (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            params.height = totalHeight + dividerHeight;
            listView.setLayoutParams(params);
    }


}
