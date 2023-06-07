package com.example.bali_ratn_island;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class chef__disp_order_list_adapter extends FirebaseRecyclerAdapter<ctmr_chef_order_lisr_model,chef__disp_order_list_adapter.myviewholder25>{
    String man_id;
    public chef__disp_order_list_adapter(@NonNull FirebaseRecyclerOptions<ctmr_chef_order_lisr_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder25 holder, final int position, @NonNull ctmr_chef_order_lisr_model model) {

        holder.tb_num.setText(model.getTable_number());
        holder.time.setText(model.getTime());

        SharedPreferences sh = holder.tb_num.getContext().getSharedPreferences("id_chef", Context.MODE_PRIVATE);
        man_id= sh.getString("id_c", "");

        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="A";
                String x=model.getTable_number().toString();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference root3=database.getReference("status_of_order");
                Map<String ,Object> map3=new HashMap<>();
                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                map3.put("A",s.toString());
                map3.put("Table",x);
                root3.child(x).setValue(map3);

                Intent i=new Intent(view.getContext(),see_orders.class);
                i.putExtra("num_tab",model.getTable_number().toString());
                i.putExtra("em_id",man_id);
                view.getContext().startActivity(i);
                //Toast.makeText(view.getContext(), "hhh"+model.getTable_number().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.tb_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="A";
                String x=model.getTable_number().toString();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference root3=database.getReference("status_of_order");
                Map<String ,Object> map3=new HashMap<>();
                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                map3.put("A",s.toString());
                map3.put("Table",x);
                root3.child(x).setValue(map3);

                Intent i=new Intent(view.getContext(),see_orders.class);
                i.putExtra("num_tab",model.getTable_number().toString());
                i.putExtra("em_id",man_id);
                view.getContext().startActivity(i);
                //Toast.makeText(view.getContext(), "hhh"+model.getTable_number().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder25 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_disp_order_list_des,parent,false);
        return new myviewholder25(view);
    }

    public class myviewholder25 extends RecyclerView.ViewHolder{

        TextView tb_num,time;
        LinearLayout lo;
        public myviewholder25(@NonNull View itemView) {
            super(itemView);
            tb_num=itemView.findViewById(R.id.chef_disp_order_list_tb_num);
            time=itemView.findViewById(R.id.chef_disp_order_list_cr_time);
            lo=itemView.findViewById(R.id.lnlout);
        }
    }
}
