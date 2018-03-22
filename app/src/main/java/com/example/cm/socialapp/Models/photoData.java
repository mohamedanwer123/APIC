package com.example.cm.socialapp.Models;

/**
 * Created by cm on 22/03/2018.
 */

public class photoData {

    String name,describtion,url;

    public photoData(String name, String describtion, String url) {

        this.name = name;
        this.describtion = describtion;
        this.url = url;

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
}
