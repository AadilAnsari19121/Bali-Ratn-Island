package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.bali_ratn_island.databinding.ActivityAdminOrderStatusBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class admin_order_status extends drawer_admin {
    ActivityAdminOrderStatusBinding activityBinding;

    RecyclerView admin_see_orders_rcv;
    man__disp_order_list_adapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_status);
        activityBinding= ActivityAdminOrderStatusBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Orders");

        admin_see_orders_rcv=findViewById(R.id.admin_see_orders_rcv);

        admin_see_orders_rcv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ctmr_chef_order_lisr_model> options25 =
                new FirebaseRecyclerOptions.Builder<ctmr_chef_order_lisr_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ctr_orders2"), ctmr_chef_order_lisr_model.class)
                        .build();

        adapter=new man__disp_order_list_adapter(options25);
        admin_see_orders_rcv.setAdapter(adapter);




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