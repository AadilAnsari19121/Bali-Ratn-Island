package com.example.bali_ratn_island;

public class bali_payments_model {
    String cr_time;
    int total_payable_amount;
    String Table_number;
    String cr_date;

    public bali_payments_model(String cr_time, int total_payable_amount, String table_number, String cr_date) {
        this.cr_time = cr_time;
        this.total_payable_amount = total_payable_amount;
        Table_number = table_number;
        this.cr_date = cr_date;
    }

    public bali_payments_model() {
    }

    public String getCr_time() {
        return cr_time;
    }

    public void setCr_time(String cr_time) {
        this.cr_time = cr_time;
    }

    public int getTotal_payable_amount() {
        return total_payable_amount;
    }

    public void setTotal_payable_amount(int total_payable_amount) {
        this.total_payable_amount = total_payable_amount;
    }

    public String getTable_number() {
        return Table_number;
    }

    public void setTable_number(String table_number) {
        Table_number = table_number;
    }

    public String getCr_date() {
        return cr_date;
    }

    public void setCr_date(String cr_date) {
        this.cr_date = cr_date;
    }
}
