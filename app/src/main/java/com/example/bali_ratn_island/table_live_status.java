package com.example.bali_ratn_island;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class table_live_status extends FirebaseRecyclerAdapter<dtbl_model,table_live_status.myviewholder10>{
    public table_live_status(@NonNull FirebaseRecyclerOptions<dtbl_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder10 holder10, int position, @NonNull dtbl_model model) {
        holder10.table_live_tvv.setText(model.getTable_id());
        holder10.table_live_imgg.setImageResource(R.mipmap.dining_table);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference root=database.getReference("table_booked_status");

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(model.Table_id))
                {
                    holder10.table_live_imgg.setImageResource(R.mipmap.dining_table2);
                }
                else
                {
                    holder10.table_live_imgg.setImageResource(R.mipmap.dining_table);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @NonNull
    @Override
    public myviewholder10 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view4= LayoutInflater.from(parent.getContext()).inflate(R.layout.table_live_status_recyclerview_design,parent,false);
        return new table_live_status.myviewholder10( view4);
    }

    class myviewholder10 extends RecyclerView.ViewHolder {
        TextView table_live_tvv;
        ImageView table_live_imgg;
        public myviewholder10(@NonNull View itemView) {
            super(itemView);
            table_live_tvv=(TextView) itemView.findViewById(R.id.table_live_tv);
            table_live_imgg=(ImageView) itemView.findViewById(R.id.table_live_img);
        }
    }
}
