package com.example.bali_ratn_island;

public class staff_model {
    String Fnamen,Lname,Age,Phone,Mail,Staff_id,Address,Pincode,Edu_q,Gander,State,Pos_of_staff,Face,Filepath,Pwd;

    public staff_model(String fnamen, String lname, String age, String phone, String mail, String staff_id, String address, String pincode, String edu_q, String gander, String state, String pos_of_staff, String face, String filepath,String pwd) {
        Fnamen = fnamen;
        Lname = lname;
        Age = age;
        Phone = phone;
        Mail = mail;
        Staff_id = staff_id;
        Address = address;
        Pincode = pincode;
        Edu_q = edu_q;
        Gander = gander;
        State = state;
        Pos_of_staff = pos_of_staff;
        Face = face;
        Filepath = filepath;
        Pwd = pwd;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        this.Pwd = pwd;
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

    public String getGander() {
        return Gander;
    }

    public void setGander(String gander) {
        Gander = gander;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPos_of_staff() {
        return Pos_of_staff;
    }

    public void setPos_of_staff(String pos_of_staff) {
        Pos_of_staff = pos_of_staff;
    }

    public String getFace() {
        return Face;
    }

    public void setFace(String face) {
        Face = face;
    }

    public String getFilepath() {
        return Filepath;
    }

    public void setFilepath(String filepath) {
        Filepath = filepath;
    }
}
