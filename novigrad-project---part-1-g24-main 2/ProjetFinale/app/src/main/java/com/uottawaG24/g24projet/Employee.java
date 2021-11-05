package com.uottawaG24.g24projet;

public class Employee  {

    public String userName;
    public String password;
    public String userType;

    public Employee(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userType = "Employee";
    }

    public Employee() {
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
