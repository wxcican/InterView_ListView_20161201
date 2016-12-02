package com.fuicuiedu.idedemo.interview_listview_20161201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLv = (ListView) findViewById(R.id.main_lv);

        datas = new ArrayList<>();

        //fori
        for (int i = 0; i < 50; i++) {
            datas.add("第" + i + "条数据");
        }

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);

        myAdapter = new MyAdapter(this,datas);

        //加上加载更多的View
        addMoreView(mLv);

        mLv.setAdapter(myAdapter);

        //动态设置listview高度
//        setListViewHeight(mLv);
    }


    //加上加载更多的View
    private void addMoreView(ListView listView){
        //拿到加载更多的View
        View view = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.view_more,null);
        //给ListView添加footer布局
        listView.addFooterView(view);

        Button moreBtn = (Button) view.findViewById(R.id.view_more_btn);
        ProgressBar morePrb = (ProgressBar) view.findViewById(R.id.view_more_prb);

        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //视图操作，显示和隐藏
                //模拟网络加载数据
            }
        });
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
