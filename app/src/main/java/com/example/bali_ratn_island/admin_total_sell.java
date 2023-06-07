package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bali_ratn_island.databinding.ActivityAdminTotalSellBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_total_sell extends drawer_admin {
    ActivityAdminTotalSellBinding activityBinding;
    RecyclerView admin_t_sell_rcv;
    TextView admin_t_sell_tv;

    ArrayAdapter<Integer> adapter3;
    ArrayList<Integer> arrayList;
    DatabaseReference db2;
    admin_t_selladapter adapter;
    int this_cart_total_amount;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_total_sell);

        activityBinding= ActivityAdminTotalSellBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Sell");

        admin_t_sell_rcv=findViewById(R.id.admin_total_rcv);
        admin_t_sell_tv=findViewById(R.id.admin_total_tv);
        admin_t_sell_rcv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<bali_payments_model> options25 =
                new FirebaseRecyclerOptions.Builder<bali_payments_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("bali_customer_payment"), bali_payments_model.class)
                        .build();

        adapter=new admin_t_selladapter(options25);
        admin_t_sell_rcv.setAdapter(adapter);


        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("MyTotalAmount2"));

//        arrayList=new ArrayList<Integer >();
//        db2= FirebaseDatabase.getInstance().getReference("bali_customer_payment");
//        adapter3= new ArrayAdapter<Integer>(admin_total_sell.this,R.layout.menu_sppnr,arrayList);
//        adapter=new ArrayAdapter<Integer>(admin_total_sell.this, R.layout.menu_sppnr,arrayList);
//        db2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot mydata: snapshot.getChildren())
//                {
//                    arrayList.add(Integer.valueOf((Integer) mydata.child("total_amount").getValue()));
//                    adapter3.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//        int sum = 0;
//
//        for (int number : arrayList){
//            sum += number;
//        }
//
//        admin_t_sell_tv.setText(sum);
    }

    public BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalbill=intent.getIntExtra("Total_amount2",0);
            admin_t_sell_tv.setText("Total : "+totalbill+" ₹");
            this_cart_total_amount=totalbill;


//            SharedPreferences sharedPref = getSharedPreferences("total_amount_cart", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString("t_am_cart", String.valueOf(this_cart_total_amount));
//            editor.apply();
        }
    };


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