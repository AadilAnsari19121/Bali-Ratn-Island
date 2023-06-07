package com.example.bali_ratn_island;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class customer_category_adapter extends FirebaseRecyclerAdapter<rcv_model,customer_category_adapter.myviewholder6>{
    public customer_category_adapter(@NonNull FirebaseRecyclerOptions<rcv_model> options) {
        super(options);
    }


    //ProgressBar pb;
    @Override
    protected void onBindViewHolder(@NonNull final myviewholder6 holder, final int position, @NonNull rcv_model model) {
        holder.cust_cat_name.setText(model.getCat_name());
        Glide.with(holder.cust_cat_img_url.getContext()).load(model.getCat_img_url()).into(holder.cust_cat_img_url);

        holder.cust_cat_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),customer_see_menu_item.class);
                intent.putExtra("cust_cat_name_35",model.getCat_name());
                intent.putExtra("cust_cat_pic_35",model.getCat_img_url());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
                //view.getContext().startActivity(new Intent(view.getContext(),customer_see_menu_item.class));
            }
        });


//        holder.cust_cat_name.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view2) {
////
////                final DialogPlus dialogPlus=DialogPlus.newDialog(view2.getContext())
////                        .setContentHolder(new ViewHolder(R.layout.xyz))
////                        .setExpanded(true, LinearLayout.LayoutParams.MATCH_PARENT)
////                        .setGravity(Gravity.CENTER)
////                        .create();
////
////                View myview=dialogPlus.getHolderView();
////                TextView a=myview.findViewById(R.id.customer_rcv_design_menu_name_xyz);
////                TextView b=myview.findViewById(R.id.customer_rcv_design_menu_price_xyz);
////                Spinner c=myview.findViewById(R.id.customer_spinner_customer_menu_xyz);
//
////                RecyclerView rc=myview.findViewById(R.id.customer_menu_item_rcv_xyz);
////                Button bbbt=myview.findViewById(R.id.customer_btn_cart_xyz);
//
//
////
////                class xyz_adapter extends FirebaseRecyclerAdapter<menu_item_model,xyz_adapter.myviewholder8>
////                {
////
////                    public xyz_adapter(@NonNull FirebaseRecyclerOptions<menu_item_model> options) {
////                        super(options);
////                    }
////
////                    @Override
////                    protected void onBindViewHolder(@NonNull xyz_adapter.myviewholder8 holder5, int position, @NonNull menu_item_model model5) {
////                        holder5.a1.setText(model5.getMenu_item_Name());
////                        holder5.b2.setText(model5.getMenu_item_Price());
////                    }
////
////                    @NonNull
////                    @Override
////                    public xyz_adapter.myviewholder8 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////                        View view8= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_menu_item_recyclerview_design,parent,false);
////                        return new myviewholder8(view8);
////                    }
////
////                    class myviewholder8 extends RecyclerView.ViewHolder {
////                        TextView a1,b2;
////                        Spinner xyz_sp3;
////                        public myviewholder8(@NonNull View itemView3) {
////                            super(itemView3);
////                            a1=(TextView) itemView3.findViewById(R.id.customer_rcv_design_menu_name);
////                            b2=(TextView) itemView3.findViewById(R.id.customer_rcv_design_menu_price);
////                            xyz_sp3=(Spinner) itemView3.findViewById(R.id.customer_spinner_customer_menu);
////                        }
////                    }
//////                    public xyz_adapter(@NonNull FirebaseRecyclerOptions<menu_item_model> options) {
//////                        super(options);
//////                    }
//////
//////                    @Override
//////                    protected void onBindViewHolder(@NonNull xyz_adapter.myviewholder7 holder2, int position, @NonNull menu_item_model model2) {
//////                        holder2.a.setText(model2.getMenu_item_Name());
//////                        holder2.b.setText(model2.getMenu_item_Price());
//////                    }
//////
//////                    @NonNull
//////                    @Override
//////                    public xyz_adapter.myviewholder7 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//////                        View view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_menu_item_recyclerview_design,parent,false);
//////                        return new myviewholder7(view2);
//////                    }
////
////
////                }
//
////                xyz_adapter xyz_adapter2;
////                //rc.setAdapter(xyz_adapter2);
////                rc.setLayoutManager(new LinearLayoutManager(view2.getContext()));
////
////                FirebaseRecyclerOptions<menu_item_model> options8 =
////                        new FirebaseRecyclerOptions.Builder<menu_item_model>()
////                                .setQuery(FirebaseDatabase.getInstance().getReference().child("menu_items"), menu_item_model.class)
////                                .build();
////
////                xyz_adapter2 = new xyz_adapter(options8);
////                rc.setAdapter(xyz_adapter2);
//
////                dialogPlus.show();
//
//
//
//
//
////                  Intent intent=new Intent(view.getContext(),customer_see_menu_addapter.class);
////                intent.putExtra("cust_cat_name_35",model.getCat_name());
////                intent.putExtra("cust_cat_pic_35",model.getCat_img_url());
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                view.getContext().startActivity(intent);
//            }
//
//
//
//        });


    }

    @NonNull
    @Override
    public myviewholder6 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customercat_recyclerview_design,parent,false);
        return new myviewholder6(view);
    }

    class myviewholder6 extends RecyclerView.ViewHolder {
        TextView cust_cat_name;
        CircleImageView cust_cat_img_url;
        public myviewholder6(@NonNull View itemView) {
            super(itemView);
           // pb=itemView.findViewById(R.id.progressBar3);
            cust_cat_img_url=(CircleImageView) itemView.findViewById(R.id.customer_circleImageView_for_rcv_design);
            cust_cat_name=(TextView) itemView.findViewById(R.id.customer_textView_for_rcv_design);
        }
    }



    //    class myviewholder7 extends RecyclerView.ViewHolder{
//        TextView a,b;
//        Spinner xyz_sp;
//
//        public myviewholder7(@NonNull View itemView) {
//            super(itemView);
//            a=(TextView) itemView.findViewById(R.id.customer_rcv_design_menu_name);
//            b=(TextView) itemView.findViewById(R.id.customer_rcv_design_menu_price);
//            xyz_sp=(Spinner) itemView.findViewById(R.id.customer_spinner_customer_menu);
//        }
//    }
}
