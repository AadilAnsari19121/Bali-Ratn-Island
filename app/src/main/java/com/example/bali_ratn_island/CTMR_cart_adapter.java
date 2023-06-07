package com.example.bali_ratn_island;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CTMR_cart_adapter extends FirebaseRecyclerAdapter<CTMR_cart_model,CTMR_cart_adapter.myviewholder17>{

    int total_cart_amount=0;
    //ArrayList<CTMR_cart_model> datlist;
    DatabaseReference db= FirebaseDatabase.getInstance().getReference("cart");

    public CTMR_cart_adapter(@NonNull FirebaseRecyclerOptions<CTMR_cart_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder17 holder17, @SuppressLint("RecyclerView") final int position, @NonNull CTMR_cart_model model) {
        SharedPreferences sharedPreferences = holder17.name.getContext().getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
        String Table_number = sharedPreferences.getString("table_number_ctmr","");

        holder17.name.setText(model.getItem_name());
        holder17.pr.setText(model.getItem_price());
        holder17.qn.setText(model.getItem_quantity());
        holder17.tpr.setText(String.valueOf(model.getItem_total_price()));
        holder17.time.setText(model.getCr_time());


            int oneproduct=((Integer.valueOf(model.getItem_price()))) * Integer.valueOf(model.getItem_quantity());
            total_cart_amount=total_cart_amount+oneproduct;
//        total_cart_amount=total_cart_amount+datlist.get(position).getItem_total_price();
        Intent intent=new Intent("MyTotalAmount");
        intent.putExtra("Total_amount",total_cart_amount);
        intent.putExtra("name_item",model.getItem_name());
        LocalBroadcastManager.getInstance(holder17.tpr.getContext()).sendBroadcast(intent);




        holder17.edit_cart.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                           .setContentHolder(new ViewHolder(R.layout.add_to_cart_menu_item_popup))
                           .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                           .setGravity(Gravity.CENTER)
                           .create();


                   View myview=dialogPlus.getHolderView();
                   TextView popup_menu_name=myview.findViewById(R.id.popup_menu_name);
                   TextView popup_menu_price=myview.findViewById(R.id.popup_menu_price);
                   EditText popup_menu_spnr=myview.findViewById(R.id.popup_menu_qun);
                   Button popup_edit_to_cart_btn=myview.findViewById(R.id.popup_menu_cart_btn);

                   popup_menu_name.setText(model.getItem_name());
                   popup_menu_price.setText(model.getItem_price());
                   popup_menu_spnr.setText(model.getItem_quantity());
                   popup_edit_to_cart_btn.setText("update cart");

                   popup_edit_to_cart_btn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           String a=popup_menu_spnr.getText().toString();
                           int b=Integer.parseInt(a);

                           if (b==0)
                           {
                               Toast.makeText(view.getContext(), "Enter The Quantity Between 0 to 20", Toast.LENGTH_SHORT).show();
                           }
                           else if (b<21)
                           {
                               String item_name;
                               String item_price;
                               String item_quantity;
                               String cr_time;
                               String cr_date;
                               String tb_num;
                               String item_name_X_item_quantity;
                               String item_price_X_item_quantity;
                               String item_price_X_item_quantity_eq_item_total_pr;
                               int item_total_price;

                               //String x,y,z;
                               item_name=popup_menu_name.getText().toString();
                               item_price=popup_menu_price.getText().toString();
                               item_quantity=popup_menu_spnr.getText().toString();

                               item_name_X_item_quantity=item_name +" X "+item_quantity;



                               //String crtime,crdt;
                               int pr=Integer.parseInt(popup_menu_price.getText().toString());
                               int qn=Integer.parseInt(popup_menu_spnr.getText().toString());


                               item_total_price=pr*qn;

                               item_price_X_item_quantity= item_price +" X "+ item_quantity;
                               item_price_X_item_quantity_eq_item_total_pr=item_price+" X "+ item_quantity+" = "+ String.valueOf(item_total_price);

                               Calendar calender=Calendar.getInstance();

                               SimpleDateFormat crd=new SimpleDateFormat("MM dd, yyyy");
                               SimpleDateFormat crt=new SimpleDateFormat("HH:mm:ss a");

                               cr_time=crt.format(calender.getTime());
                               cr_date=crd.format(calender.getTime());
                               tb_num=Table_number;

//                            Map<String,Object> cartmap=new HashMap<>();

//                            cartmap.put("item name",item_name);
//                            cartmap.put("item price",item_price);
//                            cartmap.put("item quantity",item_quantity);
//                            cartmap.put("total price",item_total_price);
//                            cartmap.put("Time",cr_time);
//                            cartmap.put("Date",cr_date);

                               CTMR_cart_model obj=new CTMR_cart_model(item_name,item_price,item_quantity,cr_time,cr_date,tb_num,item_name_X_item_quantity,item_price_X_item_quantity,item_price_X_item_quantity_eq_item_total_pr,item_total_price);

                               FirebaseDatabase database=FirebaseDatabase.getInstance();
                               DatabaseReference root=database.getReference("cart");
                               root.child(Table_number).child(item_name).setValue(obj)
                                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void unused) {
                                               Toast.makeText(view.getContext(), "cart updated", Toast.LENGTH_SHORT).show();
                                               dialogPlus.dismiss();
                                           }
                                       })
                                       .addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull Exception e) {
                                               Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                                           }
                                       });

                               DatabaseReference root2=database.getReference("order_list_2");
                               DatabaseReference root3=database.getReference("order_list_3");
                               Map<String ,Object> map2=new HashMap<>();
                               //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                               map2.put("item_name_X_item_quantity",item_name_X_item_quantity);
                               root2.child(Table_number).push().setValue(map2);

                               Map<String ,Object> map3=new HashMap<>();
                               //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                               map3.put("item_name_X_item_quantity",item_name_X_item_quantity);
                               root2.child(Table_number).push().setValue(map3);
                           }
                           else if (b>20)
                           {
                               Toast.makeText(view.getContext(), "Enter The Quantity Between 0 to 20", Toast.LENGTH_SHORT).show();
                           }


                       }

                   });

                   dialogPlus.show();

               }
           });

           holder17.remove_cart.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                   builder.setTitle("Remove..")
                           .setMessage("Do  you  wan't remove "+ model.getItem_name()+" item from cart...?");

                   builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                           FirebaseDatabase.getInstance().getReference().child("cart").child(Table_number).child(getRef(position).getKey()).removeValue();
                           Toast.makeText(view.getContext(), model.getItem_name()+"  Removed", Toast.LENGTH_SHORT).show();

                           FirebaseDatabase.getInstance().getReference().child("order_list_2").child(Table_number).child(getRef(position).getKey()).removeValue();
                           FirebaseDatabase.getInstance().getReference().child("order_list_3").child(Table_number).child(getRef(position).getKey()).removeValue();

                       }
                   });

                   builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                       }
                   });
                   builder.show();
               }
           });
    }

    @NonNull
    @Override
    public myviewholder17 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ctmr_cart_des,parent,false);
        return new myviewholder17(view);
    }

    class myviewholder17 extends RecyclerView.ViewHolder {
        TextView name,pr,qn,tpr,time,dt;
        TextView remove_cart,edit_cart;
        public myviewholder17(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_name);
            pr=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_price);
            qn=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_quantity);
            tpr=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_total_price);
            time=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_time);
            dt=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_date);

            remove_cart=(TextView) itemView.findViewById(R.id.des_remove_cart);
            edit_cart=(TextView) itemView.findViewById(R.id.des_edit_cart);
        }
    }
}
