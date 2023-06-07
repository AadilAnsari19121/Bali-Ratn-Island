package com.example.bali_ratn_island;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class chef__home extends AppCompatActivity {

    RecyclerView chef_see_orders_rcv;
    chef__disp_order_list_adapter adapter;
    FloatingActionButton chef_profile;

    String man_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_home);
        chef_see_orders_rcv=findViewById(R.id.chef_see_orders_rcv);
        chef_profile=findViewById(R.id.float_btn_for_chef_profile);

        Intent p=getIntent();
        man_id=p.getStringExtra("em_id");

        SharedPreferences sharedPreferences = chef__home.this.getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        String Table_number = sharedPreferences.getString("table_number_ctmr","");

        SharedPreferences sharedPreferences2 = getSharedPreferences("id_chef",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences2.edit();
        myEdit.putString("id_c", man_id);
        //myEdit.putInt("age", Integer.parseInt(age.getText().toString()));

// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();

        chef_see_orders_rcv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ctmr_chef_order_lisr_model> options25 =
                new FirebaseRecyclerOptions.Builder<ctmr_chef_order_lisr_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ctr_orders"), ctmr_chef_order_lisr_model.class)
                        .build();

        adapter=new chef__disp_order_list_adapter(options25);
        chef_see_orders_rcv.setAdapter(adapter);





        chef_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent p=getIntent();
//                String man_id=p.getStringExtra("em_id");

                //startActivity(new Intent(manager_home.this,manager_profile.class));
                Intent i=new Intent(chef__home.this,manager_profile.class);
                i.putExtra("id",man_id);
                startActivity(i);
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