package com.example.samp.zhihudaily.utils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by samp on 16-12-13.
 */

public class OkhttpUtils {

    public static OkHttpClient client = new OkHttpClient();
    private Request request;
    private Call call;


    public static OkHttpClient getInstance(){
        return client;
    }



}
