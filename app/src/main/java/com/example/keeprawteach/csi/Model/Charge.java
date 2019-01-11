package com.example.keeprawteach.csi.Model;

public class Charge {

    String outdoor, investigation, pharmacy, admission, discharge, extra, time, namehere, status,total,id;

    public Charge() {
    }

    public Charge(String outdoor, String investigation, String pharmacy, String admission, String discharge, String extra, String time, String namehere, String status, String total, String id) {
        this.outdoor = outdoor;
        this.investigation = investigation;
        this.pharmacy = pharmacy;
        this.admission = admission;
        this.discharge = discharge;
        this.extra = extra;
        this.time = time;
        this.namehere = namehere;
        this.status = status;
        this.total = total;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(String outdoor) {
        this.outdoor = outdoor;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getDischarge() {
        return discharge;
    }

    public void setDischarge(String discharge) {
        this.discharge = discharge;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNamehere() {
        return namehere;
    }

    public void setNamehere(String namehere) {
        this.namehere = namehere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}