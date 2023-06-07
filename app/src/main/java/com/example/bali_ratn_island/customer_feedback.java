package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bali_ratn_island.databinding.ActivityCustomerBillsBinding;
import com.example.bali_ratn_island.databinding.ActivityCustomerFeedbackBinding;
import com.example.bali_ratn_island.databinding.ActivityCustomerSeeCartBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class customer_feedback extends drawer_ctmr {

    ActivityCustomerFeedbackBinding activityCustomerFeedbackBinding;

    TextInputLayout name,pn,mail,msg;
    RatingBar food_rating,ser_rating;
    Button submit_fb;
    RadioButton yesrb,norb;
    String food_rat,ser_rat;
    RadioGroup rg;
    TextView frtv,srtv;

    Pattern pattern;
    Matcher matcher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedback);

        activityCustomerFeedbackBinding= ActivityCustomerFeedbackBinding.inflate(getLayoutInflater());
        setContentView(activityCustomerFeedbackBinding.getRoot());
        allactivityy2("Feedback");

        SharedPreferences sharedPreferences = customer_feedback.this.getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        String Table_number = sharedPreferences.getString("table_number_ctmr","");

        name=findViewById(R.id.ctmr_feedback_name);
        pn=findViewById(R.id.ctmr_feedback_phone);
        mail=findViewById(R.id.ctmr_feedback_mail);
        msg=findViewById(R.id.ctmr_feedback_long_msg);

        rg=findViewById(R.id.rdgrp);

        food_rating=findViewById(R.id.food_rating);
        ser_rating=findViewById(R.id.service_rating);

        submit_fb=findViewById(R.id.ctmr_feedback_done_btn);

        yesrb=findViewById(R.id.yes_rdb);
        norb=findViewById(R.id.no_rdb);

        frtv=findViewById(R.id.food_raring_text);
        srtv=findViewById(R.id.ser_raring_text);



        food_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                food_rat=String.valueOf(v);

                if (food_rat.equals("0.0"))
                {
                    frtv.setText("very bad 😔");
                }
                else if (food_rat.equals("0.5"))
                {
                    frtv.setText("bad ☹");
                }
                else if (food_rat.equals("1.0"))
                {
                    frtv.setText("bad ☹");
                }
                else if (food_rat.equals("1.5"))
                {
                    frtv.setText("below average 😢");
                }
                else if (food_rat.equals("2.0"))
                {
                    frtv.setText("below average 😢");
                }
                else if (food_rat.equals("2.5"))
                {
                    frtv.setText("average 😐");
                }
                else if (food_rat.equals("3.0"))
                {
                    frtv.setText("good 😃");
                }
                else if (food_rat.equals("3.5"))
                {
                    frtv.setText("good 😃");
                }
                else if (food_rat.equals("4.0"))
                {
                    frtv.setText("very good 😃");
                }
                else if (food_rat.equals("4.5"))
                {
                    frtv.setText("excellent 😄");
                }
                else if (food_rat.equals("5.0"))
                {
                    frtv.setText("delicious 😋");
                }
                else
                {
                    frtv.setText("");
                }




            }
        });

        ser_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ser_rat=String.valueOf(v);


                if (ser_rating.equals("0.0"))
                {
                    srtv.setText("very bad 😔");
                }
                else if (ser_rating.equals("0.5"))
                {
                    srtv.setText("bad ☹");
                }
                else if (ser_rating.equals("1.0"))
                {
                    srtv.setText("bad ☹");
                }
                else if (ser_rating.equals("1.5"))
                {
                    srtv.setText("below average 😢");
                }
                else if (ser_rating.equals("2.0"))
                {
                    srtv.setText("below average 😢");
                }
                else if (ser_rating.equals("2.5"))
                {
                    srtv.setText("average 😐");
                }
                else if (ser_rating.equals("3.0"))
                {
                    srtv.setText("good 😃");
                }
                else if (ser_rating.equals("3.5"))
                {
                    srtv.setText("good 😃");
                }
                else if (ser_rating.equals("4.0"))
                {
                    srtv.setText("very good 😃");
                }
                else if (ser_rating.equals("4.5"))
                {
                    srtv.setText("excellent 😄");
                }
                else if (ser_rating.equals("5.0"))
                {
                    srtv.setText("delicious 😋");
                }
                else
                {
                    srtv.setText("");
                }


            }
        });




        pattern= Pattern.compile("[0-9]{10}");





        submit_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Customer_name = name.getEditText().getText().toString();
                String Customer_phone = pn.getEditText().getText().toString();
                String Customer_mail = mail.getEditText().getText().toString();
                String Customer_longmsg = msg.getEditText().getText().toString();
                String Customer_food_rating = food_rat.toString();
                String Customer_service_rating = ser_rat.toString();
                String tb_num = Table_number;

                if (name.getEditText().getText().toString().isEmpty() &&
                        pn.getEditText().getText().toString().isEmpty() &&
                        mail.getEditText().getText().toString().isEmpty() &&
                        msg.getEditText().getText().toString().isEmpty()) {
                    Toast.makeText(customer_feedback.this, "Enter Detail Please", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //String  x= String.valueOf(android.util.Patterns.PHONE.matcher(pn.getEditText().getText().toString()).matches());
                    if (!isValidPhoneNumber(Customer_phone)) {
                        Toast.makeText(customer_feedback.this, "Enter correct phone number", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (!isValidEmail(Customer_mail)) {
                            Toast.makeText(customer_feedback.this, "Enter correct Email", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {


                            String phoneNumber = pn.getEditText().getText().toString(); // Phone number to send the SMS to
                            String message = "Thanks for your feedback!"; // Message to send

                            try {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                                Toast.makeText(getApplicationContext(), "SMS sent successfully!", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "SMS failed to send!", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }


                            // Toast.makeText(customer_feedback.this, x_number+y_name, Toast.LENGTH_SHORT).show();

                            ProgressDialog dialog = new ProgressDialog(customer_feedback.this);
                            dialog.setTitle("Feedback");
                            dialog.show();

                            Calendar calender = Calendar.getInstance();

                            SimpleDateFormat crd = new SimpleDateFormat("MM_dd_yyyy");
                            SimpleDateFormat crt = new SimpleDateFormat("HH:mm:ss_a");

                            String cr_time = crt.format(calender.getTime());
                            String cr_date = crd.format(calender.getTime());

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference root = database.getReference("feedback").push();

                            feedback_model fb = new feedback_model(Customer_name, Customer_phone, Customer_mail, Customer_longmsg, Customer_food_rating, Customer_service_rating, tb_num, cr_time, cr_date);

                            String x = cr_time + " " + cr_date;
                            root.setValue(fb).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            Toast.makeText(customer_feedback.this, "Thank You", Toast.LENGTH_SHORT).show();

    //                                        SmsManager mysms = SmsManager.getDefault();
    //                                        String sms = "Drear " + Customer_name + "\n\nThank You for Visiting Our Restaurant And Also Thanks for Your Valuable Feedback. Hope You Enjoyed The Meal.\n\n We Hope You Will Visit Our Restaurant Again.";
    //                                        mysms.sendTextMessage(Customer_phone, null, sms, null, null);


                                            //ye naya he


                                            name.getEditText().setText("");
                                            pn.getEditText().setText("");
                                            mail.getEditText().setText("");
                                            msg.getEditText().setText("");
                                            food_rating.setRating(0);
                                            ser_rating.setRating(0);
                                            frtv.setText("");
                                            srtv.setText("");
                                            dialog.dismiss();

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(customer_feedback.this, "Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                    }

                }


                }
            }
        });
    }

    private boolean isValidEmail(String customer_mail) {
        String regex = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$"; // regular expression to match email addresses
        return customer_mail.matches(regex);
    }

    private boolean isValidPhoneNumber(String customer_phone) {
        String regex = "\\d{10}"; // regular expression to match 10 digit phone numbers
        return customer_phone.matches(regex);
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
                        Toast.makeText(customer_feedback.this, "See You Soon", Toast.LENGTH_LONG).show();
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