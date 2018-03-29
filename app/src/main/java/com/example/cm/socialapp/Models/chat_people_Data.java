package com.example.cm.socialapp.Models;

/**
 * Created by cm on 29/03/2018.
 */

public class chat_people_Data {

    String name , img ;

    public chat_people_Data(){}

    public chat_people_Data(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
