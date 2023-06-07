package com.example.bali_ratn_island;

public class dtbl_model {
    String Table_id,Table_capacity;

    dtbl_model()
    {

    }

    public String getTable_id() {
        return Table_id;
    }

    public void setTable_id(String table_id) {
        Table_id = table_id;
    }

    public dtbl_model(String table_id, String table_capacity) {
        this.Table_id=table_id;
        this.Table_capacity = table_capacity;
    }

    public String getTable_capacity() {
        return Table_capacity;
    }

    public void setTable_capacity(String table_capacity) {
        Table_capacity = table_capacity;
    }
}
