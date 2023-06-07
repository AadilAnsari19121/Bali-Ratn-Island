package com.example.bali_ratn_island;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class man_table_book_adapter extends FirebaseRecyclerAdapter<dtbl_model,man_table_book_adapter.myviewholder5>{

    public man_table_book_adapter(@NonNull FirebaseRecyclerOptions<dtbl_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder5 holder, @SuppressLint("RecyclerView") final int position, @NonNull final dtbl_model model) {
        holder.man_dtbl_id.setText(model.getTable_id());
        holder.man_dtbl_cap.setText(model.getTable_capacity());
        holder.imv.setImageResource(R.mipmap.dining_table);

        holder.table_book.setEnabled(true);
        holder.table_free.setEnabled(false);
        //DatabaseReference dtbl_db = FirebaseDatabase.getInstance().getReference("table_booked_status");
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference root=database.getReference("table_booked_status");


        holder.table_book.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String Table_capacity=holder.man_dtbl_cap.getText().toString();
                String Table_Id=holder.man_dtbl_id.getText().toString();

//                dtbl_model obj2=new dtbl_model(Table_capacity);
                Map<String ,Object> map3=new HashMap<>();
                map3.put("table_capacity",holder.man_dtbl_cap.getText().toString());
                map3.put("table_id",holder.man_dtbl_id.getText().toString());



//                node.child("Table Id: "+Table_Id).setValue(obj2)
                root.child(holder.man_dtbl_id.getText().toString()).setValue(map3)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        holder.imv.setImageResource(R.mipmap.dining_table2);
                                        holder.table_free.setEnabled(true);
                                        holder.table_book.setEnabled(false);
                                        Toast.makeText(view.getContext(), "Table Number : "+model.getTable_id().toString()+" Booked", Toast.LENGTH_SHORT).show();
                                    }
                                });

                return false;
            }
        });

        holder.table_free.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                holder.imv.setImageResource(R.mipmap.dining_table);
                holder.table_free.setEnabled(false);
                holder.table_book.setEnabled(true);
                FirebaseDatabase.getInstance().getReference().child("table_booked_status").child(model.Table_id).removeValue();
                Toast.makeText(view.getContext(), "Table Number : "+model.getTable_id().toString()+" Now Free", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(model.Table_id))
                {
                    holder.imv.setImageResource(R.mipmap.dining_table2);
                    holder.table_book.setEnabled(false);
                    holder.table_free.setEnabled(true);
                }
                else
                {
                    holder.imv.setImageResource(R.mipmap.dining_table);
                    holder.table_book.setEnabled(true);
                    holder.table_free.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @NonNull
    @Override
    public myviewholder5 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view3= LayoutInflater.from(parent.getContext()).inflate(R.layout.man_table_booking_rcv_design,parent,false);
        return new myviewholder5(view3);
    }

    class myviewholder5 extends RecyclerView.ViewHolder{

        TextView man_dtbl_id,man_dtbl_cap;
        Button table_book,table_free;
        ImageView imv;

        public myviewholder5(@NonNull View itemView) {
            super(itemView);
            man_dtbl_id=(TextView)itemView.findViewById(R.id.man_rcv_des_dining_table_id);
            man_dtbl_cap=(TextView)itemView.findViewById(R.id.man_rcv_desdining_table_capcity);
            table_book=(Button) itemView.findViewById(R.id.man_booking_table_rcv_book_table_btn);
            table_free=(Button) itemView.findViewById(R.id.man_booking_table_rcv_free_table_btn);

            imv=(ImageView) itemView.findViewById(R.id.man_rcv_dtbl_imv);
        }
    }
}
