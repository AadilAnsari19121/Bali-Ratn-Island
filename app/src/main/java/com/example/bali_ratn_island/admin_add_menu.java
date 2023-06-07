package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_add_menu extends AppCompatActivity {

    ArrayList<String> arlist;
    ArrayAdapter<String> dataAdapter;
    DatabaseReference ref;
    ValueEventListener listener;
    TextInputLayout ab;
    AutoCompleteTextView cat;
    TextInputLayout menu_item_id,menu_item_name,menu_item_price;
    Button btn_add_menu;
    String item_cat;

    DatabaseReference db23;

    String xyz;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_menu);
        ab=findViewById(R.id.xyz);
        cat=findViewById(R.id.cat_menu);

        menu_item_id=findViewById(R.id.menu_id);
        menu_item_name=findViewById(R.id.menu_name);
        menu_item_price=findViewById(R.id.menu_price);

        btn_add_menu=findViewById(R.id.add_menu);

        db23=FirebaseDatabase.getInstance().getReference("menu_items");

//        db23.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                xyz=snapshot.getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        arlist = new ArrayList<String>();
        ref=FirebaseDatabase.getInstance().getReference("category_table");
        dataAdapter = new ArrayAdapter<String>(admin_add_menu.this, R.layout.menu_sppnr, arlist);
        cat.setAdapter(dataAdapter);
        listener = ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot mydata : snapshot.getChildren())
                        arlist.add(mydata.child("cat_name").getValue().toString());
                        dataAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item_cat = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(admin_add_employee.this, "state is: "+item_state+i, Toast.LENGTH_SHORT).show();
            }
        });



        btn_add_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!menu_item_id.getEditText().getText().toString().isEmpty() || !menu_item_name.getEditText().getText().toString().isEmpty() || !menu_item_price.getEditText().getText().toString().isEmpty())
                {
//                    if (menu_item_id.getEditText().getText().equals(xyz)) {
//                        Toast.makeText(admin_add_menu.this, "Enter Another Id", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {


                    String menu_item_Id, menu_item_Name, menu_item_Price, menu_item_Cat;
                    menu_item_Id = menu_item_id.getEditText().getText().toString();
                    menu_item_Name = menu_item_name.getEditText().getText().toString();
                    menu_item_Price = menu_item_price.getEditText().getText().toString();
                    menu_item_Cat = item_cat.toString();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference root = database.getReference("menu_items");

                    menu_item_model obj = new menu_item_model(menu_item_Id, menu_item_Name, menu_item_Price, menu_item_Cat);
                    //root.child(menu_item_Cat).push().setValue(obj)
                    root.child("Item Id: " + menu_item_id.getEditText().getText().toString()).setValue(obj)

                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    menu_item_id.getEditText().setText("");
                                    menu_item_name.getEditText().setText("");
                                    menu_item_price.getEditText().setText("");
                                    cat.setSelection(0);

                                    Toast.makeText(admin_add_menu.this, "Menu Item Added Successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(admin_add_menu.this, "Failed Please Try Again", Toast.LENGTH_SHORT).show();
                                }
                            });

                //}
                }

                else
                {
                    Toast.makeText(admin_add_menu.this, "Fill The Details Please", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
//        new AlertDialog.Builder(this)
        moveTaskToBack(false);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want add more items...?").setTitle("Add more...?");
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
                startActivity(new Intent(admin_add_menu.this,admin_menu.class));
                finish();
                dialogInterface.dismiss();
            }
        });
        builder.show();
//        super.onBackPressed();
    }
}