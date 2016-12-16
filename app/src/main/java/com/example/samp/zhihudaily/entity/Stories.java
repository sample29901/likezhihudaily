package com.example.samp.zhihudaily.entity;

import java.util.List;

import static android.R.attr.type;
/**
 * Created by samp on 16-12-15.
 */

public class Stories {
    /**
     * images : ["http://pic4.zhimg.com/6c1d7339916365b195b25b8e26a37d77.jpg"]
     * type : 0
     * id : 9068170
     * ga_prefix : 121514
     * title : 所谓「母乳过稀、质量不好」的问题，根本不存在
     */

    private int id;
    private String title;
    private List<String> images;


    public void setId(int id) {
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(List<String> images) {
        this.images = images;
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

    public List<String> getImages() {
        return images;
    }

    @Override
    public boolean equals(Object obj) {
        Stories s = (Stories)obj;
        return this.id == s.getId();
    }
}