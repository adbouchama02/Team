package com.uottawaG24.g24projet;

public class Employee extends Client{
    public Employee (String uName, String uPass) {
        //Sets username and password to desired username and password
        username = uName;
        password = uPass;
        userType = "Employee";
    }
}
