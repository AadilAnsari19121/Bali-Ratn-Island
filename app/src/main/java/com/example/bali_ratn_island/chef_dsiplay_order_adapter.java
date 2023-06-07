package com.example.bali_ratn_island;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class chef_dsiplay_order_adapter extends FirebaseRecyclerAdapter<CTMR_cart_model,chef_dsiplay_order_adapter.myviewholder27>{

    public chef_dsiplay_order_adapter(@NonNull FirebaseRecyclerOptions<CTMR_cart_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder27 holder, int position, @NonNull CTMR_cart_model model) {

        //holder.chef_display_order_des_item_name_X_qn_tv.setText(model.getItem_name_X_item_quantity());
    }

    @NonNull
    @Override
    public myviewholder27 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view27= LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_display_whole_order_list_des,parent,false);
        return new chef_dsiplay_order_adapter.myviewholder27(view27);
    }

    class myviewholder27 extends RecyclerView.ViewHolder {
        TextView chef_display_order_des_item_name_X_qn_tv;
        public myviewholder27(@NonNull View itemView) {
            super(itemView);
            //chef_display_order_des_item_name_X_qn_tv=itemView.findViewById(R.id.chef_display_order_des_item_name_X_qn);
        }
    }
}
