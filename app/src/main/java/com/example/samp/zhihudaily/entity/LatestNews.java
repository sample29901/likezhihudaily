package com.example.samp.zhihudaily.entity;

import java.util.List;

import static android.R.attr.type;

/**
 * Created by samp on 16-12-15.
 */

public class LatestNews {


    /**
     * date : 20161215
     * stories : [{"images":["http://pic4.zhimg.com/6c1d7339916365b195b25b8e26a37d77.jpg"],"type":0,"id":9068170,"ga_prefix":"121514","title":"所谓「母乳过稀、质量不好」的问题，根本不存在"},{"images":["http://pic4.zhimg.com/ab67d1d402db1b04f28e5847ee8d8c27.jpg"],"type":0,"id":9066682,"ga_prefix":"121513","title":"担心果蔬有农药残留，有必要买「超声波清洗机」吗？"},{"images":["http://pic1.zhimg.com/ab3d2c2ccf4e027a126d3dc114f30694.jpg"],"type":0,"id":9064955,"ga_prefix":"121512","title":"大误 · ki 米喏哪麦伊哇\u2014\u2014"},{"images":["http://pic4.zhimg.com/f2675ddd64a0fc8214e73896ffa60363.jpg"],"type":0,"id":9065438,"ga_prefix":"121511","title":"国漫逐渐发展，漫画网站的知识产权界定也不容易"},{"images":["http://pic2.zhimg.com/c30e19cbb3db6a1092fdc75a20ef064d.jpg"],"type":0,"id":9066705,"ga_prefix":"121510","title":"手机没联网，支付宝也支付成功了，这\u2026\u2026靠谱吗？"},{"images":["http://pic2.zhimg.com/a24a36f561824dbbb49d8aac6845f3fd.jpg"],"type":0,"id":9066687,"ga_prefix":"121509","title":"现实就在眼前，为什么经济学家非得「建个模型」？"},{"images":["http://pic3.zhimg.com/bddc2f0abed2654ef21d491d833a0f96.jpg"],"type":0,"id":9063425,"ga_prefix":"121508","title":"冬天去日本，这些都是不可错过的特选「限定美食」"},{"images":["http://pic3.zhimg.com/0c231a66280279647c89f28ba7801fce.jpg"],"type":0,"id":9065973,"ga_prefix":"121507","title":"害怕感染 HIV，口交会比较安全吗？"},{"images":["http://pic4.zhimg.com/9a92f631a47ff78754d0367cd6d8d323.jpg"],"type":0,"id":9066732,"ga_prefix":"121507","title":"发明了全美国最火的一道中国菜，他的人生简直就是近代史"},{"images":["http://pic1.zhimg.com/1112e59ec512b9779eef44f29f1c5118.jpg"],"type":0,"id":9065677,"ga_prefix":"121507","title":"银联推出二维码支付，一纸文件引发蝴蝶效应"},{"images":["http://pic3.zhimg.com/b74d64d96b15834a15b21d088880e186.jpg"],"type":0,"id":9066423,"ga_prefix":"121507","title":"读读日报 24 小时热门 TOP 5 · 朴槿惠被弹劾因一条狗"},{"images":["http://pic4.zhimg.com/aab6bb210bca7811be94d6cd01f84def.jpg"],"type":0,"id":9066765,"ga_prefix":"121506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/414d7a79d4df403698c7ef20d72111eb.jpg","type":0,"id":9065677,"ga_prefix":"121507","title":"银联推出二维码支付，一纸文件引发蝴蝶效应"},{"image":"http://pic2.zhimg.com/d21cb1438f8d140463de985d465dd081.jpg","type":0,"id":9066732,"ga_prefix":"121507","title":"发明了全美国最火的一道中国菜，他的人生简直就是近代史"},{"image":"http://pic2.zhimg.com/cfa05454d566485d4a28a1b62f75563d.jpg","type":0,"id":9066705,"ga_prefix":"121510","title":"手机没联网，支付宝也支付成功了，这\u2026\u2026靠谱吗？"},{"image":"http://pic2.zhimg.com/5fb7d9ea7e1a31f3e7e366a4d9758ecd.jpg","type":0,"id":9066423,"ga_prefix":"121507","title":"读读日报 24 小时热门 TOP 5 · 朴槿惠被弹劾因一条狗"},{"image":"http://pic3.zhimg.com/f4825a9f42c749875e828bb0df03311e.jpg","type":0,"id":9065662,"ga_prefix":"121420","title":"冬天还是不够冷，我要去西伯利亚感受一下"}]
     */

    private String date;
    private List<Stories> stories;
    private List<TopStories> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStories> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }




}
