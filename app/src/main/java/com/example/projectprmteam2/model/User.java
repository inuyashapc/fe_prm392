package com.example.projectprmteam2.model;

import java.util.Date;

public class User {
    private int _id;
    private String password;
    private String fullname;
    private String username;
    private String role;
    private int phoneNumber;

    private String address;


    public User() {
    }

    public User(int _id, String password, String fullname, String username, String role, int phoneNumber, String address) {
        this._id = _id;
        this.password = password;
        this.fullname = fullname;
        this.username = username;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
