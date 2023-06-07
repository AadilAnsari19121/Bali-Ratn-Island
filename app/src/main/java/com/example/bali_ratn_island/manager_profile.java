package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bali_ratn_island.databinding.ActivityManagerProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class manager_profile extends drawer_man {

    ActivityManagerProfileBinding activityManagerProfileBinding;

    TextView id,fnm,age,phone,mail,add,pin,state,edu,poz;
    TextView logout;
    CircleImageView cv;
    Button change_pwd_btn;
    String idd;
    String man_id;
    String poz2;
    DatabaseReference db= FirebaseDatabase.getInstance().getReference("staff_of_bali");
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_profile);

        activityManagerProfileBinding=ActivityManagerProfileBinding.inflate(getLayoutInflater());
        setContentView(activityManagerProfileBinding.getRoot());
        allactivityy("Profile");

        id=findViewById(R.id.man_full_info_id);
        fnm=findViewById(R.id.man_full_info_fnm);
        age=findViewById(R.id.man_full_info_age);
        phone=findViewById(R.id.man_full_info_phone);
        mail=findViewById(R.id.man_full_info_mail);
        add=findViewById(R.id.man_full_info_add);
        pin=findViewById(R.id.man_full_info_pin);
        state=findViewById(R.id.man_full_info_state);
        edu=findViewById(R.id.man_full_info_edu);
        poz=findViewById(R.id.man_full_info_poz);

        logout=findViewById(R.id.log_out);

        cv=findViewById(R.id.man_full_info_face);

        change_pwd_btn=findViewById(R.id.man_change_pass_btn);


        SharedPreferences sh6 = getSharedPreferences("staff_id_from_login",MODE_PRIVATE);
        man_id = sh6.getString("staff_id_from_login_id", "");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(man_id))
                {
                    final String stpos = snapshot.child(man_id).child("poz").getValue(String.class);
                    if (stpos.equals("Chef"))
                    {
                        Toolbar toolbar=drawerLayout_man.findViewById(R.id.toolbar);
                        toolbar.setVisibility(View.GONE);

                        NavigationView navigationView_man=drawerLayout_man.findViewById(R.id.nav_view_man);
                        navigationView_man.setVisibility(View.GONE);
                        //startActivity(new Intent(manager_profile.this,chef__home.class ));
//                        Intent i=new Intent(manager_profile.this,chef__home.class);
//                        i.putExtra("id",man_id);
//                        startActivity(i);
                    }
                    else if (stpos.equals("Manager"))
                    {
//                        Intent i=new Intent(manager_profile.this,manager_home.class);
//                        i.putExtra("id",man_id);
//                        startActivity(i);
                        //startActivity(new Intent(manager_profile.this,manager_home.class ));
                    }
                    else
                    {
                        Toast.makeText(manager_profile.this, "wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(manager_profile.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        Intent p=getIntent();
//        man_id=p.getStringExtra("id");

        //Toast.makeText(this, "your"+man_id, Toast.LENGTH_LONG).show();

        //String man_id=getIntent().getStringExtra("id");

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(man_id))
                {
                    String fullname,f_nm,l_nm,img_url;
                    f_nm=snapshot.child(man_id).child("fnamen").getValue(String.class);
                    l_nm=snapshot.child(man_id).child("lname").getValue(String.class);
                    fullname=f_nm+" "+l_nm;
                    id.setText(snapshot.child(man_id).child("staff_id").getValue(String.class));
                    fnm.setText(fullname);
                    age.setText("Age: "+snapshot.child(man_id).child("age").getValue(String.class));
                    phone.setText("Phone: "+snapshot.child(man_id).child("phone").getValue(String.class));
                    mail.setText("Mail: "+snapshot.child(man_id).child("mail").getValue(String.class));
                    add.setText("Address: "+snapshot.child(man_id).child("address").getValue(String.class));
                    pin.setText("Pin Code: "+snapshot.child(man_id).child("pincode").getValue(String.class));
                    state.setText("State: "+snapshot.child(man_id).child("state").getValue(String.class));
                    edu.setText("Education: "+snapshot.child(man_id).child("edu_q").getValue(String.class));
                    poz.setText(snapshot.child(man_id).child("poz").getValue(String.class));
                    img_url=snapshot.child(man_id).child("pimg").getValue(String.class);
                    Glide.with(manager_profile.this).load(img_url).into(cv);

                    poz2=snapshot.child(man_id).child("poz").getValue(String.class);


                    logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FirebaseDatabase.getInstance().getReference().child("login_status_staff").child(man_id).removeValue();
                            startActivity(new Intent(manager_profile.this,home.class));
                        }
                    });

                }
                else
                {
                    Toast.makeText(manager_profile.this, "something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        change_pwd_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.staff_chage_password))
                        .setExpanded(true, LinearLayout.LayoutParams.MATCH_PARENT)
                        .setGravity(Gravity.CENTER)
                        .create();

                dialogPlus.show();

                View myview=dialogPlus.getHolderView();
                TextInputLayout cr_pwd=myview.findViewById(R.id.change_pwd_des_current_password);
                TextInputLayout new_pwd=myview.findViewById(R.id.change_pwd_des_new_password);
                TextInputLayout cn_pwd=myview.findViewById(R.id.change_pwd_des_new_confirm_password);

                Button next=myview.findViewById(R.id.change_pwd_des_next);
                Button new_pwd_btn=myview.findViewById(R.id.change_pwd_des);

                TextView stid6=myview.findViewById(R.id.change_pwd_des_emp_id);

                stid6.setText(man_id);

                new_pwd.setEnabled(false);
                cn_pwd.setEnabled(false);
                new_pwd_btn.setEnabled(false);

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String uspwd=cr_pwd.getEditText().getText().toString().trim();
                        db.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                final String pwwd = snapshot.child(man_id).child("cnf_pwd").getValue(String.class);
                                if (pwwd.equals(uspwd))
                                {
                                    new_pwd.setEnabled(true);
                                    cn_pwd.setEnabled(true);
                                    new_pwd_btn.setEnabled(true);

                                    cr_pwd.setEnabled(false);
                                    next.setEnabled(false);


                                    new_pwd_btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String a5,b5;
                                            a5=new_pwd.getEditText().getText().toString();
                                            b5=cn_pwd.getEditText().getText().toString();

                                            if (!a5.equals(b5))
                                            {
                                                Toast.makeText(manager_profile.this, "check the password", Toast.LENGTH_SHORT).show();
                                            }
                                            else if (a5.equals(b5))
                                            {
                                                //Toast.makeText(manager_profile.this, "sahi he", Toast.LENGTH_SHORT).show();

                                                Map<String ,Object> map16=new HashMap<>();
                                                map16.put("cr_pwd",new_pwd.getEditText().getText().toString());
                                                map16.put("cnf_pwd",cn_pwd.getEditText().getText().toString());

                                                FirebaseDatabase.getInstance().getReference().child("staff_of_bali").child(man_id)
                                                        .updateChildren(map16)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>()
                                                        {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                Toast.makeText(view.getContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                                                                dialogPlus.dismiss();
                                                                FirebaseDatabase.getInstance().getReference().child("login_status_staff").child(man_id).removeValue();

                                                                new Handler().postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        Toast.makeText(manager_profile.this, "Login Again", Toast.LENGTH_LONG).show();
                                                                        startActivity(new Intent(manager_profile.this,staff_login_page.class));

                                                                    }
                                                                },3000);
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener()
                                                        {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e)
                                                            {
                                                                Toast.makeText(manager_profile.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        }
                                    });
                                    //Toast.makeText(manager_profile.this, "correct", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(manager_profile.this, "incorrect password", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void onBackPressed() {




        if (poz2.equals("Manager"))
        {
            AlertDialog.Builder ab=new AlertDialog.Builder(this);
            ab.setTitle("Alert...!");
            ab.setMessage("do you want close this app..?");
            ab.setCancelable(false);
            ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
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
        else if (poz2.equals("Chef"))
        {
            Intent i=new Intent(this,chef__home.class);
            i.putExtra("em_id",man_id);
            startActivity(i);
            super.onBackPressed();
        }
        else
        {
            startActivity(new Intent(this,staff_login_page.class));
        }

        //String man_id=getIntent().getStringExtra("id");

//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.hasChild(man_id))
//                {
//                    final String stpos = snapshot.child(man_id).child("poz").getValue(String.class);
//                    if (stpos.equals("Chef"))
//                    {
//                        //startActivity(new Intent(manager_profile.this,chef__home.class ));
//                        Intent i=new Intent(manager_profile.this,chef__home.class);
//                        i.putExtra("id",man_id);
//                        startActivity(i);
//                    }
//                    else if (stpos.equals("Manager"))
//                    {
//                        Intent i=new Intent(manager_profile.this,manager_home.class);
//                        i.putExtra("id",man_id);
//                        startActivity(i);
//                        //startActivity(new Intent(manager_profile.this,manager_home.class ));
//                    }
//                    else
//                    {
//                        Toast.makeText(manager_profile.this, "wrong", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(manager_profile.this, "wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        //startActivity(new Intent(this,manager_home.class ));

    }
}