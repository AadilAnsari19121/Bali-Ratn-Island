package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bali_ratn_island.databinding.ActivityMangerBillsBinding;
import com.example.bali_ratn_island.databinding.ActivityMangerPayBillBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class manger_pay_bill extends drawer_man implements PaymentResultListener {

    ActivityMangerPayBillBinding activityMangerPayBillBinding;

    ListView man_pay_bill_rc;
    ArrayList<String> man_pay_arrayList;
    ArrayList<String> man_pay_arrayList2;
    //ArrayAdapter<String> man_pay_arrayAdapter;
    DatabaseReference db,db2;
   // ListView rcv_123456;
    ValueEventListener man_pay_listener;
    ValueEventListener man_pay_listener2;
    Button man_pay_bt;
    man_pay_bills_details_adapter adapter;
    String c;
    TextView pay_bill_tam;
    DialogPlus dialogPlus;
    String tb_num;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manger_pay_bill);

        activityMangerPayBillBinding= ActivityMangerPayBillBinding.inflate(getLayoutInflater());
        setContentView(activityMangerPayBillBinding.getRoot());
        allactivityy("Pay Bill");

        man_pay_bill_rc=findViewById(R.id.man_pay_bill_rcv);
        man_pay_bt=findViewById(R.id.man_pay_bill_pay_btn);
        pay_bill_tam=findViewById(R.id.man_pay_bill_t_am);

        View view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.whole_app_background);

        Checkout.preload(getApplicationContext());

        tb_num= getIntent().getStringExtra("num_tab");
        man_pay_arrayList=new ArrayList<String>();
        man_pay_arrayList2=new ArrayList<String>();
        db= FirebaseDatabase.getInstance().getReference("order_list_3").child(tb_num);
        db2= FirebaseDatabase.getInstance().getReference("ctr_orders2").child(tb_num);
        man_pay_bills_details_adapter adapter=new man_pay_bills_details_adapter(this,man_pay_arrayList,man_pay_arrayList2);
       // man_pay_arrayAdapter=new ArrayAdapter<String>(manger_pay_bill.this, R.layout.menu_sppnr,man_pay_arrayList);
        man_pay_bill_rc.setAdapter(adapter);



        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata: snapshot.getChildren())
                {

                    // tb_num=Table_number;
                    man_pay_arrayList.add(mydata.child("item_name_X_item_quantity").getValue().toString());
                    man_pay_arrayList2.add(mydata.child("item_price_X_item_quantity_eq_item_total_pr").getValue().toString());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                c=snapshot.child("Amount").getValue(String.class);
                pay_bill_tam.setText(c);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        man_pay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.payment_qer_code))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();


                View myview=dialogPlus.getHolderView();
                Button done=myview.findViewById(R.id.payment_done_btn);
                Button razor=myview.findViewById(R.id.payment_razorpay);
                TextView amount_p=myview.findViewById(R.id.payment_amount_tv);

                TextView or=myview.findViewById(R.id.or);
                ImageView sc=myview.findViewById(R.id.scanner);
                LinearLayout lo=myview.findViewById(R.id.leanear_layout_popup_payment);

                TextInputLayout ph_pop=myview.findViewById(R.id.phone_number_ctmr_payment);
                TextInputLayout ml_pop=myview.findViewById(R.id.email_ctmr_payment);
                Button razordone=myview.findViewById(R.id.payment_done_btn_razor);

                ph_pop.setVisibility(View.GONE);
                ml_pop.setVisibility(View.GONE);
                razordone.setVisibility(View.GONE);
                razor.setVisibility(View.GONE);
                or.setVisibility(View.GONE);


                amount_p.setText(String.valueOf(c)+".00 ₹");
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseDatabase.getInstance().getReference().child("ctr_orders2").child(tb_num).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("order_list_3").child(tb_num).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("table_booked_status").child(tb_num).removeValue();

                        String s="C";
                        String x=tb_num.toString();
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        DatabaseReference root3=database.getReference("status_of_order");
                        DatabaseReference root4=database.getReference("bali_customer_payment").push();
                        Map<String ,Object> map3=new HashMap<>();
                        //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                        map3.put("A",s.toString());
                        map3.put("Table",x);
                        root3.child(x).setValue(map3);

                        String cr_time;
                        String total_amount;
                        String Table_number;
                        String cr_date;

                        Calendar calender=Calendar.getInstance();
                        SimpleDateFormat crt=new SimpleDateFormat("HH:mm:ss a");
                        cr_time=crt.format(calender.getTime());

                        SimpleDateFormat crd=new SimpleDateFormat("MM dd, yyyy");
                        cr_date=crd.format(calender.getTime());

                        Table_number=tb_num;
                        int total_payable_amount =Integer.parseInt(c);


                        bali_payments_model model=new bali_payments_model(cr_time,total_payable_amount,Table_number,cr_date);
                        root4.setValue(model);

//                        Map<String ,Object> map4=new HashMap<>();
//                        //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
//                        map4.put("cr_time",cr_time);
//                        map4.put("total_amount",total_amount);
//                        map4.put("Table_number",Table_number);
//                        map4.put("cr_date",cr_date);
//                        root4.setValue(map4);


                        dialogPlus.dismiss();

                        startActivity(new Intent(manger_pay_bill.this,manger_bills.class));
                    }
                });

                razor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lo.setBackgroundResource(R.color.white);
                        sc.setVisibility(View.GONE);
                        or.setVisibility(View.GONE);
                        done.setVisibility(View.GONE);
                        razor.setVisibility(View.GONE);

                        ph_pop.setVisibility(View.VISIBLE);
                        ml_pop.setVisibility(View.VISIBLE);
                        razordone.setVisibility(View.VISIBLE);



                        razordone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String pn_pp=ph_pop.getEditText().getText().toString();
                                String ml_pp=ml_pop.getEditText().getText().toString();

                                SharedPreferences sharedPreferences7 = getSharedPreferences("pn_mail",MODE_PRIVATE);
                                SharedPreferences.Editor myEdit7 = sharedPreferences7.edit();
                                myEdit7.putString("pn_pref",pn_pp);
                                myEdit7.putString("ml_pref",ml_pp);
                                myEdit7.commit();

                                makerazorpayment();
                            }
                        });

                    }
                });
                dialogPlus.show();
            }
        });



    }

    private void makerazorpayment()
    {
        SharedPreferences sharedPreferences = manger_pay_bill.this.getSharedPreferences("pn_mail", MODE_PRIVATE);
        String xpn = sharedPreferences.getString("pn_pref","");
        String xml = sharedPreferences.getString("ml_pref","");


        double d=Double.parseDouble(c);
        double f=d*100;
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_LiTEs0NHjbaKuQ");
        checkout.setImage(R.mipmap.logo2_0);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "BALI RATN ISLAND");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", String.valueOf(f));//pass amount in currency subunits
            options.put("prefill.email", xml);
            options.put("prefill.contact",xpn);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("razorerror", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        FirebaseDatabase.getInstance().getReference().child("ctr_orders2").child(tb_num).removeValue();
        FirebaseDatabase.getInstance().getReference().child("order_list_3").child(tb_num).removeValue();
        FirebaseDatabase.getInstance().getReference().child("table_booked_status").child(tb_num).removeValue();

        String ed="C";
        String x=tb_num.toString();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference root3=database.getReference("status_of_order");
        DatabaseReference root4=database.getReference("bali_customer_payment").push();
        Map<String ,Object> map3=new HashMap<>();
        //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
        map3.put("A",ed.toString());
        map3.put("Table",x);
        root3.child(x).setValue(map3);

        int total_payable_amount=Integer.parseInt(c);
        String cr_time;
        String total_amount;
        String Table_number;
        String cr_date;

        Calendar calender=Calendar.getInstance();
        SimpleDateFormat crt=new SimpleDateFormat("HH:mm:ss a");
        cr_time=crt.format(calender.getTime());

        SimpleDateFormat crd=new SimpleDateFormat("MM dd, yyyy");
        cr_date=crd.format(calender.getTime());

        Table_number=tb_num;
        total_amount=c;


        bali_payments_model model=new bali_payments_model(cr_time,total_payable_amount,Table_number,cr_date);
        root4.setValue(model);

//                        Map<String ,Object> map4=new HashMap<>();
//                        //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
//                        map4.put("cr_time",cr_time);
//                        map4.put("total_amount",total_amount);
//                        map4.put("Table_number",Table_number);
//                        map4.put("cr_date",cr_date);
//                        root4.setValue(map4);



        dialogPlus.dismiss();

        Toast.makeText(this, "Sucessfully done", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = manger_pay_bill.this.getSharedPreferences("pn_mail", MODE_PRIVATE);
        String pn_msg = sharedPreferences.getString("pn_pref","");

        SmsManager mysms=SmsManager.getDefault();
        String mms="Your payment of Rs."+c+" is successful. \n\n Thanks for visiting ";
        mysms.sendTextMessage(pn_msg,null,mms,null,null);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();

    }
}