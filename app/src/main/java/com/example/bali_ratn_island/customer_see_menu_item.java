package com.example.bali_ratn_island;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bali_ratn_island.databinding.ActivityCustomerSeeMenuItemBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class customer_see_menu_item extends drawer_ctmr{

    ActivityCustomerSeeMenuItemBinding activityCustomerSeeMenuItemBinding;

    List<Item> items;
    ArrayList arrayList;
    RecyclerView rcv;
    TextView catnm;
    TextView searchtb;
    Button cart_btn;
    CircleImageView cat_img_in_rcv;
    xyz_adapter xyz_adapter2,xyz_adapter3;
    //String [] qun={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_see_menu_item);

        activityCustomerSeeMenuItemBinding=ActivityCustomerSeeMenuItemBinding.inflate(getLayoutInflater());
        setContentView(activityCustomerSeeMenuItemBinding.getRoot());
        allactivityy2("Menu");

        SearchView searchView=(SearchView) findViewById(R.id.search_tol);
        searchView.setVisibility(View.VISIBLE);

        cart_btn=findViewById(R.id.customer_btn_cart);
        rcv=findViewById(R.id.customer_menu_item_rcv);
        catnm=findViewById(R.id.cat_name_in_rcv);
        searchtb=findViewById(R.id.search_in_rcv);
        cat_img_in_rcv=findViewById(R.id.circleImageView);



        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(customer_see_menu_item.this,customer_see_cart.class));
            }
        });

        String cat_name_from_cust,cat_image_from_cust;
        cat_name_from_cust=getIntent().getStringExtra("cust_cat_name_35");
        cat_image_from_cust=getIntent().getStringExtra("cust_cat_pic_35");
        Glide.with(this).load(cat_image_from_cust).into(cat_img_in_rcv);
        catnm.setText(cat_name_from_cust);
        searchtb.setText(cat_name_from_cust);





        //rc.setAdapter(xyz_adapter2);
        rcv.setLayoutManager(new LinearLayoutManager(this));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseRecyclerOptions<menu_item_model> options9 =
                        new FirebaseRecyclerOptions.Builder<menu_item_model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items").orderByChild("menu_item_Name").startAt(newText).endAt(newText+"\uf8ff"), menu_item_model.class)
                                .build();
                xyz_adapter2 = new xyz_adapter(options9);
                xyz_adapter2.startListening();
                rcv.setAdapter(xyz_adapter2);
                return false;
            }
        });
//        FirebaseRecyclerOptions<menu_item_model> options8 =
//                new FirebaseRecyclerOptions.Builder<menu_item_model>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items"), menu_item_model.class)
//                        .build();
//
//        xyz_adapter2 = new xyz_adapter(options8);
//        rcv.setAdapter(xyz_adapter2);

        if (cat_image_from_cust!=null)
        {
            FirebaseRecyclerOptions<menu_item_model> options9 =
                    new FirebaseRecyclerOptions.Builder<menu_item_model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items").orderByChild("menu_item_Cat").startAt(cat_name_from_cust).endAt(cat_name_from_cust+"\uf8ff"), menu_item_model.class)
                            .build();
            xyz_adapter2 = new xyz_adapter(options9);
            xyz_adapter2.startListening();
            rcv.setAdapter(xyz_adapter2);


        }

        else if (cat_image_from_cust==null)
        {
            FirebaseRecyclerOptions<menu_item_model> options9 =
                    new FirebaseRecyclerOptions.Builder<menu_item_model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items"), menu_item_model.class)
                            .build();
            xyz_adapter2 = new xyz_adapter(options9);
            xyz_adapter2.startListening();
            rcv.setAdapter(xyz_adapter2);

            catnm.setVisibility(View.GONE);
            cat_img_in_rcv.setVisibility(View.GONE);
            searchtb.setVisibility(View.GONE);

        }
        else
        {
            FirebaseRecyclerOptions<menu_item_model> options9 =
                    new FirebaseRecyclerOptions.Builder<menu_item_model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items"), menu_item_model.class)
                            .build();
            xyz_adapter2 = new xyz_adapter(options9);
            xyz_adapter2.startListening();
            rcv.setAdapter(xyz_adapter2);

        }





//        searchtb.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                String search_name=searchtb.getText().toString().trim();
//                FirebaseRecyclerOptions<menu_item_model> options10 =
//                        new FirebaseRecyclerOptions.Builder<menu_item_model>()
//                                .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items").orderByChild("menu_item_Name").startAt(search_name).endAt(search_name+"\uf8ff"), menu_item_model.class)
//                                .build();
//                xyz_adapter2 = new xyz_adapter(options10);
//                xyz_adapter2.startListening();
//                rcv.setAdapter(xyz_adapter2);
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
////                FirebaseRecyclerOptions<menu_item_model> options10 =
////                        new FirebaseRecyclerOptions.Builder<menu_item_model>()
////                                .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items").orderByChild("menu_item_Name").startAt(cat_name_from_cust).endAt(cat_name_from_cust+"\uf8ff"), menu_item_model.class)
////                                .build();
////                xyz_adapter3 = new xyz_adapter(options10);
////                xyz_adapter3.startListening();
////                rcv.setAdapter(xyz_adapter3);
//
//            }
//        });


//        String cat_name_from_cust,cat_image_from_cust;
//        cat_name_from_cust=getIntent().getStringExtra("cust_cat_name_35");
//        cat_image_from_cust=getIntent().getStringExtra("cust_cat_pic_35");
//
//        Toast.makeText(this, "this is "+cat_name_from_cust, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,cutomer_category.class ));
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        xyz_adapter2.startListening();
       // xyz_adapter3.startListening();;

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        //man_table_book_adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
    @Override
    protected void onStop() {
        super.onStop();
        xyz_adapter2.stopListening();
      //  xyz_adapter3.startListening();;


    }

    private class Item {

    }
}