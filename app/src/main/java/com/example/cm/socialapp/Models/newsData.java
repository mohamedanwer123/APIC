package com.example.cm.socialapp.Models;

/**
 * Created by cm on 21/03/2018.
 */

public class newsData {

    String user_name;
    String post;

    public newsData(String user_name, String post) {
        this.user_name = user_name;
        this.post = post;

    }

    public String getUser_name() {
        return user_name;
    }

    public String getPost() {
        return post;
    }

}
