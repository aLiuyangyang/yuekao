package com.example.liuyangyang20181123;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Util {
//判断是否有网络网络
    @SuppressLint("MissingPermission")
    public static boolean haswl(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo!=null&&activeNetworkInfo.isAvailable();
    }
    public static void setresult(String path, final Class clazz, final Callback callback){
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return setresult(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callback.setsuccess(o);
            }
        }.execute(path);
    }

    public interface Callback<T>{
      void setsuccess(T o);
    };

    public static <T> T setresult(String path,Class clazz){
        String json = setresult(path);
        Gson gson=new Gson();
        T t = (T) gson.fromJson(json, clazz);
        return t;
    }
    public static String setresult(String path){
        String result="";
        try {
            URL url=new URL(path);
            HttpURLConnection http= (HttpURLConnection) url.openConnection();
            int responseCode = http.getResponseCode();
            if (responseCode==200){
                  result=string2(http.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String string2(InputStream inputStream) throws IOException {
        InputStreamReader in=new InputStreamReader(inputStream);
        BufferedReader br=new BufferedReader(in);
        StringBuilder builder=new StringBuilder();
        for (String t=br.readLine();t!=null;t=br.readLine()){
            builder.append(t);
        }
        return builder.toString();
    }
}
