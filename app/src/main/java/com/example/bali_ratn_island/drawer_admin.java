package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class drawer_admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout_admin;
    String man_id;
    DatabaseReference db= FirebaseDatabase.getInstance().getReference("admin");


    @Override
    public void setContentView(View view) {
        drawerLayout_admin=(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_admin,null);
        FrameLayout con_ad=drawerLayout_admin.findViewById(R.id.frame_layout);
        con_ad.addView(view);
        super.setContentView(drawerLayout_admin);

        Toolbar toolbar=drawerLayout_admin.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchView searchView=drawerLayout_admin.findViewById(R.id.search_tol);
        searchView.setVisibility(View.GONE);

        NavigationView navigationView_ad=drawerLayout_admin.findViewById(R.id.nav_view_admin);
        navigationView_ad.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout_admin,toolbar,R.string.open_draw,R.string.close_draw);
        drawerLayout_admin.addDrawerListener(toggle);
        toggle.syncState();

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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout_admin.closeDrawer(GravityCompat.START);

        if(item.getItemId()==R.id.drawer_admin_category)
        {
            startActivity(new Intent(this,admin_cart.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_admin_menu)
        {
            startActivity(new Intent(this,admin_menu.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_admin_table)
        {
            startActivity(new Intent(this,admin_table.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_admin_table_status)
        {
            startActivity(new Intent(this,admin_table_live_status.class));
            overridePendingTransition(0,0);
        }

        else if (item.getItemId()==R.id.drawer_admin_emp)
        {
            startActivity(new Intent(this,admin_employee.class));
            overridePendingTransition(0,0);
        }

        else if (item.getItemId()==R.id.drawer_admin_order)
        {
            startActivity(new Intent(this,admin_order_status.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_admin_total)
        {
            startActivity(new Intent(this,admin_total_sell.class));
            overridePendingTransition(0,0);
        }

        else if (item.getItemId()==R.id.drawer_admin_feedback)
        {
            startActivity(new Intent(this,admin_feedback_see.class));
            overridePendingTransition(0,0);
        }

        else if (item.getItemId()==R.id.drawer_admin_change_pwd)
        {
            DialogPlus dialogPlus=DialogPlus.newDialog(drawerLayout_admin.getContext())
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
                                            Toast.makeText(drawerLayout_admin.getContext(), "check the password", Toast.LENGTH_SHORT).show();
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
                                                                    Toast.makeText(drawerLayout_admin.getContext(), "Login Again", Toast.LENGTH_LONG).show();
                                                                    startActivity(new Intent(drawerLayout_admin.getContext(),staff_login_page.class));

                                                                }
                                                            },3000);
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener()
                                                    {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e)
                                                        {
                                                            Toast.makeText(drawerLayout_admin.getContext(), "Please Try Again", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    }
                                });
                                //Toast.makeText(manager_profile.this, "correct", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(drawerLayout_admin.getContext(), "incorrect password", Toast.LENGTH_SHORT).show();
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

        else if (item.getItemId()==R.id.drawer_admin_log_out)
        {
            FirebaseDatabase.getInstance().getReference().child("login_status_staff").child(man_id).removeValue();

            startActivity(new Intent(drawer_admin.this,home.class));
            overridePendingTransition(0,0);
        }
        else
        {
            startActivity(new Intent(this,admin_table_live_status.class));
            overridePendingTransition(0,0);
        }
        return false;
    }

    protected void allactivityy3(String titleString)
    {
        if (getSupportActionBar() !=null)
        {
            getSupportActionBar().setTitle(titleString);
        }
    }
}