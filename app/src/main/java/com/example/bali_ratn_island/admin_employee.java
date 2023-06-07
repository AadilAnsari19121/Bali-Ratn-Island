package com.example.bali_ratn_island;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bali_ratn_island.databinding.ActivityAdminEmployeeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class admin_employee extends drawer_admin {
    ActivityAdminEmployeeBinding activityBinding;
    RecyclerView rcv_employee;
    FloatingActionButton employee_add_btn;
    emp_adapter emp_adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee);

        activityBinding= ActivityAdminEmployeeBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        allactivityy3("Staff");

        SearchView searchView=(SearchView) findViewById(R.id.search_tol);
        searchView.setVisibility(View.VISIBLE);

        rcv_employee=findViewById(R.id.employee_rcv);
        employee_add_btn=findViewById(R.id.float_btn_for_emp);
        employee_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_employee.this,admin_add_employee.class));
            }
        });


        rcv_employee.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<emp_model> options2 =
                new FirebaseRecyclerOptions.Builder<emp_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("staff_of_bali"), emp_model.class)
                        .build();

        emp_adapter=new emp_adapter(options2);
        rcv_employee.setAdapter(emp_adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseRecyclerOptions<emp_model> options =
                        new FirebaseRecyclerOptions.Builder<emp_model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("staff_of_bali").orderByChild("fnamen").startAt(newText).endAt(newText+"\uf8ff"), emp_model.class)
                                .build();
                emp_adapter = new emp_adapter(options);
                emp_adapter.startListening();
                rcv_employee.setAdapter(emp_adapter);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        emp_adapter.startListening();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
        //man_table_book_adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStop() {
        super.onStop();
        emp_adapter.stopListening();
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

