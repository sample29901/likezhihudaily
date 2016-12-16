package com.example.samp.zhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.samp.zhihudaily.R;
import com.example.samp.zhihudaily.activity.DetailActivity;
import com.example.samp.zhihudaily.entity.LatestNews;
import com.example.samp.zhihudaily.entity.Stories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samp on 16-12-15.
 */

public class LatestNewsAdapter extends BaseAdapter {

    private List<Stories> newsList = new ArrayList<>();
    private Context context;

    public LatestNewsAdapter(Context context,List<Stories> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //绑定视图,使用viewholder,优化了findviewbyid
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(context,R.layout.lv_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_content = (ImageView) convertView.findViewById(R.id.iv_content);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.cardView = (CardView) convertView.findViewById(R.id.cardView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //绑定数据
        final Stories stories = (Stories) getItem(position);
        viewHolder.tv_title.setText(stories.getTitle());
        //设置图片
        Glide.with(context).load(stories.getImages().get(0)).into(viewHolder.iv_content);

        //设置点击事件
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",stories.getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        private CardView cardView;
        private ImageView iv_content;
        private TextView tv_title;
    }
}
