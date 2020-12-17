package com.app.sqlitedemo.Model;

public class RetrieveDataModel {
    String name,course,contact,total_fee,fee_paid;

    public RetrieveDataModel(String name, String course, String contact, String total_fee, String fee_paid) {
        this.name = name;
        this.course = course;
        this.contact = contact;
        this.total_fee = total_fee;
        this.fee_paid = fee_paid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_paid() {
        return fee_paid;
    }

    public void setFee_paid(String fee_paid) {
        this.fee_paid = fee_paid;
    }
}
