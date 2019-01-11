package com.example.keeprawteach.csi.Model;

public class Pres {
    String patient,doctor,complain,history,prescription,advice,investigation,time;

    public Pres() {
    }

    public Pres(String patient, String doctor, String complain, String history, String prescription, String advice, String investigation, String time) {
        this.patient = patient;
        this.doctor = doctor;
        this.complain = complain;
        this.history = history;
        this.prescription = prescription;
        this.advice = advice;
        this.investigation = investigation;
        this.time = time;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
