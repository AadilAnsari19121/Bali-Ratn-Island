package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.bali_ratn_island.databinding.AdminActivityTableLiveStatusBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class admin_table_live_status extends drawer_admin {
    AdminActivityTableLiveStatusBinding activityBinding;
    RecyclerView rcv_table_live;
    table_live_status table_live_status;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_table_live_status);

        activityBinding= AdminActivityTableLiveStatusBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Table Status");

        rcv_table_live=findViewById(R.id.table_live_status_rcv);
        rcv_table_live.setLayoutManager(new GridLayoutManager(this,2));

        FirebaseRecyclerOptions<dtbl_model> options11 =
                new FirebaseRecyclerOptions.Builder<dtbl_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dining_table"), dtbl_model.class)
                        .build();

        table_live_status=new table_live_status(options11);
        rcv_table_live.setAdapter(table_live_status);
    }

    @Override
    protected void onStart() {
        super.onStart();
        table_live_status.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        table_live_status.stopListening();
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