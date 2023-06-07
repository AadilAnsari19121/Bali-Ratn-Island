package com.example.bali_ratn_island;

public class rcv_model {
    String cat_img_url,cat_name;

    rcv_model()
    {

    }

    public rcv_model(String cat_img_url, String cat_name) {
        this.cat_img_url = cat_img_url;
        this.cat_name = cat_name;
    }

    public String getCat_img_url() {
        return cat_img_url;
    }

    public void setCat_img_url(String cat_img_url) {
        this.cat_img_url = cat_img_url;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

}
