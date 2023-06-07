package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.telephony.SmsManager;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bali_ratn_island.databinding.ActivityCustomerBillsBinding;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class customer_bills extends drawer_ctmr implements PaymentResultListener {

    ActivityCustomerBillsBinding activityCustomerBillsBinding;
    //ActivityCustomerBillsBinding activityCustomerBillsBinding;

    TextView ctmr_bills_table_num,ctmr_bills_time,ctmr_bills_date,ctmr_bills_total_amount;
    ListView ctmr_bills_lv;

    ArrayList<String > arl1;
    ArrayList<String > arl2;
    customer_see_order_status_adapter adapter;
    DatabaseReference db,db2;
    ValueEventListener listener;
    String total_amount;
    Button online_pay;
    String tb_num;


    DatabaseReference db3= FirebaseDatabase.getInstance().getReference("status_of_order");

    TextView servicecharge,cgst,sgst,pay_total,weekday_offer,weekday_amount;

    int c;
    String xyz;
    DialogPlus dialogPlus;
    DialogPlus dialogPlus2;

    String  pn_number_he;
    String tam;
    String pn_number_pay;
    String email_pay;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bills);
        activityCustomerBillsBinding=ActivityCustomerBillsBinding.inflate(getLayoutInflater());
        setContentView(activityCustomerBillsBinding.getRoot());
        allactivityy2("Bills");

//        activityCustomerBillsBinding= ActivityCustomerBillsBinding.inflate(getLayoutInflater());
//        setContentView(activityCustomerBillsBinding.getRoot());
//        allactivityy2("Bill");


        ctmr_bills_table_num=findViewById(R.id.ctmr_bills_table_num);
        ctmr_bills_time=findViewById(R.id.ctmr_bills_time);
        ctmr_bills_date=findViewById(R.id.ctmr_bills_date);

        ctmr_bills_lv=findViewById(R.id.ctmr_bills_listview);
        ctmr_bills_total_amount=findViewById(R.id.ctmr_bills_total_amount);

        online_pay=findViewById(R.id.pay_online_btn);

        online_pay.setEnabled(false);

        servicecharge=findViewById(R.id.ctmr_bills_service_charge);
        cgst=findViewById(R.id.ctmr_bills_cgst);
        sgst=findViewById(R.id.ctmr_bills_sgst);
        pay_total=findViewById(R.id.ctmr_bills_pay_total_amount_this);
        weekday_offer=findViewById(R.id.weekday_offer);
        weekday_amount=findViewById(R.id.ctmr_weekday);

        SharedPreferences sharedPreferences = customer_bills.this.getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        tb_num = sharedPreferences.getString("table_number_ctmr","");

        arl1=new ArrayList<>();
        arl2=new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference("order_list_3").child(tb_num);
        db2= FirebaseDatabase.getInstance().getReference("ctr_orders2").child(tb_num);
        customer_see_order_status_adapter adapter=new customer_see_order_status_adapter(this,arl1,arl2);

        //adapter=new ArrayAdapter<String>(cutomer_order_status.this,R.layout.menu_sppnr,arl);
        ctmr_bills_lv.setAdapter(adapter);



        Calendar calender=Calendar.getInstance();

        SimpleDateFormat crd=new SimpleDateFormat("MM dd, yyyy");
        SimpleDateFormat crt=new SimpleDateFormat("HH:mm:ss a");

        String cr_time=crt.format(calender.getTime());
        String cr_date=crd.format(calender.getTime());

        ctmr_bills_table_num.setText(tb_num);
        ctmr_bills_time.setText(cr_time);
        ctmr_bills_date.setText(cr_date);

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

        db2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata2:snapshot.getChildren())
                {
                    tam=snapshot.child("Amount").getValue(String.class);
                    ctmr_bills_total_amount.setText(tam);


                    //String day= LocalDate.now().getDayOfWeek().name();
                    String day= String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

                    if (day.equals("7"))
                    {
                        weekday_offer.setText("saturday offer");
                    }
                    else if (day.equals("1"))
                    {
                        weekday_offer.setText("sunday offer");
                    }
                    else if (day.equals("2"))
                    {
                        weekday_offer.setText("monday offer");
                    }
                    else if (day.equals("3"))
                    {
                        weekday_offer.setText("tuesday offer");
                    }
                    else if (day.equals("4"))
                    {
                        weekday_offer.setText("wednesday offer");
                    }
                    else if (day.equals("5"))
                    {
                        weekday_offer.setText("thursday offer");
                    }
                    else if (day.equals("6"))
                    {
                        weekday_offer.setText("friday offer");
                    }
                    else
                    {
                        weekday_offer.setText("holiday offer");
                    }


                    double i=Double.parseDouble(tam);


                    double c_gst,s_gst,service_charge1,week;
                    c_gst=(i/100)*9;
                    s_gst=(i/100)*9;
                    service_charge1=(i/100)*5;
                    week=(i/100)*15;

                    cgst.setText("+ "+String.valueOf(c_gst));
                    sgst.setText("+ "+String.valueOf(s_gst));
                    servicecharge.setText("+ "+String.valueOf(service_charge1));
                    weekday_amount.setText("- "+String.valueOf(week));

                    double b=(i+c_gst+s_gst+service_charge1)-week;

                    c= (int) Math.round(b);
                    pay_total.setText(String.valueOf(c)+".00 ₹");
                    xyz= String.valueOf(c);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(tb_num))
                {
                    final String keyvalue= snapshot.child(tb_num).child("A").getValue(String.class);
                    String x1="B";
                    String x2="A";
                    String x3="C";

                    {
                        if (keyvalue.equals(x1))
                        {
                            online_pay.setEnabled(true);
                        }
                        else if (keyvalue.equals(x3))
                        {
                            online_pay.setEnabled(false);
                        }
                        else {
                            online_pay.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        online_pay.setOnClickListener(new View.OnClickListener() {
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

                amount_p.setText(String.valueOf(c)+".00 ₹");
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPlus.dismiss();
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

                        pn_number_pay=new String(ph_pop.getEditText().getText().toString());
                        email_pay=new String(ml_pop.getEditText().getText().toString());

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


    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        ab.setTitle("Alert...!");
        ab.setMessage("do you want close this app..?");
        ab.setCancelable(false);
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(customer_bills.this, "See You Soon", Toast.LENGTH_LONG).show();
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

    private void makerazorpayment()
    {

        SharedPreferences sharedPreferences = customer_bills.this.getSharedPreferences("pn_mail", MODE_PRIVATE);
        String xpn = sharedPreferences.getString("pn_pref","");
        String xml = sharedPreferences.getString("ml_pref","");



       // Toast.makeText(this, ""+xpn+" "+xml, Toast.LENGTH_SHORT).show();
        double d=Double.parseDouble(xyz);
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
        int total_payable_amount= c;


        bali_payments_model model=new bali_payments_model(cr_time,total_payable_amount,Table_number,cr_date);
        root4.setValue(model);

//                        Map<String ,Object> map4=new HashMap<>();
//                        //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
//                        map4.put("cr_time",cr_time);
//                        map4.put("total_amount",total_amount);
//                        map4.put("Table_number",Table_number);
//                        map4.put("cr_date",cr_date);
//                        root4.setValue(map4);



        ctmr_bills_total_amount.setText("00.00");
        servicecharge.setText("00.00");
        sgst.setText("00.00");
        cgst.setText("00.00");
        weekday_amount.setText("00.00");
        pay_total.setText("00.00");
        dialogPlus.dismiss();
       // dialogPlus.dismiss();



        Toast.makeText(this, "Sucessfully done", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = customer_bills.this.getSharedPreferences("pn_mail", MODE_PRIVATE);
        String pn_msg = sharedPreferences.getString("pn_pref","");

        SmsManager mysms=SmsManager.getDefault();
        String mms="Your payment of Rs."+String.valueOf(c)+" is successful. \n\n Thanks for visiting ";
        mysms.sendTextMessage(pn_msg,null,mms,null,null);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();

    }


}