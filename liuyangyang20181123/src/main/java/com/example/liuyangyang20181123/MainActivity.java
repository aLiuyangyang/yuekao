package com.example.liuyangyang20181123;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     private DrawerLayout draw;
     private ViewPager pager;
     private RadioGroup radio;
     private ListView listView;
     private List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取S资源id
        draw=findViewById(R.id.draw);
        pager=findViewById(R.id.pager);
        radio=findViewById(R.id.radio);
        listView=findViewById(R.id.listview);
        //实例化
        list=new ArrayList<>();
        list.add(new SyFragment());
        list.add(new ZrFragment());
        list.add(new WdFragment());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.but2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.but3:
                        pager.setCurrentItem(2);
                        break;
                        default:
                            break;
                }
            }
        });



        List<String> list1=new ArrayList<>();
        list1.add("首页");
        list1.add("找人");
        list1.add("未登录");
        listView.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list1));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pager.setCurrentItem(position);
                draw.closeDrawers();
            }
        });
    }
}
