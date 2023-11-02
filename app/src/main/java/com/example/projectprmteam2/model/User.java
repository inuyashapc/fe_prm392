package com.example.projectprmteam2.model;

import java.util.Date;

public class User {
    private String _id;
    private String username;
    private String password;
    private String fullname;
    private String role;
    private int phoneNumber;

    private String address;


    public User() {
    }

    public User(String username,String password) {
        this.password = password;
        this.username = username;
    }



    public User(String password, String fullname, String username, String role, int phoneNumber, String address) {
        this.password = password;
        this.fullname = fullname;
        this.username = username;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String get_id() {
        return _id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
