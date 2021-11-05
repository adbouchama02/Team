package com.uottawaG24.g24projet;

import java.util.Random;

public class Client {
    public String username;
    public String password;
    public String userType;

    public Client(String uName, String uPass){
        //Sets username and password to desired username and password
        username = uName;
        password = uPass;
        userType = "Client";
    }

    public Client(){
    }

    //Access to username
    public String getUsername(){ return username; }

    //Access to password
    public String getPassword(){ return password; }

    //Access to users type
    public String getUserType(){ return userType; }
}
