package com.example.bali_ratn_island;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class emp_full_info extends AppCompatActivity {
    TextView id,fnm,lnm,age,phone,mail,add,pin,state,edu,poz;
    CircleImageView cv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_full_info);
        id=findViewById(R.id.emp_full_info_id);
        fnm=findViewById(R.id.emp_full_info_fnm);
        age=findViewById(R.id.emp_full_info_age);
        phone=findViewById(R.id.emp_full_info_phone);
        mail=findViewById(R.id.emp_full_info_mail);
        add=findViewById(R.id.emp_full_info_add);
        pin=findViewById(R.id.emp_full_info_pin);
        state=findViewById(R.id.emp_full_info_state);
        edu=findViewById(R.id.emp_full_info_edu);
        poz=findViewById(R.id.emp_full_info_poz);

        cv=findViewById(R.id.emp_full_info_face);



        String ffnm,llnm,fullnm,pimg;
        ffnm=getIntent().getStringExtra("em_fnm");
        llnm=getIntent().getStringExtra("em_lnm");
        pimg=getIntent().getStringExtra("em_face");
        Glide.with(emp_full_info.this).load(pimg).into(cv);

        fnm.setText(ffnm+" "+llnm);

        id.setText(getIntent().getStringExtra("em_id"));
        age.setText(getIntent().getStringExtra("em_age"));
        phone.setText(getIntent().getStringExtra("em_pn"));
        mail.setText(getIntent().getStringExtra("em_ml"));
        add.setText(getIntent().getStringExtra("em_add"));
        pin.setText(getIntent().getStringExtra("em_pin"));
        state.setText(getIntent().getStringExtra("em_stat"));
        edu.setText(getIntent().getStringExtra("em_ed"));
        poz.setText(getIntent().getStringExtra("em_poz"));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,admin_employee.class ));
        finish();
        super.onBackPressed();
    }
}