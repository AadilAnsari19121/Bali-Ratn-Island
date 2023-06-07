package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bali_ratn_island.databinding.ActivityCustomerSeeCartBinding;
import com.example.bali_ratn_island.databinding.ActivityCutomerOrderStatusBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class cutomer_order_status extends drawer_ctmr {

    ActivityCutomerOrderStatusBinding activityCutomerOrderStatusBinding;


    ListView cutomer_see_order_status_lv;
    Button cutomer_see_order_status_cancel_btn;
    ArrayList<String > arl1;
    ArrayList<String > arl2;
    //ArrayAdapter<String > adapter;
    customer_see_order_status_adapter adapter;

   // TextView total_am;
    DatabaseReference db;
    ValueEventListener listener;
    String total_amount;
    Handler mHandler;

    DatabaseReference db2= FirebaseDatabase.getInstance().getReference("status_of_order");

    LinearLayout lo;
    TextView tv;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutomer_order_status);

        activityCutomerOrderStatusBinding= ActivityCutomerOrderStatusBinding.inflate(getLayoutInflater());
        setContentView(activityCutomerOrderStatusBinding.getRoot());
        allactivityy2("Order Status");

        tv=findViewById(R.id.order_status_tv);
        cutomer_see_order_status_lv=findViewById(R.id.cutomer_see_order_status_listview);
        cutomer_see_order_status_cancel_btn=findViewById(R.id.cutomer_see_order_status_cancel_btn);
        lo=findViewById(R.id.lllllllllllll);
        //total_am=findViewById(R.id.cutomer_see_order_status_total_amount);

        SharedPreferences sharedPreferences = cutomer_order_status.this.getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        String Table_number = sharedPreferences.getString("table_number_ctmr","");


        this.mHandler = new Handler();
        m_Runnable.run();
       // LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("MyTotalAmount"));

//        SharedPreferences sharedPreferences2 = cutomer_order_status.this.getSharedPreferences("total_amount_cart", MODE_PRIVATE);
//        total_amount = sharedPreferences2.getString("t_am_cart","");
//
//        total_am.setText(total_amount);
        arl1=new ArrayList<>();
        arl2=new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference("order_list_2").child(Table_number);

        customer_see_order_status_adapter adapter=new customer_see_order_status_adapter(this,arl1,arl2);

        //adapter=new ArrayAdapter<String>(cutomer_order_status.this,R.layout.menu_sppnr,arl);
        cutomer_see_order_status_lv.setAdapter(adapter);

        listener=db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata:snapshot.getChildren())
                {
                    arl1.add(mydata.child("item_name_X_item_quantity").getValue().toString());
                    arl2.add(mydata.child("item_price_X_item_quantity_eq_item_total_pr").getValue().toString());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        cutomer_see_order_status_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ab=new AlertDialog.Builder(cutomer_order_status.this);
                ab.setTitle("Cancel....?");
                ab.setMessage("do you want Cancel item which have cost is "+total_amount+" ..?");
                ab.setCancelable(false);

                ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        DatabaseReference root3=database.getReference("cancel_order");
                        Map<String ,Object> map3=new HashMap<>();
                        //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                        map3.put("Table",Table_number);
                        root3.child(Table_number).setValue(map3)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(cutomer_order_status.this, "Order Canceled", Toast.LENGTH_SHORT).show();
                                        dialogInterface.dismiss();
                                        FirebaseDatabase.getInstance().getReference().child("order_list_2").child(Table_number).removeValue();
                                        FirebaseDatabase.getInstance().getReference().child("order_list_3").child(Table_number).removeValue();
                                        FirebaseDatabase.getInstance().getReference().child("ctr_orders").child(Table_number).removeValue();
                                        FirebaseDatabase.getInstance().getReference().child("ctr_orders2").child(Table_number).removeValue();

                                        tv.setVisibility(View.GONE);
                                        tv.setBackgroundResource(R.color.header);
                                        tv.setText("Your order was canceled");
                                        tv.setTextColor(R.color.white);
                                        //cutomer_see_order_status_lv.removeAllViews();
                                        startActivity(new Intent(cutomer_order_status.this,cutomer_category.class));

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(cutomer_order_status.this, "Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                });


//                        total_amount="00";
//                        total_am.setText("Total : 00 ₹");

                    }
                });
                ab.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                ab.show();
            }
        });


        tv.setBackgroundResource(R.color.yellow);

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(Table_number))
                {
                    final String keyvalue= snapshot.child(Table_number).child("A").getValue(String.class);
                    String x1="A";
                    String x2="B";

                    if (keyvalue.equals(x1))
                    {
                        tv.setBackgroundResource(R.color.yellow);
                        tv.setText("Your order in cooking process");
                        tv.setTextColor(R.color.black);
                    }
                    else if (keyvalue.equals(x2))
                    {
                        tv.setBackgroundResource(R.color.green);
                        //lo.setBackgroundResource(R.color.golden);
                        tv.setTextColor(R.color.black);
                       // tv.setText("Your order was ready");

                        new CountDownTimer(120000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                tv.setText("Your order was ready " + millisUntilFinished / 1000);
                                //here you can have your logic to set text to edittext
                            }

                            public void onFinish() {
                                tv.setText("Enjoy Your meal");
                            }

                        }.start();

                        cutomer_see_order_status_cancel_btn.setVisibility(View.GONE);
                    }
                    else
                    {
                        tv.setBackgroundResource(R.color.red);
                        tv.setTextColor(R.color.black);
                        tv.setText("Your order in waiting list");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                lo.notifyAll();
//                adapter.notifyDataSetChanged();
//                handler.postDelayed(this, 10 * 1000);
//            }
//        }, 10 * 1000);
//
//
//        Timer timerAsync;
//        TimerTask timerTaskAsync;
//
//        timerAsync = new Timer();
//        timerTaskAsync = new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
//        };
//        timerAsync.schedule(timerTaskAsync, 1000, 5000);

    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            //Toast.makeText(MainActivity.this,"in runnable",Toast.LENGTH_SHORT).show();

           cutomer_order_status.this.mHandler.postDelayed(m_Runnable,1000);
        }

    };

//    public BroadcastReceiver receiver=new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            int totalbill=intent.getIntExtra("Total_amount",0);
//           // total_am.setText("Total : "+totalbill+" ₹");
//            this_cart_total_amount=totalbill;
//
//
//            SharedPreferences sharedPref = getSharedPreferences("total_amount_cart", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString("t_am_cart", String.valueOf(totalbill));
//            editor.apply();
//        }
//    };


    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        ab.setTitle("Alert...!");
        ab.setMessage("do you want close this app..?");
        ab.setCancelable(false);
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(cutomer_order_status.this, "See You Soon", Toast.LENGTH_LONG).show();
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