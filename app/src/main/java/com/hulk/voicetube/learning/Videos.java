package com.hulk.voicetube.learning;

import java.io.Serializable;

/**
 * Created by Administrator on 13/8/2017.
 */

public class Videos implements Serializable {
    //新闻标题，内容，图片
    private String title;
    private String desc;
    private int photoId;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Videos(String title, String desc, int photoId) {
        this.title = title;
        this.desc = desc;
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

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
