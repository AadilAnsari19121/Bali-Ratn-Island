package com.example.bali_ratn_island;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.bali_ratn_island.databinding.ActivityAdminFeedbackSeeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class admin_feedback_see extends drawer_admin {
    ActivityAdminFeedbackSeeBinding activityBinding;

    RecyclerView rc;
    admin_see_feedback_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback_see);

        activityBinding= ActivityAdminFeedbackSeeBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Feedback");

        rc=findViewById(R.id.admin_see_feedback_rcv);
        rc.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<feedback_model> options60 =
                new FirebaseRecyclerOptions.Builder<feedback_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("feedback"), feedback_model.class)
                        .build();

        adapter=new admin_see_feedback_adapter(options60);
        rc.setAdapter(adapter);
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