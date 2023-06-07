package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class admin_add_dining_table extends AppCompatActivity {

    EditText dining_table_id,dining_table_capacity;
    Button add_dining_table_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_dining_table);

        dining_table_id=findViewById(R.id.admin_add_dining_table_table_id);
        dining_table_capacity=findViewById(R.id.admin_add_dining_table_table_capicity);
        add_dining_table_bt=findViewById(R.id.admin_add_dining_table_add_btn);

        add_dining_table_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dining_table_id.getText().toString().isEmpty() && dining_table_capacity.getText().toString().isEmpty())
                {
                    Toast.makeText(admin_add_dining_table.this, "Please Enter The Data", Toast.LENGTH_SHORT).show();
                }
                else if (!dining_table_id.getText().toString().isEmpty() && !dining_table_capacity.getText().toString().isEmpty())
                {
                    processaddingDtable();
                }
                else
                {
                    Toast.makeText(admin_add_dining_table.this, "Please Enter The Data", Toast.LENGTH_SHORT).show();
                }
            }

            private void processaddingDtable() {
                String Table_capacity=dining_table_capacity.getText().toString();
                String Table_Id=dining_table_id.getText().toString();

//                dtbl_model obj2=new dtbl_model(Table_capacity);
                Map<String ,Object> map3=new HashMap<>();
                map3.put("table_capacity",dining_table_capacity.getText().toString());
                map3.put("table_id",dining_table_id.getText().toString());

                FirebaseDatabase dtbl_db=FirebaseDatabase.getInstance();
                DatabaseReference node=dtbl_db.getReference("dining_table");
//                node.child("Table Id: "+Table_Id).setValue(obj2)
                node.child("Table Id: "+Table_Id).setValue(map3)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dining_table_id.setText("");
                        dining_table_capacity.setText("");
                        Toast.makeText(admin_add_dining_table.this, "Table Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        moveTaskToBack(false);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want add more category...?").setTitle("Add more...?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                startActivity(new Intent(admin_add_dining_table.this,admin_table.class));
                finish();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}