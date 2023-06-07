package com.example.bali_ratn_island;

import java.util.ArrayList;

public class chef_displayy_order_model {
    String crtime_crdate;
    int order_amount;
    chef_dsiplay_order_adapter adapter2;
    String Table_number;

    public chef_displayy_order_model(String crtime_crdate, int order_amount, chef_dsiplay_order_adapter adapter2, String table_number) {
        this.crtime_crdate = crtime_crdate;
        this.order_amount = order_amount;
        this.adapter2 = adapter2;
        Table_number = table_number;
    }

    public chef_displayy_order_model() {

    }

    public String getCrtime_crdate() {
        return crtime_crdate;
    }

    public void setCrtime_crdate(String crtime_crdate) {
        this.crtime_crdate = crtime_crdate;
    }

    public int getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(int order_amount) {
        this.order_amount = order_amount;
    }

    public chef_dsiplay_order_adapter getAdapter2() {
        return adapter2;
    }

    public void setAdapter2(chef_dsiplay_order_adapter adapter2) {
        this.adapter2 = adapter2;
    }

    public String getTable_number() {
        return Table_number;
    }

    public void setTable_number(String table_number) {
        Table_number = table_number;
    }
}
