package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class cutomer_dashboard extends AppCompatActivity implements View.OnClickListener {
    
    Button see_category,see_cart,see_order_status,see_bills,feedback;
    String Table_number_CTMR;

    DatabaseReference db= FirebaseDatabase.getInstance().getReference("dining_table");
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutomer_dashboard);
        
        see_category=findViewById(R.id.cutomer_see_category);
        see_cart=findViewById(R.id.cutomer_see_cart);
        see_order_status=findViewById(R.id.cutomer_see_order_status);
        see_bills=findViewById(R.id.cutomer_see_bill);
        feedback=findViewById(R.id.cutomer_feedback);

        SharedPreferences sharedPreferences = cutomer_dashboard.this.getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        Table_number_CTMR = sharedPreferences.getString("table_number_ctmr","");

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                DialogPlus dialogPlus=DialogPlus.newDialog(cutomer_dashboard.this)
//                        .setContentHolder(new ViewHolder(R.layout.table_number_ctmr_des_enter))
//                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
//                        .setGravity(Gravity.CENTER)
//                        .create();
//                dialogPlus.show();
//
//                View myview=dialogPlus.getHolderView();
//                TextInputLayout tbn=myview.findViewById(R.id.table_number_for_CTMR_des_number);
//
//                Button en_bt=myview.findViewById(R.id.table_number_for_CTMR_des_btn);
//
//                en_bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        Table_number_CTMR=tbn.getEditText().getText().toString().trim();
//
//                        db.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if (snapshot.hasChild(Table_number_CTMR))
//                                {
//                                    dialogPlus.dismiss();
//                                }
//                                else
//                                {
//                                    Toast.makeText(cutomer_dashboard.this, "enter valid table number please", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//
//
//                    }
//                });
//
//            }
//        },500);


        
        see_category.setOnClickListener(this);
        see_cart.setOnClickListener(this);
        see_order_status.setOnClickListener(this);
        see_bills.setOnClickListener(this);
        feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==see_category)
        {
            SharedPreferences sharedPref = getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("table_number_ctmr", Table_number_CTMR);
            editor.apply();


            Intent i=new Intent(cutomer_dashboard.this,cutomer_category.class);
            //i.putExtra("table_number_ctmr",Table_number_CTMR);
            startActivity(i);
           //startActivity(new Intent(cutomer_dashboard.this,cutomer_category.class));
            //Toast.makeText(this, "category", Toast.LENGTH_SHORT).show();
        }
        else if (view==see_cart)
        {
            SharedPreferences sharedPref = getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("table_number_ctmr", Table_number_CTMR);
            editor.apply();

            Intent i=new Intent(cutomer_dashboard.this,customer_see_cart.class);
            //i.putExtra("table_number_ctmr",Table_number_CTMR);
            startActivity(i);
            //startActivity(new Intent(cutomer_dashboard.this,customer_see_cart.class));
        }
        else if (view==see_order_status)
        {
            SharedPreferences sharedPref = getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("table_number_ctmr", Table_number_CTMR);
            editor.apply();

            Intent i=new Intent(cutomer_dashboard.this,cutomer_order_status.class);
            //i.putExtra("table_number_ctmr",Table_number_CTMR);
            startActivity(i);
            //Toast.makeText(this, "order", Toast.LENGTH_SHORT).show();
        }
        else if (view==see_bills)
        {
            SharedPreferences sharedPref = getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("table_number_ctmr", Table_number_CTMR);
            editor.apply();

            Intent i=new Intent(cutomer_dashboard.this,customer_bills.class);
            //i.putExtra("table_number_ctmr",Table_number_CTMR);
            startActivity(i);
            //startActivity(new Intent(cutomer_dashboard.this,xyzabcd.class));
        }

        else if (view==feedback)
        {
            SharedPreferences sharedPref = getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("table_number_ctmr", Table_number_CTMR);
            editor.apply();

            Intent i=new Intent(cutomer_dashboard.this,customer_feedback.class);
            //i.putExtra("table_number_ctmr",Table_number_CTMR);
            startActivity(i);
            //startActivity(new Intent(cutomer_dashboard.this,xyzabcd.class));
        }

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
                        Toast.makeText(cutomer_dashboard.this, "see you soon", Toast.LENGTH_LONG).show();
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