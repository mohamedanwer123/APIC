package com.example.cm.socialapp.Models;

/**
 * Created by cm on 22/03/2018.
 */

public class photoData {

    String name,describtion,url,comment,like,userimage;


    public photoData(){}

    public photoData(String name, String describtion, String url,String comment , String like , String userimage) {

        this.name = name;
        this.describtion = describtion;
        this.url = url;
        this.comment = comment;
        this.like = like;
        this.userimage = userimage;

    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
