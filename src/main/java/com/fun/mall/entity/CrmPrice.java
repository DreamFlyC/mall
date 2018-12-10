package com.fun.mall.entity;

import java.util.Date;

public class CrmPrice {
    private Integer id;

    private String number;

    private String cid;

    private String user;

    private String price;

    private String title;

    private String type;

    private String viewid;

    private String cname;

    private Date date;

    private String uid;

    public CrmPrice(Integer id, String number, String cid, String user, String price, String title, String type, String viewid, String cname, Date date, String uid) {
        this.id = id;
        this.number = number;
        this.cid = cid;
        this.user = user;
        this.price = price;
        this.title = title;
        this.type = type;
        this.viewid = viewid;
        this.cname = cname;
        this.date = date;
        this.uid = uid;
    }

    public CrmPrice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getViewid() {
        return viewid;
    }

    public void setViewid(String viewid) {
        this.viewid = viewid == null ? null : viewid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }
}