package com.example.liuyangyang20181123;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

class MyBase extends BaseAdapter {
    private List<Bean.DataBean> mlist;
    private Context context;
    public MyBase(Context context){
        this.context=context;
        mlist=new ArrayList<>();
    }
    public void setdata(List<Bean.DataBean> list){
        mlist.clear();
        if (list!=null){
            mlist.addAll(list);
        }
        notifyDataSetChanged();
    }
    public void adddata(List<Bean.DataBean> list){
        if (list!=null){
            mlist.addAll(list);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Bean.DataBean getItem(int position) {
        return mlist.get(position);
    }
    private final int Counts=2;
    private final int Types1=0;
    private final int Types2=1;
    @Override
    public int getViewTypeCount() {
        return Counts;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            if (getItemViewType(position)==Types1){
                convertView=View.inflate(context,R.layout.type1,null);
            }else{
                convertView=View.inflate(context,R.layout.type2,null);
            }
            holder=new ViewHolder();
            holder.text=convertView.findViewById(R.id.text);
            holder.image1=convertView.findViewById(R.id.image1);
            holder.image2=convertView.findViewById(R.id.image2);
            holder.image3=convertView.findViewById(R.id.image3);
            convertView.setTag(holder);
            }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.text.setText(getItem(position).getTitle());
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s(),holder.image1,options);
        if (holder.image2!=null||holder.image3!=null){
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s02(),holder.image2,options);
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s03(),holder.image3,options);
        }
        return convertView;
    }
    class ViewHolder{
        TextView text;
        ImageView image1,image2,image3;
    }
}
