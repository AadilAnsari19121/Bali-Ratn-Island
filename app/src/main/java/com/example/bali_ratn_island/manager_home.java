package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class manager_home extends AppCompatActivity implements View.OnClickListener {

    Button gotothe_table,gotothe_orders,gotothe_bills,gotothe_profile;

    String man_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

        gotothe_table=findViewById(R.id.man_home_table);
        gotothe_orders=findViewById(R.id.man_home_orders);
        gotothe_bills=findViewById(R.id.man_home_bills);
        gotothe_profile=findViewById(R.id.man_home_profile);

        gotothe_table.setOnClickListener(this);
        gotothe_orders.setOnClickListener(this);
        gotothe_bills.setOnClickListener(this);
        gotothe_profile.setOnClickListener(this);


        Intent p=getIntent();
        man_id=p.getStringExtra("em_id");
        //Toast.makeText(this, "aa"+man_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view==gotothe_table)
        {
           // Intent p=getIntent();
            //man_id=p.getStringExtra("em_id");
            //startActivity(new Intent(manager_home.this,manager_profile.class));
            Intent i=new Intent(manager_home.this,manager_table_book.class);
            i.putExtra("id",man_id);
            startActivity(i);
        }
        else if (view==gotothe_orders)
        {
            //orders
           // Intent p=getIntent();
           // String man_id=p.getStringExtra("em_id");
            //startActivity(new Intent(manager_home.this,manager_profile.class));
            Intent i=new Intent(manager_home.this,manager_order_status.class);
            i.putExtra("id",man_id);
            startActivity(i);
        }
        else if (view==gotothe_bills)
        {
          //  Intent p=getIntent();
           // String man_id=p.getStringExtra("em_id");
            //startActivity(new Intent(manager_home.this,manager_profile.class));
            Intent i=new Intent(manager_home.this,manger_bills.class);
            i.putExtra("id",man_id);
            startActivity(i);
            //orders
        }

        else if (view==gotothe_profile)
        {
            //Intent p=getIntent();
           // String man_id=p.getStringExtra("em_id");
            //startActivity(new Intent(manager_home.this,manager_profile.class));
            Intent i=new Intent(manager_home.this,manager_profile.class);
            i.putExtra("id",man_id);
            startActivity(i);
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