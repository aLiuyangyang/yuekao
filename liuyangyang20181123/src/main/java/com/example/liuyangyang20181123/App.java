package com.example.liuyangyang20181123;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions options=new DisplayImageOptions.Builder().build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)

                .memoryCacheSizePercentage(10)
                .diskCacheSize(50*1024*1024)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);

    }
}
