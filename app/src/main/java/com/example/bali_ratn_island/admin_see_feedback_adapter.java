package com.example.bali_ratn_island;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class admin_see_feedback_adapter extends FirebaseRecyclerAdapter<feedback_model,admin_see_feedback_adapter.myviewholder31> {
    public admin_see_feedback_adapter(@NonNull FirebaseRecyclerOptions<feedback_model> options) {
        super(options);
    }


    protected void onBindViewHolder(@NonNull myviewholder31 holder, int position, @NonNull feedback_model model) {
        holder.tbnm.setText(model.getTb_num());
        holder.name.setText(model.getCustomer_name());
        holder.time.setText(model.getCr_time());
        holder.date.setText(model.getCr_date());

        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.see_feedback_details_popup))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();

                dialogPlus.show();

                View myview=dialogPlus.getHolderView();
                TextView x=myview.findViewById(R.id.feedback_popup_tv);
                x.setText("NAME: "+model.getCustomer_name()+"\n\n\n"+
                        "PHONE: "+model.getCustomer_phone()+"\n\n\n"+
                        "MAIL: "+model.getCustomer_mail()+"\n\n\n"+
                        "TIME: "+model.getCr_time()+"\n\n\n"+
                        "DATE: "+model.getCr_date()+"\n\n\n"+
                        "FOOD RATING: "+model.getCustomer_food_rating()+"\n\n\n"+
                        "SERVICE RATING: "+model.getCustomer_service_rating()+"\n\n\n"+
                        "MASSAGE: "+model.getCustomer_longmsg());
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.see_feedback_details_popup))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();

                dialogPlus.show();

                View myview=dialogPlus.getHolderView();
                TextView x=myview.findViewById(R.id.feedback_popup_tv);
                x.setText("NAME: "+model.getCustomer_name()+"\n\n\n"+
                        "PHONE: "+model.getCustomer_phone()+"\n\n\n"+
                        "MAIL: "+model.getCustomer_mail()+"\n\n\n"+
                        "TIME: "+model.getCr_time()+"\n\n\n"+
                        "DATE: "+model.getCr_date()+"\n\n\n"+
                        "FOOD RATING: "+model.getCustomer_food_rating()+"\n\n\n"+
                        "SERVICE RATING: "+model.getCustomer_service_rating()+"\n\n\n"+
                        "MASSAGE: "+model.getCustomer_longmsg());
            }
        });
        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.see_feedback_details_popup))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();


                dialogPlus.show();

                View myview=dialogPlus.getHolderView();
                TextView x=myview.findViewById(R.id.feedback_popup_tv);
                x.setText("NAME: "+model.getCustomer_name()+"\n\n\n"+
                        "PHONE: "+model.getCustomer_phone()+"\n\n\n"+
                        "MAIL: "+model.getCustomer_mail()+"\n\n\n"+
                        "TIME: "+model.getCr_time()+"\n\n\n"+
                        "DATE: "+model.getCr_date()+"\n\n\n"+
                        "FOOD RATING: "+model.getCustomer_food_rating()+"\n\n\n"+
                        "SERVICE RATING: "+model.getCustomer_service_rating()+"\n\n\n"+
                        "MASSAGE: "+model.getCustomer_longmsg());
            }
        });
        holder.tbnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.see_feedback_details_popup))
                        .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                        .setGravity(Gravity.CENTER)
                        .create();

                dialogPlus.show();

                View myview=dialogPlus.getHolderView();
                TextView x=myview.findViewById(R.id.feedback_popup_tv);
                x.setText("NAME :  "+model.getCustomer_name()+"\n\n\n"+
                        "PHONE :  "+model.getCustomer_phone()+"\n\n\n"+
                        "MAIL :  "+model.getCustomer_mail()+"\n\n\n"+
                        "TIME  : "+model.getCr_time()+"\n\n\n"+
                        "DATE :  "+model.getCr_date()+"\n\n\n"+
                        "FOOD RATING :  "+model.getCustomer_food_rating()+"\n\n\n"+
                        "SERVICE RATING :  "+model.getCustomer_service_rating()+"\n\n\n"+
                        "MASSAGE :  "+model.getCustomer_longmsg());
            }
        });
    }

    @NonNull

    @Override
    public myviewholder31 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view27= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_see_feedback_list_des,parent,false);
        return new admin_see_feedback_adapter.myviewholder31(view27);
    }

    public class myviewholder31 extends RecyclerView.ViewHolder {
        TextView time,date,name,tbnm;
        public myviewholder31(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.admin_feedback_des_time);
            date=itemView.findViewById(R.id.admin_feedback_des_date);
            name=itemView.findViewById(R.id.admin_feedback_des_name);
            tbnm=itemView.findViewById(R.id.admin_feedback_des_tbnm);
        }
    }
}
