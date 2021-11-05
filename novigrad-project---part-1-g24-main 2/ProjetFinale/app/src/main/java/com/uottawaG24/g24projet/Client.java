package com.uottawaG24.g24projet;

import java.util.Random;

public class Client  {
    public String userName;
    public String password;
    public String userType;

    public Client() {
    }

    public Client(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userType = "Client";
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }
}