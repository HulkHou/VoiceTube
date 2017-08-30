package com.hulk.voicetube.blog;

import java.io.Serializable;

/**
 * Created by Administrator on 13/8/2017.
 */

public class Blogs implements Serializable {
    //blog图片，标题，创建时间，浏览次数，作者
    private String title;
    private int photoId;
    private String createTime;
    private String pageViews;
    private String author;
    private String blogDesc;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Blogs(String title, int photoId, String createTime, String pageViews, String author, String blogDesc) {
        this.title = title;
        this.photoId = photoId;
        this.createTime = createTime;
        this.pageViews = pageViews;
        this.author = author;
        this.blogDesc = blogDesc;
    }

    public Blogs(String title, int photoId) {
        this.title = title;
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPageViews() {
        return pageViews;
    }

    public void setPageViews(String pageViews) {
        this.pageViews = pageViews;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlogDesc() {
        return blogDesc;
    }

    public void setBlogDesc(String blogDesc) {
        this.blogDesc = blogDesc;
    }
}
