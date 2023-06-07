package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class staff_login_page extends AppCompatActivity {

    TextInputLayout uslognm,uslogps;
    //EditText username_log,password_log;
    Button button_login,login,man;

    DatabaseReference db= FirebaseDatabase.getInstance().getReference("staff_of_bali");
    DatabaseReference db2= FirebaseDatabase.getInstance().getReference("admin");
    DatabaseReference db4= FirebaseDatabase.getInstance().getReference("login_status_staff");
    FirebaseDatabase db3=FirebaseDatabase.getInstance();
//
    String shred_id="x";
    String shred_pwd="x";
    String shred_pos="x";
//    String em_id,em_pwd,em_pos;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login_page);
        uslognm=findViewById(R.id.user_id);
        uslogps=findViewById(R.id.user_password);

        stopService(new Intent(this,music_service.class));

        man=findViewById(R.id.man);
        button_login=findViewById(R.id.log_button);
        login=findViewById(R.id.log_button2);

        man.setVisibility(View.GONE);
        button_login.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = this.getSharedPreferences("idpwd", MODE_PRIVATE);
        shred_id = sharedPreferences.getString("id","");
        shred_pos = sharedPreferences.getString("pos","");
        shred_pwd = sharedPreferences.getString("pwd","");


        db4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(shred_id))
                {
                   // final String pwwd = snapshot.child(shred_id).child("id").getValue(String.class);
                   // final String stpos = snapshot.child(shred_id).child("pos").getValue(String.class);

                    if (shred_pos.equals("Chef"))
                    {
                        SharedPreferences sharedPreferences6 = getSharedPreferences("staff_id_from_login",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit6 = sharedPreferences6.edit();
                        myEdit6.putString("staff_id_from_login_id",shred_id);
                        myEdit6.commit();

                        Intent i = new Intent(staff_login_page.this, chef__home.class);
                        // i.putExtra("em_id", usnm);
                        startActivity(i);
                    }
                    else if (shred_pos.equals("Manager"))
                    {
                        SharedPreferences sharedPreferences6 = getSharedPreferences("staff_id_from_login",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit6 = sharedPreferences6.edit();
                        myEdit6.putString("staff_id_from_login_id",shred_id);
                        myEdit6.commit();

                        Intent i = new Intent(staff_login_page.this, manager_table_book.class);
                        // i.putExtra("em_id", usnm);
                        startActivity(i);
                    }

                    else if (shred_pos.equals("Admin"))
                    {
                        SharedPreferences sharedPreferences6 = getSharedPreferences("staff_id_from_login",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit6 = sharedPreferences6.edit();
                        myEdit6.putString("staff_id_from_login_id",shred_id);
                        myEdit6.commit();

                        Intent i = new Intent(staff_login_page.this, admin_table_live_status.class);
                        // i.putExtra("em_id", usnm);
                        startActivity(i);
                    }
                }
                else
                {
                    //Eat 5 star do nothing
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        db2.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                id=snapshot.child("id").getValue().toString().trim();//retrieved name
//                pwd=snapshot.child("password").getValue().toString();//retrieved name
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        db.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                em_id=snapshot.child("staff_id").getValue().toString().trim();
//                em_pwd=snapshot.child("cnf_pwd").getValue().toString().trim();
//                em_pos=snapshot.child("poz").getValue().toString().trim();
//
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(staff_login_page.this,manager_home.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usnm = uslognm.getEditText().getText().toString().trim();
                String uspwd = uslogps.getEditText().getText().toString().trim();

                if (usnm.isEmpty())
                {
                    Toast.makeText(staff_login_page.this, "Enter the details", Toast.LENGTH_SHORT).show();
                }
                else if (uspwd.isEmpty())
                {
                    Toast.makeText(staff_login_page.this, "Enter the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(usnm)) {
                                final String pwwd = snapshot.child(usnm).child("cnf_pwd").getValue(String.class);
                                final String stpos = snapshot.child(usnm).child("poz").getValue(String.class);

                                if (pwwd.equals(uspwd)) {
                                    if (stpos.equals("Chef")) {

                                        SharedPreferences sharedPreferences6 = getSharedPreferences("staff_id_from_login",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit6 = sharedPreferences6.edit();
                                        myEdit6.putString("staff_id_from_login_id",usnm);
                                        myEdit6.commit();

                                        DatabaseReference root3=db3.getReference("login_status_staff");

                                        Map<String ,Object> map12=new HashMap<>();
                                        map12.put("id",usnm);
                                        map12.put("pwd",uspwd);
                                        map12.put("pos",stpos);
                                        root3.child(usnm).setValue(map12);

                                        SharedPreferences sharedPreferences7 = getSharedPreferences("idpwd",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit7 = sharedPreferences7.edit();
                                        myEdit7.putString("id",usnm);
                                        myEdit7.putString("pwd",uspwd);
                                        myEdit7.putString("pos",stpos);
                                        myEdit7.commit();

                                        Intent i = new Intent(staff_login_page.this, chef__home.class);
                                       // i.putExtra("em_id", usnm);
                                        startActivity(i);
                                       // Toast.makeText(staff_login_page.this, "your chef", Toast.LENGTH_SHORT).show();
                                    } else if (stpos.equals("Manager")) {

                                        SharedPreferences sharedPreferences6 = getSharedPreferences("staff_id_from_login",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit6 = sharedPreferences6.edit();
                                        myEdit6.putString("staff_id_from_login_id",usnm);
                                        myEdit6.commit();


                                        DatabaseReference root3=db3.getReference("login_status_staff");

                                        Map<String ,Object> map12=new HashMap<>();
                                        map12.put("id",usnm);
                                        map12.put("pwd",uspwd);
                                        map12.put("pos",stpos);
                                        root3.child(usnm).setValue(map12);

                                        SharedPreferences sharedPreferences7 = getSharedPreferences("idpwd",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit7 = sharedPreferences7.edit();
                                        myEdit7.putString("id",usnm);
                                        myEdit7.putString("pwd",uspwd);
                                        myEdit7.putString("pos",stpos);
                                        myEdit7.commit();

                                        Intent i = new Intent(staff_login_page.this, manager_table_book.class);
                                        //i.putExtra("em_id", usnm);
                                        startActivity(i);
                                        //Toast.makeText(staff_login_page.this, "your man", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(staff_login_page.this, "", Toast.LENGTH_SHORT).show();
                                    }
                                    //Toast.makeText(staff_login_page.this, "login hogaya", Toast.LENGTH_SHORT).show();
                                }

                                else
                                {
                                    Toast.makeText(staff_login_page.this, "Something Was Wrong, Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    db2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            final String ad_id = snapshot.child("id").getValue(String.class);
                            final String pwwd = snapshot.child("admin_password").getValue(String.class);

                            if (ad_id.equals(usnm)) {
                                if (pwwd.equals(uspwd)) {

                                    final String stpos="Admin";
                                    DatabaseReference root3=db3.getReference("login_status_staff");

                                    Map<String ,Object> map12=new HashMap<>();
                                    map12.put("id",usnm);
                                    map12.put("pwd",uspwd);
                                    map12.put("pos",stpos);
                                    root3.child(usnm).setValue(map12);

                                    SharedPreferences sharedPreferences7 = getSharedPreferences("idpwd",MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit7 = sharedPreferences7.edit();
                                    myEdit7.putString("id",usnm);
                                    myEdit7.putString("pwd",uspwd);
                                    myEdit7.putString("pos",stpos);
                                    myEdit7.commit();

                                    startActivity(new Intent(staff_login_page.this, admin_table_live_status.class));
                                }
                            }
                            else
                            {
                                //Toast.makeText(staff_login_page.this, "something wrong", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(staff_login_page.this,chef__home.class));
            }
        });
    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,home.class));
    }
}