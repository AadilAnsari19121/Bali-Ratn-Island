package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bali_ratn_island.databinding.ActivityCutomerCategoryBinding;
import com.example.bali_ratn_island.databinding.ActivityMangerBillsBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class cutomer_category extends drawer_ctmr {

    ActivityCutomerCategoryBinding activityCutomerCategoryBinding;
    RecyclerView rcv_cust_category;
    customer_category_adapter customer_category_adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutomer_category);

        activityCutomerCategoryBinding= ActivityCutomerCategoryBinding.inflate(getLayoutInflater());
        setContentView(activityCutomerCategoryBinding.getRoot());
        allactivityy2("Category");

        SearchView searchView=(SearchView) findViewById(R.id.search_tol);
        searchView.setVisibility(View.VISIBLE);


        checkConnection();
        rcv_cust_category = findViewById(R.id.rcv_customer_category);
        rcv_cust_category.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<rcv_model> options =
                new FirebaseRecyclerOptions.Builder<rcv_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category_table"), rcv_model.class)
                        .build();

        customer_category_adapter = new customer_category_adapter(options);
        rcv_cust_category.setAdapter(customer_category_adapter);

        SharedPreferences sharedPreferences = cutomer_category.this.getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        String Table_number = sharedPreferences.getString("table_number_ctmr","");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseRecyclerOptions<rcv_model> options11 =
                        new FirebaseRecyclerOptions.Builder<rcv_model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("category_table").orderByChild("cat_name").startAt(newText).endAt(newText+"\uf8ff"), rcv_model.class)
                                .build();
                customer_category_adapter = new customer_category_adapter(options11);
                customer_category_adapter.startListening();
                rcv_cust_category.setAdapter(customer_category_adapter);
                return false;
            }
        });






    }


    public void checkConnection()
    {
        ConnectivityManager manager=(ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activitynetwork=manager.getActiveNetworkInfo();

        if (activitynetwork !=null)
        {
            if (activitynetwork.getType() == ConnectivityManager.TYPE_WIFI)
            {
                //rcv_cust_category.setVisibility(View.VISIBLE);
               // shimmerFrameLayout.setVisibility(View.GONE);

            }
            else if (activitynetwork.getType() == ConnectivityManager.TYPE_MOBILE)
            {
               // rcv_cust_category.setVisibility(View.VISIBLE);
              //  shimmerFrameLayout.setVisibility(View.GONE);

            }

        }
        else
        {
            Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        customer_category_adapter.startListening();

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
        customer_category_adapter.stopListening();

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
                        Toast.makeText(cutomer_category.this, "See You Soon", Toast.LENGTH_LONG).show();
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

        //startActivity(new Intent(this,cutomer_dashboard.class ));
    }
}
