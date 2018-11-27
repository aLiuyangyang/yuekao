package com.example.liuyangyang20181123;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private MySqlite sqlite;
    private SQLiteDatabase database;
    public Dao(Context context){
        sqlite=new MySqlite(context);
        database=sqlite.getReadableDatabase();
    }
    //添加
    public void add(String title){
        ContentValues vaules=new ContentValues();
        vaules.put("title",title);

        database.insert("user",null,vaules);
    }
    //查询
    public List<Bean.DataBean> selsct(){
        List<Bean.DataBean> list=new ArrayList<>();

        Cursor query = database.query("user", null, null,null, null, null, null);
        while (query.moveToNext()){
            String title = query.getString(query.getColumnIndex("title"));
            Bean.DataBean bean=new Bean.DataBean();
            bean.setTitle(title);
            list.add(bean);
        }
        return list;
    }
    public void delect(){
        database.delete("user",null,null);
    }
}
