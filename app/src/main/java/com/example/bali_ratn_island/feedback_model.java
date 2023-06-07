package com.example.bali_ratn_island;

public class feedback_model {
    String Customer_name;
    String Customer_phone;
    String Customer_mail;
    String Customer_longmsg;
    String Customer_food_rating;
    String Customer_service_rating;
    String tb_num;
    String cr_date;
    String cr_time;

    public feedback_model(String customer_name, String customer_phone, String customer_mail, String customer_longmsg, String customer_food_rating, String customer_service_rating, String tb_num, String cr_date, String cr_time) {
        Customer_name = customer_name;
        Customer_phone = customer_phone;
        Customer_mail = customer_mail;
        Customer_longmsg = customer_longmsg;
        Customer_food_rating = customer_food_rating;
        Customer_service_rating = customer_service_rating;
        this.tb_num = tb_num;
        this.cr_date = cr_date;
        this.cr_time = cr_time;
    }

    public feedback_model() {
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return Customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        Customer_phone = customer_phone;
    }

    public String getCustomer_mail() {
        return Customer_mail;
    }

    public void setCustomer_mail(String customer_mail) {
        Customer_mail = customer_mail;
    }

    public String getCustomer_longmsg() {
        return Customer_longmsg;
    }

    public void setCustomer_longmsg(String customer_longmsg) {
        Customer_longmsg = customer_longmsg;
    }

    public String getCustomer_food_rating() {
        return Customer_food_rating;
    }

    public void setCustomer_food_rating(String customer_food_rating) {
        Customer_food_rating = customer_food_rating;
    }

    public String getCustomer_service_rating() {
        return Customer_service_rating;
    }

    public void setCustomer_service_rating(String customer_service_rating) {
        Customer_service_rating = customer_service_rating;
    }

    public String getTb_num() {
        return tb_num;
    }

    public void setTb_num(String tb_num) {
        this.tb_num = tb_num;
    }

    public String getCr_date() {
        return cr_date;
    }

    public void setCr_date(String cr_date) {
        this.cr_date = cr_date;
    }

    public String getCr_time() {
        return cr_time;
    }

    public void setCr_time(String cr_time) {
        this.cr_time = cr_time;
    }
}
