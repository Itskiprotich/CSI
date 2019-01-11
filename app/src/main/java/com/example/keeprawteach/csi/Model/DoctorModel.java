package com.example.keeprawteach.csi.Model;

public class DoctorModel {

    String name,identity,speciality,gender,phone,username,password;

    public DoctorModel(String name, String identity, String speciality, String gender, String phone, String username, String password) {
        this.name = name;
        this.identity = identity;
        this.speciality = speciality;
        this.gender = gender;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public DoctorModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
