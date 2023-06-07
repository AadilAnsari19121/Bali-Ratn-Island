package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class admin_home extends AppCompatActivity implements View.OnClickListener {

    Button cat,recipe,table,table_live,employee,orders,total_sell,change_pwd,fb;
    DatabaseReference db= FirebaseDatabase.getInstance().getReference("admin");
    String man_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        cat=findViewById(R.id.Button_cat);
        recipe=findViewById(R.id.Button_menu);
        table=findViewById(R.id.Button_table);
        table_live=findViewById(R.id.Button_table_status);
        employee=findViewById(R.id.Button_employee);
        orders=findViewById(R.id.Button_orders);
        total_sell=findViewById(R.id.Button_sell);
        change_pwd=findViewById(R.id.Button_change_pwd);
        fb=findViewById(R.id.Button_fb);


        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String id = snapshot.child("id").getValue(String.class);
                man_id=id;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        cat.setOnClickListener(this);
        recipe.setOnClickListener(this);
        table.setOnClickListener(this);
        table_live.setOnClickListener(this);
        employee.setOnClickListener(this);
        orders.setOnClickListener(this);
        total_sell.setOnClickListener(this);
        fb.setOnClickListener(this);



        change_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.staff_chage_password))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
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
                cr_pwd.setHint("Enter Code");

                next.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        String uspwd=cr_pwd.getEditText().getText().toString().trim();
                        db.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                final String pwwd = snapshot.child("code").getValue(String.class);
                                if (pwwd.equals(uspwd))
                                {
                                    new_pwd.setEnabled(true);
                                    cn_pwd.setEnabled(true);
                                    new_pwd_btn.setEnabled(true);

                                    cr_pwd.setEnabled(false);
                                    next.setEnabled(false);


                                    new_pwd_btn.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View view)
                                        {
                                            String a5,b5;
                                            a5=new_pwd.getEditText().getText().toString();
                                            b5=cn_pwd.getEditText().getText().toString();

                                            if (!a5.equals(b5))
                                            {
                                                Toast.makeText(admin_home.this, "check the password", Toast.LENGTH_SHORT).show();
                                            }
                                            else if (a5.equals(b5))
                                            {
                                                //Toast.makeText(manager_profile.this, "sahi he", Toast.LENGTH_SHORT).show();

                                                Map<String ,Object> map16=new HashMap<>();
                                                map16.put("admin_password",new_pwd.getEditText().getText().toString());
                                                //map16.put("cnf_pwd",cn_pwd.getEditText().getText().toString());

                                                FirebaseDatabase.getInstance().getReference().child("admin")
                                                        .updateChildren(map16)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>()
                                                        {
                                                            @Override
                                                            public void onSuccess(Void unused)
                                                            {
                                                                Toast.makeText(view.getContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                                                                dialogPlus.dismiss();

                                                                new Handler().postDelayed(new Runnable()
                                                                {
                                                                    @Override
                                                                    public void run()
                                                                    {
                                                                        Toast.makeText(admin_home.this, "Login Again", Toast.LENGTH_LONG).show();
                                                                        startActivity(new Intent(admin_home.this,staff_login_page.class));

                                                                    }
                                                                },3000);
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener()
                                                        {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e)
                                                            {
                                                                Toast.makeText(admin_home.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                        }
                                    });
                                    //Toast.makeText(manager_profile.this, "correct", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(admin_home.this, "incorrect password", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error)
                            {

                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view==cat)
        {
            startActivity(new Intent(this,admin_cart.class));
        }

        else if (view==recipe)
        {
            startActivity(new Intent(this,admin_menu.class));
        }

        else if (view==table)
        {
            startActivity(new Intent(this,admin_table.class));
        }
        else if (view==table_live)
        {
            startActivity(new Intent(this,admin_table_live_status.class));
        }

        else if (view==employee) {
            startActivity(new Intent(this,admin_employee.class));
        }
        else if (view==orders) {
            startActivity(new Intent(this,admin_order_status.class));
        }

        else if (view==total_sell) {
            startActivity(new Intent(this,admin_total_sell.class));
        }

        else if (view==fb) {
            startActivity(new Intent(this,admin_feedback_see.class));
        }

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