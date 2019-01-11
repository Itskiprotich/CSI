package com.example.keeprawteach.csi.Model;

public class Upload {

    String name,sex,age,address,condition,time;

    public Upload() {
    }

    public Upload(String name, String sex, String age, String address, String condition, String time) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.condition = condition;
        this.time = time;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
