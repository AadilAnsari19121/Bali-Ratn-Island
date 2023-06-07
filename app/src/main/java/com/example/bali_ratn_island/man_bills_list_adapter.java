package com.example.bali_ratn_island;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class man_bills_list_adapter extends FirebaseRecyclerAdapter<ctmr_chef_order_lisr_model,man_bills_list_adapter.myviewholder30>{

    public man_bills_list_adapter(@NonNull FirebaseRecyclerOptions<ctmr_chef_order_lisr_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder30 holder, int position, @NonNull ctmr_chef_order_lisr_model model) {
        holder.tb_num.setText(model.getTable_number());
        holder.time.setText(model.getTime());

        holder.tb_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),manger_pay_bill.class);
                i.putExtra("num_tab",model.getTable_number().toString());
                view.getContext().startActivity(i);
            }
        });

        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),manger_pay_bill.class);
                i.putExtra("num_tab",model.getTable_number().toString());
                view.getContext().startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public myviewholder30 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_disp_order_list_des,parent,false);
        return new man_bills_list_adapter.myviewholder30(view);
    }

    class myviewholder30 extends RecyclerView.ViewHolder {
        TextView tb_num,time;
        public myviewholder30(@NonNull View itemView) {
            super(itemView);
            tb_num=itemView.findViewById(R.id.chef_disp_order_list_tb_num);
            time=itemView.findViewById(R.id.chef_disp_order_list_cr_time);

        }
    }
}
