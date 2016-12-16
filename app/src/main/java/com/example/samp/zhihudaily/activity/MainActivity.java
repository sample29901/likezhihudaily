package com.example.samp.zhihudaily.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.example.samp.zhihudaily.R;
import com.example.samp.zhihudaily.adapter.LatestNewsAdapter;
import com.example.samp.zhihudaily.entity.LatestNews;
import com.example.samp.zhihudaily.entity.Stories;
import com.example.samp.zhihudaily.entity.TopStories;
import com.example.samp.zhihudaily.utils.GlideImageLoader;
import com.example.samp.zhihudaily.utils.OkhttpUtils;
import com.example.samp.zhihudaily.utils.ZhihuApiUtils;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.samp.zhihudaily.utils.OkhttpUtils.client;

public class MainActivity extends Activity {

    private static String LOG_TAG = MainActivity.class.getName();

    private final int DUR_TIME = 2000;
    private final int STORY = 0;
    private final int TOP_STORY = 1;

    private boolean first = true;

    private String curDate = "";

    private ListView lv_content;
    private SwipeRefreshLayout refreshLayout;
    private Banner banner;

    private List<String> bannerImages = new ArrayList<>();
    private List<String> bannerTitles = new ArrayList<>();
    private List<TopStories> topStoriesList = new ArrayList<>();
    private LatestNews news ;
    private LatestNewsAdapter adapter;
    private List<Stories> storyList = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case STORY:
                    adapter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                    break;
                case TOP_STORY:

                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        lv_content = (ListView) findViewById(R.id.lv_content);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);

        refreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.cyan,R.color.crimson);
        refreshLayout.setProgressViewEndTarget(true, 100);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        banner.setImageLoader(new GlideImageLoader());

        adapter = new LatestNewsAdapter(this,storyList);
        lv_content.setAdapter(adapter);
    }


    private void getData(){
        OkHttpClient client = OkhttpUtils.getInstance();
        if (TextUtils.isEmpty(curDate)){
            Request request = new Request.Builder().url(ZhihuApiUtils.LATEST_NEWS).build();
            enqueue(client,request);
        }else {
            int tempDate = Integer.parseInt(curDate);
            tempDate--;
            Request request = new Request.Builder().url(ZhihuApiUtils.BEFORE_NEWS + tempDate).build();
            enqueue(client,request);
        }

    }

    private void enqueue(OkHttpClient client,Request request){
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(LOG_TAG,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                news = new Gson().fromJson(response.body().string(),LatestNews.class);
                curDate = news.getDate();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addData(news.getStories());
                        if (news.getTop_stories() != null){
                            addDataTop(news.getTop_stories());
                        }
                    }
                });
            }
        });
    }

    private void addDataTop(List<TopStories> top_stories) {
        for (TopStories story : top_stories){
            if (!topStoriesList.contains(story)){
                bannerTitles.add(story.getTitle());
                bannerImages.add(story.getImage());
            }
        }
        banner.setImages(bannerImages);
        banner.start();
    }

    private void addData(List<Stories> list){
        for (Stories story : list){
            if (!storyList.contains(story)){
                storyList.add(story);
            }
        }
        if (first){
            adapter.notifyDataSetChanged();
            first = false;
        }else{
            handler.sendEmptyMessageDelayed(STORY,DUR_TIME);
        }

    }
}
