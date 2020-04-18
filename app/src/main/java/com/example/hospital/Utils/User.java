package com.example.hospital.Utils;

public class User {

    private static User user = null;

    private User(){
    }
    public static User getInstance()
    {
        if (user == null)
            user = new User();
        return user;
    }
    private String userName;
    private String lastName;
    private String email;
    private String PhoneNumer;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumer() {
        return PhoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        PhoneNumer = phoneNumer;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
