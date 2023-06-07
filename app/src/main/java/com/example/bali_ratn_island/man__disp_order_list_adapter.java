package com.example.bali_ratn_island;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class man__disp_order_list_adapter extends FirebaseRecyclerAdapter<ctmr_chef_order_lisr_model,man__disp_order_list_adapter.myviewholder27> {

    public man__disp_order_list_adapter(@NonNull FirebaseRecyclerOptions<ctmr_chef_order_lisr_model> options) {
        super(options);
    }

    DatabaseReference db;
    ValueEventListener listener;
    ListView rcv_123456;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Button bt;
    @Override
    protected void onBindViewHolder(@NonNull myviewholder27 holder, int position, @NonNull ctmr_chef_order_lisr_model model) {
        holder.tb_num.setText(model.getTable_number());
        holder.time.setText(model.getTime());


        //DatabaseReference db,db2;

        holder.tb_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_see_orders))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();



                View myview=dialogPlus.getHolderView();
                rcv_123456=myview.findViewById(R.id.recyclerView123456);
                bt=myview.findViewById(R.id.order_items_cart);
                bt.setVisibility(View.GONE);
                CircleImageView closebt=myview.findViewById(R.id.close_btn);



                String tb_num=model.getTable_number().toString();
                arrayList=new ArrayList<String>();
                db= FirebaseDatabase.getInstance().getReference("order_list_3").child(tb_num);
                arrayAdapter=new ArrayAdapter<String>(view.getContext(), R.layout.menu_sppnr,arrayList);
                rcv_123456.setAdapter(arrayAdapter);

                closebt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPlus.dismiss();
                    }
                });

                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot mydata: snapshot.getChildren())
                        {

                            // tb_num=Table_number;
                            arrayList.add(mydata.child("item_name_X_item_quantity").getValue().toString());
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                dialogPlus.show();
            }

        });

        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_see_orders))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();



                View myview=dialogPlus.getHolderView();
                rcv_123456=myview.findViewById(R.id.recyclerView123456);
                bt=myview.findViewById(R.id.order_items_cart);
                bt.setVisibility(View.GONE);



                String tb_num=model.getTable_number().toString();
                arrayList=new ArrayList<String>();
                db= FirebaseDatabase.getInstance().getReference("order_list_3").child(tb_num);
                arrayAdapter=new ArrayAdapter<String>(view.getContext(), R.layout.menu_sppnr,arrayList);
                rcv_123456.setAdapter(arrayAdapter);

                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot mydata: snapshot.getChildren())
                        {

                            // tb_num=Table_number;
                            arrayList.add(mydata.child("item_name_X_item_quantity").getValue().toString());
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                dialogPlus.show();
            }

        });
    }

    @NonNull
    @Override
    public myviewholder27 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_disp_order_list_des,parent,false);
        return new man__disp_order_list_adapter.myviewholder27(view);
    }

    class myviewholder27 extends RecyclerView.ViewHolder{
        TextView tb_num,time;
        public myviewholder27(@NonNull View itemView) {
            super(itemView);
            tb_num=itemView.findViewById(R.id.chef_disp_order_list_tb_num);
            time=itemView.findViewById(R.id.chef_disp_order_list_cr_time);

        }
    }
}
