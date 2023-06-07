package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class home extends AppCompatActivity {

    MaterialButton gotothecutsec,gotothestaffsec;
    String Table_number_CTMR;
    int number_tab;

    DatabaseReference db= FirebaseDatabase.getInstance().getReference("table_booked_status");
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gotothecutsec=findViewById(R.id.gotothe_customer_section);
        gotothestaffsec=findViewById(R.id.gotothe_staff_section);




        gotothecutsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus=DialogPlus.newDialog(home.this)
                        .setContentHolder(new ViewHolder(R.layout.table_number_ctmr_des_enter))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();
                dialogPlus.show();

                View myview=dialogPlus.getHolderView();
                TextInputLayout tbn=myview.findViewById(R.id.table_number_for_CTMR_des_number);

                Button en_bt=myview.findViewById(R.id.table_number_for_CTMR_des_btn);

                en_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        number_tab= Integer.parseInt(tbn.getEditText().getText().toString().trim());
                       // Table_number_CTMR=tbn.getEditText().getText().toString().trim();

                        if (number_tab>0 && number_tab<10)
                        {
                            Table_number_CTMR="0"+String.valueOf(number_tab);
                        }
                        else if (number_tab>9)
                        {
                            Table_number_CTMR=String.valueOf(number_tab);
                        }
                        db.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(Table_number_CTMR))
                                {
                                    dialogPlus.dismiss();

                                    SharedPreferences sharedPref = getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("table_number_ctmr", Table_number_CTMR);
                                    editor.apply();
                                    startActivity(new Intent(home.this,cutomer_category.class));

                                }
                                else
                                {
                                    Toast.makeText(home.this, "enter valid table number please", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }
                });

            }
        });

        gotothestaffsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,staff_login_page.class));
            }
        });
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finishAffinity();
    }
}