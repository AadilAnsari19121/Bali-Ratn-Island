package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bali_ratn_island.databinding.ActivityAdminCartBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class admin_cart extends drawer_admin {

    ActivityAdminCartBinding activityBinding;
    FloatingActionButton ftb;
    RecyclerView rv;
    rcv_adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cart);

        activityBinding= ActivityAdminCartBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Category");

        SearchView searchView=(SearchView) findViewById(R.id.search_tol);
        searchView.setVisibility(View.VISIBLE);

        ftb=findViewById(R.id.float_btn_for_cat);
        rv=findViewById(R.id.rcv69);



        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_cart.this,admin_add_cat.class));
            }
        });


        rv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<rcv_model> options =
                new FirebaseRecyclerOptions.Builder<rcv_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category_table"), rcv_model.class)
                        .build();

        adapter=new rcv_adapter(options);
//        rv.setAdapter(adapter);
        rv.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseRecyclerOptions<rcv_model> options =
                        new FirebaseRecyclerOptions.Builder<rcv_model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("category_table").orderByChild("cat_name").startAt(newText).endAt(newText+"\uf8ff"), rcv_model.class)
                                .build();
                adapter = new rcv_adapter(options);
                adapter.startListening();
                rv.setAdapter(adapter);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
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
        adapter.stopListening();
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