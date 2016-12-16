package com.example.samp.zhihudaily.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.samp.zhihudaily.R;
import com.example.samp.zhihudaily.entity.NewsContent;
import com.example.samp.zhihudaily.utils.OkhttpUtils;
import com.example.samp.zhihudaily.utils.ZhihuApiUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.data;

public class DetailActivity extends AppCompatActivity {

    private final int CONTENT = 0;
    private final int CSS = 1;

    private WebView wb_content;
    private ProgressBar progressBar;

    private int newsId;

    private NewsContent newsContent;

    private String css = "<style type=\"text/css\">";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CONTENT:
                    getCss();
                    Log.e("info",newsContent.getBody());
                    break;
                case CSS:
                    wb_content.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    wb_content.loadData(css + newsContent.getBody(),"text/html; charset=UTF-8", null);

                    //会乱码
                    //wb_content.loadData(newsContent.getBody(),"text/html","12");
                    break;
            }

        }
    };

    private void getCss() {
        OkHttpClient client = OkhttpUtils.getInstance();
        Request request = new Request.Builder().url(newsContent.getCss().get(0)).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("DetailActivity",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                css = css + response.body().string() + "\n</style>";
                //Log.d("info",css);
                handler.sendEmptyMessage(CSS);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        newsId = getIntent().getIntExtra("id",0);

        initView();

        getData();

    }

    private void initView() {
        wb_content = (WebView) findViewById(R.id.wb_content);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        wb_content.getSettings().setDefaultTextEncodingName("utf-8");
        progressBar.setVisibility(View.VISIBLE);
    }


    public void getData() {
        if (newsId == 0){
            Toast.makeText(this,"错误的新闻ID!",Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient client = OkhttpUtils.getInstance();
        Request request = new Request.Builder().url(ZhihuApiUtils.NEWS_CONTENT + newsId).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("DetailActivity",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                newsContent = new Gson().fromJson(response.body().string(),NewsContent.class);
                handler.sendEmptyMessage(CONTENT);
            }
        });

    }




}
