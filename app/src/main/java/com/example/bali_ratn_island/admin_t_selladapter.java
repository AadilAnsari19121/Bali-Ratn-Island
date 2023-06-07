package com.example.bali_ratn_island;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class admin_t_selladapter extends FirebaseRecyclerAdapter<bali_payments_model,admin_t_selladapter.myviewholder31>{
    public admin_t_selladapter(@NonNull FirebaseRecyclerOptions<bali_payments_model> options) {
        super(options);
    }
    int total_cart_amount;
    @Override
    protected void onBindViewHolder(@NonNull myviewholder31 holder, int position, @NonNull bali_payments_model model) {
        holder.total_sell_des_time.setText(model.getCr_time());
        holder.total_sell_des_date.setText(model.getCr_date());
        holder.total_sell_des_tbnum.setText("Table No. "+model.getTable_number());
        holder.total_sell_des_am.setText("Amount "+model.getTotal_payable_amount());

        int oneproduct=model.getTotal_payable_amount();
        total_cart_amount=total_cart_amount+oneproduct;

        Intent intent=new Intent("MyTotalAmount2");
        intent.putExtra("Total_amount2",total_cart_amount);
        //intent.putExtra("name_item",model.getItem_name());
        LocalBroadcastManager.getInstance(holder.total_sell_des_am.getContext()).sendBroadcast(intent);
    }

    @NonNull
    @Override
    public myviewholder31 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view27= LayoutInflater.from(parent.getContext()).inflate(R.layout.total_sell_des,parent,false);
        return new admin_t_selladapter.myviewholder31(view27);
    }

    class myviewholder31 extends RecyclerView.ViewHolder {
        TextView total_sell_des_time;
        TextView total_sell_des_date;
        TextView total_sell_des_tbnum;
        TextView total_sell_des_am;

        public myviewholder31(@NonNull View itemView) {
            super(itemView);
            total_sell_des_time=itemView.findViewById(R.id.total_sell_des_time);
            total_sell_des_date=itemView.findViewById(R.id.total_sell_des_date);
            total_sell_des_tbnum=itemView.findViewById(R.id.total_sell_des_tbnum);
            total_sell_des_am=itemView.findViewById(R.id.total_sell_des_amount);
        }
    }
}
