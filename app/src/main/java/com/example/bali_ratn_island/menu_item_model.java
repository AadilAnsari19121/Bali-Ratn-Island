package com.example.bali_ratn_island;

public class menu_item_model {

    String menu_item_Id,menu_item_Name,menu_item_Price,menu_item_Cat;

    menu_item_model()
    {

    }

    public menu_item_model(String menu_item_Id, String menu_item_Name, String menu_item_Price, String menu_item_Cat) {
        this.menu_item_Id = menu_item_Id;
        this.menu_item_Name = menu_item_Name;
        this.menu_item_Price = menu_item_Price;
        this.menu_item_Cat = menu_item_Cat;
    }

    public String getMenu_item_Id() {
        return menu_item_Id;
    }

    public void setMenu_item_Id(String menu_item_Id) {
        this.menu_item_Id = menu_item_Id;
    }

    public String getMenu_item_Name() {
        return menu_item_Name;
    }

    public void setMenu_item_Name(String menu_item_Name) {
        this.menu_item_Name = menu_item_Name;
    }

    public String getMenu_item_Price() {
        return menu_item_Price;
    }

    public void setMenu_item_Price(String menu_item_Price) {
        this.menu_item_Price = menu_item_Price;
    }

    public String getMenu_item_Cat() {
        return menu_item_Cat;
    }

    public void setMenu_item_Cat(String menu_item_Cat) {
        this.menu_item_Cat = menu_item_Cat;
    }
}
