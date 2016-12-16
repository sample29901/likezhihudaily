package com.example.samp.zhihudaily.entity;

import static android.R.attr.type;

/**
 * Created by samp on 16-12-15.
 */

public  class TopStories {
    /**
     * image : http://pic4.zhimg.com/414d7a79d4df403698c7ef20d72111eb.jpg
     * type : 0
     * id : 9065677
     * ga_prefix : 121507
     * title : 银联推出二维码支付，一纸文件引发蝴蝶效应
     */

    private String image;
    private int id;
    private String title;

    public void setImage(String image) {
        this.image = image;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }
}