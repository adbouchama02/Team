package com.uottawaG24.g24projet;

public class Admin {
    public String username;
    public String password;
    public String userType;

    public Admin (){
        //Predetermined username and password
        username = "admin";
        password = "123admin456";
        userType = "Administrator";
    }

    //Access to username
    public String getUsername(){
        return username;
    }

    //Access to password
    public String getPassword(){
        return password;
    }

    //Access to users type
    public String getUserType(){ return userType; }
}
