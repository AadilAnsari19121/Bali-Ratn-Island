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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bali_ratn_island.databinding.ActivityCustomerSeeCartBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class customer_see_cart extends drawer_ctmr {

    ActivityCustomerSeeCartBinding activityCustomerSeeCartBinding;
    int this_cart_total_amount;

    Button oder_the_items;

    TextView cartbill_tv;
    RecyclerView rc,rc2;

    CTMR_cart_adapter adapter;
    chef_dsiplay_order_adapter adapter2;
    ArrayList<CTMR_cart_model> datlist;

    ArrayAdapter<String> adapter3;
    ArrayList<String> arrayList;
    ValueEventListener listener;

    ArrayList<CTMR_cart_model> orderlist;
    DatabaseReference db;
    FirebaseAuth auth;
    //CTMR_see_cart_adpater_2_0 adpater_2_0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_see_cart);

        activityCustomerSeeCartBinding=ActivityCustomerSeeCartBinding.inflate(getLayoutInflater());
        setContentView(activityCustomerSeeCartBinding.getRoot());
        allactivityy2("Cart");


        cartbill_tv=findViewById(R.id.total_cart_bill);
        oder_the_items=findViewById(R.id.order_items_cart);

        SharedPreferences sharedPreferences = customer_see_cart.this.getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        String Table_number = sharedPreferences.getString("table_number_ctmr","");


         rc=findViewById(R.id.rccccv);
         rc2=findViewById(R.id.rcv2_see_cart);

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("MyTotalAmount"));

        rc.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CTMR_cart_model> options17 =
                new FirebaseRecyclerOptions.Builder<CTMR_cart_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cart").child(Table_number), CTMR_cart_model.class)
                        .build();

        adapter=new CTMR_cart_adapter(options17);
        rc.setAdapter(adapter);


        rc2.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CTMR_cart_model> options18 =
                new FirebaseRecyclerOptions.Builder<CTMR_cart_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cart").child(Table_number), CTMR_cart_model.class)
                        .build();

        adapter2=new chef_dsiplay_order_adapter(options18);
        //rc2.setAdapter(adapter2);



        //orderlist=new ArrayList<>();
       //rc2.setAdapter(x);
        //ArrayAdapter<String > adapter2=new ArrayAdapter(customer_see_cart.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,x);
        //rc2.setAdapter(adapter2);



//        db.collection("cart").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                for (DocumentSnapshot d : list) {
//                    // after getting this list we are passing
//                    // that list to our object class.
//                    CTMR_cart_model dataModal = d.toObject(CTMR_cart_model.class);
//
//                    // after getting data from Firebase we are
//                    // storing that data in our array list
//                    orderlist.add(dataModal);
//
//                    ArrayAdapter<CTMR_cart_model> adapter2=new ArrayAdapter<CTMR_cart_model>(customer_see_cart.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,orderlist);
//                    rc2.setAdapter(adapter2);
//                }
//
//            }
//        });


        arrayList=new ArrayList<String >();
        db= FirebaseDatabase.getInstance().getReference("order_list_2").child(Table_number);
        adapter3=new ArrayAdapter<String>(customer_see_cart.this, R.layout.menu_sppnr,arrayList);
        listener=db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata: snapshot.getChildren())
                {

                    arrayList.add(mydata.child("item_name_X_item_quantity").getValue().toString());
                    adapter3.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        oder_the_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(customer_see_cart.this);
                ab.setTitle("Order....?");
                ab.setMessage("do you want order item which have cost is "+this_cart_total_amount+" ..?");
                ab.setCancelable(false);
                ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

//                                SharedPreferences sharedPref = getSharedPreferences("tb_num_2", MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPref.edit();
//                                editor.putString("table_number_2", Table_number);
//                                editor.apply();
//
//                                startActivity(new Intent(customer_see_cart.this,see_orders.class));


                                String cr_time,tb_num,amount;
                                tb_num=Table_number;
                                Calendar calender=Calendar.getInstance();
                                SimpleDateFormat crt=new SimpleDateFormat("HH:mm:ss a");
                                cr_time=crt.format(calender.getTime());
//                                cr_date=crd.format(calender.getTime());

                                FirebaseDatabase database=FirebaseDatabase.getInstance();
                                DatabaseReference root3=database.getReference("ctr_orders");
                                DatabaseReference root4=database.getReference("ctr_orders2");
                                Map<String ,Object> map3=new HashMap<>();
                                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                                map3.put("Time",cr_time);
                                map3.put("Table_number",tb_num);
                                map3.put("orders",arrayList);

                                root3.child(Table_number).setValue(map3)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(customer_see_cart.this, "order successfull", Toast.LENGTH_SHORT).show();
                                                FirebaseDatabase.getInstance().getReference().child("cart").child(Table_number).removeValue();
                                                FirebaseDatabase.getInstance().getReference().child("cancel_order").child(Table_number).removeValue();
                                                rc.removeAllViews();

                                                startActivity(new Intent(customer_see_cart.this,cutomer_order_status.class));
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(customer_see_cart.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                amount= String.valueOf(this_cart_total_amount);

                                Map<String ,Object> map4=new HashMap<>();
                                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                                map4.put("Time",cr_time);
                                map4.put("Amount",amount);
                                map4.put("Table_number",tb_num);
                                map4.put("orders",arrayList);

                                root4.child(Table_number).setValue(map4);

                                cartbill_tv.setText("Total : 00 ₹");


                                String s="C";
                                String x=tb_num.toString();
                                FirebaseDatabase database20=FirebaseDatabase.getInstance();
                                DatabaseReference root5=database20.getReference("status_of_order");
                                Map<String ,Object> map5=new HashMap<>();
                                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                                map5.put("A",s.toString());
                                map5.put("Table",x);
                                root5.child(x).setValue(map5);


//                                TextView v =view.findViewById(R.id.order_status_tv);
//                                v.setVisibility(View.VISIBLE);
//                                db=FirebaseDatabase.getInstance().getReference("order_list");
//
//
//
//                                String cr_time;
//                                String cr_date;
//                                Calendar calender=Calendar.getInstance();
//
//                                SimpleDateFormat crd=new SimpleDateFormat("dd MM yyyy");
//                                SimpleDateFormat crt=new SimpleDateFormat("HH:mm:ss a");
//
//                                cr_time=crt.format(calender.getTime());
//                                cr_date=crd.format(calender.getTime());
//
//                                String crtime_crdate= cr_time+"_&&_"+cr_date;
//                                int order_amount=this_cart_total_amount;
//
//
//                                chef_displayy_order_model obj=new chef_displayy_order_model(crtime_crdate,order_amount,adapter2,Table_number);
//
//                                db.child(Table_number).child(crtime_crdate).setValue(obj)
//                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                if (task.isSuccessful())
//                                                {
//                                                }
//                                                else
//                                                {
//                                                    Toast.makeText(customer_see_cart.this, "nhi hua", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        })
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                Toast.makeText(customer_see_cart.this, "order success", Toast.LENGTH_SHORT).show();
//                                                FirebaseDatabase.getInstance().getReference().child("cart").child(Table_number).removeValue();
//                                                rc.removeAllViews();
//                                            }
//                                        })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Toast.makeText(customer_see_cart.this, "try again", Toast.LENGTH_SHORT).show();
//                                            }
//                                        });


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
        });


//        refreshLayout=findViewById(R.id.swipe_layout);
//        refreshLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });





//        rc.setLayoutManager(new LinearLayoutManager(this));
//        datlist=new ArrayList<>();
//        adpater_2_0=new CTMR_see_cart_adpater_2_0(datlist);
//        rc.setAdapter(adpater_2_0);
//        db=FirebaseFirestore.getInstance();
//        auth=FirebaseAuth.getInstance();
//        db.collection("Cart_item").document(auth.getCurrentUser().getUid())
//                .collection("users").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot d : list) {
//                            CTMR_cart_model obj = d.toObject(CTMR_cart_model.class);
//                            datlist.add(obj);
//                        }
//                        adpater_2_0.notifyDataSetChanged();
//                    }
//                });
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                //adpater_2_0
//            }
//        });


    }

    public BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalbill=intent.getIntExtra("Total_amount",0);
            cartbill_tv.setText("Total : "+totalbill+" ₹");
            this_cart_total_amount=totalbill;


            SharedPreferences sharedPref = getSharedPreferences("total_amount_cart", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("t_am_cart", String.valueOf(this_cart_total_amount));
            editor.apply();
        }
    };



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();
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
        adapter2.stopListening();
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
                        Toast.makeText(customer_see_cart.this, "See You Soon", Toast.LENGTH_LONG).show();
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

