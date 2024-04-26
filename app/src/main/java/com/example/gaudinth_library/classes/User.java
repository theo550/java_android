package com.example.gaudinth_library.classes;

import android.text.EmojiConsistency;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public long uid;
    protected String user_name;
    protected String user_password;
    protected String user_avatar;

    public User(String user_name, String user_password, String user_avatar) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_avatar = user_avatar;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getUid() {
        return uid;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String toString(User user) {
        return "user_name:" + user.user_name + "," + "avatar:" + user.user_avatar;
    }

}
