package com.example.day1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

class MyBase extends PagerAdapter {
    private List<String> list;
    private Context context;

    public MyBase(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return list.size() > 0 ? 5000:0;
    }
    public String getItem(int position){
        return list.get(position%list.size());
    }

    public void setdata(List<String> lists){
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        ImageLoader.getInstance().displayImage(getItem(position),imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
