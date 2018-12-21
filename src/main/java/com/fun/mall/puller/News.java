package com.fun.mall.puller;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 12:16 2018/12/20
 * @ Description：
 * @ Modified By：
 * @ Version    : $
 */
public class News {
    private int id;
    private String title;
    private String href;
    private String content;
    private String date;
    private String photoUrl;

    public News() {
    }

    public News(String title, String href, String content, int id) {
        this.title = title;
        this.content = content;
        this.href = href;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
