package com.example.bali_ratn_island;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class chef_final_see_order_adapter extends FirebaseRecyclerAdapter<chef_displayy_order_model,chef_final_see_order_adapter.myviewholder28>{

    public chef_final_see_order_adapter(@NonNull FirebaseRecyclerOptions<chef_displayy_order_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull chef_final_see_order_adapter.myviewholder28 holder, int position, @NonNull chef_displayy_order_model model) {

        holder.chef_display_order_des_tb_num.setText(model.getTable_number());
        holder.chef_display_order_des_time.setText(model.getCrtime_crdate());

        holder.chef_display_order_des_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "hllo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public chef_final_see_order_adapter.myviewholder28 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view27= LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_display_whole_order_list_des,parent,false);
        return new chef_final_see_order_adapter.myviewholder28(view27);
    }

    class myviewholder28 extends RecyclerView.ViewHolder {
        TextView chef_display_order_des_tb_num;
        TextView chef_display_order_des_time;
        LinearLayout lo;
        public myviewholder28(@NonNull View itemView) {
            super(itemView);
            chef_display_order_des_tb_num=itemView.findViewById(R.id.chef_display_order_des_item_tb_num);
            chef_display_order_des_time=itemView.findViewById(R.id.chef_display_ordertime);
            lo=itemView.findViewById(R.id.lnlout);
        }
    }
}

