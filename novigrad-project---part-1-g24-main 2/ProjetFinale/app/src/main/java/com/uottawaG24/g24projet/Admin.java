package com.uottawaG24.g24projet;

public class Admin  {



        public String userName;
        public String password;
        public String userType;

        public Admin(String userName, String password) {
            this.userName = userName;
            this.password = password;
            this.userType = "Admin";
        }

    public Admin() {
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
