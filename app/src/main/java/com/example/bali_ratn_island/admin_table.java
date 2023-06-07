package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bali_ratn_island.databinding.ActivityAdminTableBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class admin_table extends drawer_admin {
    ActivityAdminTableBinding activityBinding;

    RecyclerView rcv_dining_table;
    FloatingActionButton dining_table_add_btn;
    dtbl_adapter dtbl_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_table);
        setTitle("Table");

        activityBinding= ActivityAdminTableBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Table");

        dining_table_add_btn=findViewById(R.id.float_btn_for_table);
        rcv_dining_table=findViewById(R.id.table_rcv);
        rcv_dining_table.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<dtbl_model> options =
                new FirebaseRecyclerOptions.Builder<dtbl_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dining_table"), dtbl_model.class)
                        .build();

        dtbl_adapter=new dtbl_adapter(options);
        rcv_dining_table.setAdapter(dtbl_adapter);

        dining_table_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_table.this,admin_add_dining_table.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        dtbl_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dtbl_adapter.stopListening();
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