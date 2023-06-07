package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.bali_ratn_island.databinding.ActivityMangerBillsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class manger_bills extends drawer_man {

     ActivityMangerBillsBinding activityMangerBillsBinding;

    RecyclerView man_bills_rc;
    man_bills_list_adapter adapter;
    String man_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manger_bills);

        activityMangerBillsBinding=ActivityMangerBillsBinding.inflate(getLayoutInflater());
        setContentView(activityMangerBillsBinding.getRoot());
        allactivityy("Bills");


        man_bills_rc=findViewById(R.id.man_bills_rcv);

        //String man_id=getIntent().getStringExtra("id");

        man_bills_rc.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ctmr_chef_order_lisr_model> options25 =
                new FirebaseRecyclerOptions.Builder<ctmr_chef_order_lisr_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ctr_orders2"), ctmr_chef_order_lisr_model.class)
                        .build();

        adapter=new man_bills_list_adapter(options25);
        man_bills_rc.setAdapter(adapter);

        Intent p=getIntent();
        man_id=p.getStringExtra("id");

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