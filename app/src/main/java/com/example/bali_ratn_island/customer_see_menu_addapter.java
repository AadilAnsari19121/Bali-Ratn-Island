package com.example.bali_ratn_island;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customer_see_menu_addapter extends FirebaseRecyclerAdapter<menu_item_model,customer_see_menu_addapter.myviewholder7>{

    public customer_see_menu_addapter(@NonNull FirebaseRecyclerOptions<menu_item_model> options) {
        super(options);
    }
    //DatabaseReference db= FirebaseDatabase.getInstance().getReference("staff_of_bali");
    @Override
    protected void onBindViewHolder(@NonNull myviewholder7 holder, int position, @NonNull menu_item_model model) {

        holder.tv_name_menu.setText(model.getMenu_item_Name());
        holder.tv_price_menu.setText(model.getMenu_item_Price());
        String cat_name_from_cust,cat_image_from_cust;


        //cat_name_from_cust=getIntent().getStringExtra("cust_cat_name_35");
        //cat_image_from_cust=getIntent().getStringExtra("cust_cat_pic_35");


//        db.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.hasChild(cat_name_from_cust.toString())) {
//                    final String pwwd = snapshot.child(usnm).child("cnf_pwd").getValue(String.class);
//                    final String stpos = snapshot.child(usnm).child("poz").getValue(String.class);
//
//                    if (pwwd.equals(uspwd)) {
//                        if (stpos.equals("Chef")) {
//
//                            Toast.makeText(staff_login_page.this, "your chef", Toast.LENGTH_SHORT).show();
//                        } else if (stpos.equals("Manager")) {
//                            startActivity(new Intent(staff_login_page.this,manager_home.class));
//                            //Toast.makeText(staff_login_page.this, "your man", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(staff_login_page.this, "chakka he tu", Toast.LENGTH_SHORT).show();
//                        }
//                        //Toast.makeText(staff_login_page.this, "login hogaya", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(staff_login_page.this, "nahi hua", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(staff_login_page.this, "User Not Found", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

    @NonNull
    @Override
    public myviewholder7 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class myviewholder7 extends RecyclerView.ViewHolder {
        TextView tv_name_menu,tv_price_menu;
        Spinner sp_quanity_menu;
        public myviewholder7(@NonNull View itemView) {
            super(itemView);
            tv_name_menu=(TextView) itemView.findViewById(R.id.customer_rcv_design_menu_name);
            tv_price_menu=(TextView) itemView.findViewById(R.id.customer_rcv_design_menu_price);



        }
    }
}
