package com.fuicuiedu.idedemo.interview_listview_20161201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        mLv.setAdapter(myAdapter);

        //动态设置listview高度
//        setListViewHeight(mLv);
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
