package com.example.manastuspace.ui.home;

public class UserHelperClass {
    String email,name,address,date,phno;

    public UserHelperClass() {
    }
    public UserHelperClass(String email, String name, String address, String date, String phno) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.date = date;
        this.phno = phno;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
