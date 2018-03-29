package com.example.cm.socialapp.Models;

import android.net.Uri;

/**
 * Created by cm on 28/03/2018.
 */

public class SignupData {

     String name, pass ,  email ,  phone  , img;

      public  SignupData(){}
    public SignupData(String name, String pass, String email, String phone, String img) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
