package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import com.example.bali_ratn_island.databinding.ActivityMainBinding;
import com.example.bali_ratn_island.databinding.ActivityManagerTableBookBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class manager_table_book extends drawer_man {

    ActivityManagerTableBookBinding activityManagerTableBookBinding;
    RecyclerView rcv_man_booktable;
    man_table_book_adapter man_table_book_adapter;
    String man_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_table_book);

        activityManagerTableBookBinding=ActivityManagerTableBookBinding.inflate(getLayoutInflater());
        setContentView(activityManagerTableBookBinding.getRoot());
        allactivityy("Book Table");
        rcv_man_booktable = findViewById(R.id.man_booking_table_rcv);


        SearchView searchView=(SearchView) findViewById(R.id.search_tol);
        searchView.setVisibility(View.VISIBLE);
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);
        //searchView.setQueryHint("SEARCH CAPICIT");


        //String man_id=getIntent().getStringExtra("id");

        rcv_man_booktable.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<dtbl_model> options =
                new FirebaseRecyclerOptions.Builder<dtbl_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dining_table"), dtbl_model.class)
                        .build();

        man_table_book_adapter=new man_table_book_adapter(options);
        rcv_man_booktable.setAdapter(man_table_book_adapter);

        SharedPreferences sh6 = getSharedPreferences("staff_id_from_login",MODE_PRIVATE);
        man_id = sh6.getString("staff_id_from_login_id", "");




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseRecyclerOptions<dtbl_model> options11 =
                        new FirebaseRecyclerOptions.Builder<dtbl_model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("dining_table").orderByChild("table_capacity").startAt(newText).endAt(newText+"\uf8ff"), dtbl_model.class)
                                .build();
                man_table_book_adapter = new man_table_book_adapter(options11);
                man_table_book_adapter.startListening();
                rcv_man_booktable.setAdapter(man_table_book_adapter);
                return false;
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        man_table_book_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        man_table_book_adapter.stopListening();
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

//    interface OnActivityStateChanged{
//
////        fun onResumed()
////        fun onPaused()
//    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        ab.setTitle("Alert...!");
        ab.setMessage("do you want close this app..?");
        ab.setCancelable(false);
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //manager_home.this.finish();
                        finishAffinity();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        ab.show();
    }
}