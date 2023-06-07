package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.bali_ratn_island.databinding.ActivityAdminMenuBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class admin_menu extends drawer_admin {

    ActivityAdminMenuBinding activityBinding;
    RecyclerView rcv_menu;
    FloatingActionButton floatingActionButton_for_menu;
    menu_adapter menu_adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        activityBinding= ActivityAdminMenuBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Menu");

        SearchView searchView=(SearchView) findViewById(R.id.search_tol);
        searchView.setVisibility(View.VISIBLE);

        rcv_menu=findViewById(R.id.menu_rcv);
        floatingActionButton_for_menu=findViewById(R.id.float_btn_for_menu);

        floatingActionButton_for_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_menu.this,admin_add_menu.class));
            }
        });

        rcv_menu.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<menu_item_model> options =
                new FirebaseRecyclerOptions.Builder<menu_item_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items"), menu_item_model.class)
                        .build();

        menu_adapter=new menu_adapter(options);
        rcv_menu.setAdapter(menu_adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseRecyclerOptions<menu_item_model> options =
                        new FirebaseRecyclerOptions.Builder<menu_item_model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items").orderByChild("menu_item_Name").startAt(newText).endAt(newText+"\uf8ff"), menu_item_model.class)
                                .build();
                menu_adapter = new menu_adapter(options);
                menu_adapter.startListening();
                rcv_menu.setAdapter(menu_adapter);
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        menu_adapter.startListening();
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
        menu_adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        ab.setTitle("Alert...!");
        ab.setMessage("do you want close this app..?");
        ab.setCancelable(false);
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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