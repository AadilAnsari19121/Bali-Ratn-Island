package com.example.bali_ratn_island;

import java.io.Serializable;

public class CTMR_cart_model implements Serializable {
    String item_name;
    String item_price;
    String item_quantity;
    String cr_time;
    String cr_date;
    String tb_num;
    String item_name_X_item_quantity;
    String item_price_X_item_quantity;
    String item_price_X_item_quantity_eq_item_total_pr;
    int item_total_price;

    public CTMR_cart_model() {
    }

    public CTMR_cart_model(String item_name, String item_price, String item_quantity, String cr_time, String cr_date, String tb_num, String item_name_X_item_quantity, String item_price_X_item_quantity, String item_price_X_item_quantity_eq_item_total_pr, int item_total_price) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.cr_time = cr_time;
        this.cr_date = cr_date;
        this.tb_num = tb_num;
        this.item_name_X_item_quantity = item_name_X_item_quantity;
        this.item_price_X_item_quantity = item_price_X_item_quantity;
        this.item_price_X_item_quantity_eq_item_total_pr = item_price_X_item_quantity_eq_item_total_pr;
        this.item_total_price = item_total_price;
    }



    public String getItem_price_X_item_quantity_eq_item_total_pr() {
        return item_price_X_item_quantity_eq_item_total_pr;
    }

    public void setItem_price_X_item_quantity_eq_item_total_pr(String item_price_X_item_quantity_eq_item_total_pr) {
        this.item_price_X_item_quantity_eq_item_total_pr = item_price_X_item_quantity_eq_item_total_pr;
    }

    public String getItem_name_X_item_quantity() {
        return item_name_X_item_quantity;
    }

    public void setItem_name_X_item_quantity(String item_name_X_item_quantity) {
        this.item_name_X_item_quantity = item_name_X_item_quantity;
    }

    public String getItem_price_X_item_quantity() {
        return item_price_X_item_quantity;
    }

    public void setItem_price_X_item_quantity(String item_price_X_item_quantity) {
        this.item_price_X_item_quantity = item_price_X_item_quantity;
    }

    public String getTb_num() {
        return tb_num;
    }

    public void setTb_num(String tb_num) {
        this.tb_num = tb_num;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getCr_time() {
        return cr_time;
    }

    public void setCr_time(String cr_time) {
        this.cr_time = cr_time;
    }

    public String getCr_date() {
        return cr_date;
    }

    public void setCr_date(String cr_date) {
        this.cr_date = cr_date;
    }

    public int getItem_total_price() {
        return item_total_price;
    }

    public void setItem_total_price(int item_total_price) {
        this.item_total_price = item_total_price;
    }
}
