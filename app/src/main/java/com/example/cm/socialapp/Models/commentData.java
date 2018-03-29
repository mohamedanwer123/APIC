package com.example.cm.socialapp.Models;

/**
 * Created by cm on 26/03/2018.
 */

public class commentData {

    String name,comment,img;

    public commentData(String name, String comment,String img) {
        this.name = name;
        this.comment = comment;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
