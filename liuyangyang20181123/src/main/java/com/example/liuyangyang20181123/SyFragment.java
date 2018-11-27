package com.example.liuyangyang20181123;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SyFragment extends Fragment {
    private String path="http://www.xieast.com/api/news/news.php?page=%d";
    private PullToRefreshListView pull;
    private int page=1;
    private MyBase adapter;
    private Dao dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sy,container,false);
        //获取资源id
        pull=view.findViewById(R.id.pull);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //实例化
        adapter=new MyBase(getActivity());
        pull.setAdapter(adapter);
        dao=new Dao(getActivity());
        page=1;
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                  page=1;
                  load();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
             load();
            }
        });
        load();
    }

    @SuppressLint("StaticFieldLeak")
    private void load() {
  Util.setresult(String.format(path,page),Bean.class, new Util.Callback<Bean>() {
           @Override
           public void setsuccess(Bean o) {
               if (!Util.haswl(getActivity())||!o.haswll()||o==null) {
                   List<Bean.DataBean> selsct = dao.selsct();
                   pull.onRefreshComplete();
                   return;
               } else {
                   List<Bean.DataBean> data = o.getData();
                   if (page == 1) {
                       dao.delect();
                   }
                   for (int i = 0; i < data.size(); i++) {
                       String title = data.get(i).getTitle();
                       dao.add(title);
                   }
                   if (page == 1) {
                       adapter.setdata(o.getData());
                   } else {
                       adapter.adddata(o.getData());
                   }
                   page++;
                   pull.onRefreshComplete();
               }
           }
       });
    }


}
