package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class see_orders extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    DatabaseReference db,db2;
    ListView rcv_123456;
    ValueEventListener listener;
    ValueEventListener listener2;
    Button bt;

    CircleImageView close_btn;
    LinearLayout ll;

    Handler mHandler;
    String man_id;

    int this_cart_total_amount;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_orders);

        rcv_123456=findViewById(R.id.recyclerView123456);
        bt=findViewById(R.id.order_items_cart);
        ll=findViewById(R.id.llllllout);
        close_btn=findViewById(R.id.close_btn);
        View view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.whole_app_background);

        this.mHandler = new Handler();
        m_Runnable.run();

        Intent p=getIntent();
        man_id=p.getStringExtra("em_id");

       // Toast.makeText(this, "as " +man_id, Toast.LENGTH_SHORT).show();
//
//        SharedPreferences sharedPreferences = see_orders.this.getSharedPreferences("tb_num_2", MODE_PRIVATE);
//        String Table_number = sharedPreferences.getString("table_number_2","");

        //Toast.makeText(this, "dsd "+Table_number, Toast.LENGTH_SHORT).show();

        String tb_num= getIntent().getStringExtra("num_tab");
        arrayList=new ArrayList<String>();
        db= FirebaseDatabase.getInstance().getReference("order_list_2").child(tb_num);
        db2= FirebaseDatabase.getInstance().getReference("cancel_order");
        arrayAdapter=new ArrayAdapter<String>(see_orders.this, R.layout.menu_sppnr,arrayList);
        rcv_123456.setAdapter(arrayAdapter);


        close_btn.setVisibility(View.GONE);
        listener=db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata: snapshot.getChildren())
                {

                   // tb_num=Table_number;
                    arrayList.add(mydata.child("item_name_X_item_quantity").getValue().toString());
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("ctr_orders").child(tb_num).removeValue();
                FirebaseDatabase.getInstance().getReference().child("order_list_2").child(tb_num).removeValue();

                Toast.makeText(see_orders.this, "Nice cooking great work", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(see_orders.this,chef__home.class));

                String s="B";
                String x=tb_num.toString();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference root3=database.getReference("status_of_order");
                Map<String ,Object> map3=new HashMap<>();
                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                map3.put("A",s.toString());
                map3.put("Table",x);
                root3.child(x).setValue(map3);

//                FirebaseDatabase database=FirebaseDatabase.getInstance();
//                DatabaseReference root3=database.getReference("ctr_orders");
//                Map<String ,Object> map3=new HashMap<>();
//                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
//                map3.put("orders",arrayList);
//
//                root3.child(Table_number).setValue(map3);
            }
        });


        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(tb_num))
                {
                    final String keyvalue= snapshot.child(tb_num).child("tb_num").getValue(String.class);


                    if (keyvalue.equals(tb_num))
                    {
                        view.setBackgroundResource(R.color.red);
                        rcv_123456.setBackgroundResource(R.color.red);
                    }
                    else if (!keyvalue.equals(tb_num))
                    {
                        view.setBackgroundResource(R.color.whole_app_background);
                        rcv_123456.setBackgroundResource(R.color.whole_app_background);
                    }
                    else
                    {
                        view.setBackgroundResource(R.color.whole_app_background);
                        rcv_123456.setBackgroundResource(R.color.whole_app_background);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            //Toast.makeText(MainActivity.this,"in runnable",Toast.LENGTH_SHORT).show();

            see_orders.this.mHandler.postDelayed(m_Runnable,1000);
        }

    };

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,chef__home.class);
        i.putExtra("em_id",man_id);
        startActivity(i);
        super.onBackPressed();
    }
}