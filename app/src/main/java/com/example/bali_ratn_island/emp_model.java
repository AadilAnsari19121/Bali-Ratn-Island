package com.example.bali_ratn_island;

public class emp_model {


    String Fnamen,Lname,Age,Phone,Mail,Staff_id,Address,Pincode,Edu_q,Cr_pwd,Cnf_pwd,Gan,State,Poz,Pimg;

    emp_model(){

    }
    public emp_model(String fnamen, String lname, String age, String phone, String mail, String staff_id, String address, String pincode, String edu_q, String cr_pwd, String cnf_pwd, String gan, String state, String poz, String pimg) {
        Fnamen = fnamen;
        Lname = lname;
        Age = age;
        Phone = phone;
        Mail = mail;
        Staff_id = staff_id;
        Address = address;
        Pincode = pincode;
        Edu_q = edu_q;
        Cr_pwd = cr_pwd;
        Cnf_pwd = cnf_pwd;
        Gan = gan;
        State = state;
        Poz = poz;
        Pimg = pimg;
    }

    public String getPimg() {
        return Pimg;
    }

    public void setPimg(String pimg) {
        Pimg = pimg;
    }

    public String getFnamen() {
        return Fnamen;
    }

    public void setFnamen(String fnamen) {
        Fnamen = fnamen;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getStaff_id() {
        return Staff_id;
    }

    public void setStaff_id(String staff_id) {
        Staff_id = staff_id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getEdu_q() {
        return Edu_q;
    }

    public void setEdu_q(String edu_q) {
        Edu_q = edu_q;
    }

    public String getCr_pwd() {
        return Cr_pwd;
    }

    public void setCr_pwd(String cr_pwd) {
        Cr_pwd = cr_pwd;
    }

    public String getCnf_pwd() {
        return Cnf_pwd;
    }

    public void setCnf_pwd(String cnf_pwd) {
        Cnf_pwd = cnf_pwd;
    }

    public String getGan() {
        return Gan;
    }

    public void setGan(String gan) {
        Gan = gan;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPoz() {
        return Poz;
    }

    public void setPoz(String poz) {
        Poz = poz;
    }
}
