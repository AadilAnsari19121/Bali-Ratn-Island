package com.example.bali_ratn_island;

public class ctmr_chef_order_lisr_model {
    String Time,Table_number;

    public ctmr_chef_order_lisr_model(String time, String table_number) {
        Time = time;
        Table_number = table_number;

    }

    public ctmr_chef_order_lisr_model() {
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTable_number() {
        return Table_number;
    }

    public void setTable_number(String table_number) {
        Table_number = table_number;
    }

}
