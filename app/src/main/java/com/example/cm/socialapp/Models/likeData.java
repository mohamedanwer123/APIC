package com.example.cm.socialapp.Models;

/**
 * Created by cm on 26/03/2018.
 */

public class likeData {

    String name , count;

    public likeData(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
