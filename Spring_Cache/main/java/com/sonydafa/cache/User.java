package com.sonydafa.cache;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String userName;
    private int age;
    public User(){}
    public User(String userId,String userName){
        this.userId=userId;
        this.userName=userName;
    }
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
