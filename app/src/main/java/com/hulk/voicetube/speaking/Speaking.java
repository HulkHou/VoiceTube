package com.hulk.voicetube.speaking;

import java.io.Serializable;

/**
 * Created by Administrator on 13/8/2017.
 */

public class Speaking implements Serializable {
    //视频简介图，标题，描述，作者，发布时间
    private int photoId;
    private String title;
    private String desc;
    private String author;
    private String postedTime;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Speaking(int photoId, String title, String desc, String author, String postedTime) {
        this.photoId = photoId;
        this.title = title;
        this.desc = desc;
        this.author = author;
        this.postedTime = postedTime;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }
}
