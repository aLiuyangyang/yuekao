package com.example.day1;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     private String path="http://www.zhaoapi.cn/product/getProductDetail?pid=1";
     private ViewPager pager;
     private TextView title1,title2,title3;
     private MyBase adapter;
     private Handler handler=new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             pager.setCurrentItem(pager.getCurrentItem()+1);
             handler.sendEmptyMessageDelayed(0,1000);

         }
     };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager=findViewById(R.id.pager);
        title1=findViewById(R.id.title1);
        title2=findViewById(R.id.title2);
        title3=findViewById(R.id.title3);
        adapter=new MyBase(this);
        pager.setAdapter(adapter);
        pager.setCurrentItem(5000);
        int i = pager.getCurrentItem();
        handler.sendEmptyMessageDelayed(i,1000);


          Util.setresult(path,Bean.class, new Util.Callback<Bean>() {
              @Override
              public void setsuccess(Bean o) {
                  String images = o.getData().getImages();
                  String[] split = images.split("\\|");
                  List<String> list = Arrays.asList(split);
                  for (int j = 0; j <list.size() ; j++) {
                       list.get(j);
                  }
                  adapter.setdata(list);

                  title1.setText(o.getSeller().getName());
                  title2.setText(o.getData().getPid()+"");
                  title3.setText(o.getData().getSalenum()+"");

              }
          });

    }

}
