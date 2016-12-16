package com.example.samp.zhihudaily.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.samp.zhihudaily.R;
import com.example.samp.zhihudaily.utils.OkhttpUtils;
import com.example.samp.zhihudaily.utils.ZhihuApiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.drm.DrmStore.Playback.RESUME;
import static com.example.samp.zhihudaily.utils.OkhttpUtils.client;

public class SplashActivity extends AppCompatActivity {

    private  static final int TO_MAIN = 1;

    private static final int DUR_TIME = 3000;

    private static final String IMG = "img";
    private static final String AUTHOR = "text";

    private static boolean FIRST = false;

    private String url = "";
    private String author = "";

    private ImageView iv_splash;
    private TextView tv_author;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TO_MAIN:
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (FIRST){
            handler.sendEmptyMessage(TO_MAIN);
        }else {
            initView();
            getImage();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        FIRST = true;
    }

    private void initView(){
        iv_splash = (ImageView) findViewById(R.id.iv_splash);
        tv_author = (TextView) findViewById(R.id.tv_author);
    }

    public void getImage() {

        OkHttpClient client = OkhttpUtils.getInstance();
        final Request request = new Request.Builder().url(ZhihuApiUtils.SPLASH_IMAGE_URI).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("SplashActivity", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    url = json.getString(IMG);
                    author = json.getString(AUTHOR);
                    //Log.d("SplashActivity",url);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(SplashActivity.this).load(url).into(iv_splash);
                            tv_author.setText(author);
                        }
                    });
                    handler.sendEmptyMessageDelayed(TO_MAIN,DUR_TIME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
