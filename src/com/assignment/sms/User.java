package com.assignment.sms;

public class User {
     int id;
     String username;
     String password;
     String type;

     String address ;
     String phone;
     String email ;

    public User(int id, String username, String password, String type, String address, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phone  = phone;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nEmail: " + email + "\nAddress: " + address + "\nPhone: " + phone;
    }
}
