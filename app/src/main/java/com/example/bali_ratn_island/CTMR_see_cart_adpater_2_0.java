package com.example.bali_ratn_island;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CTMR_see_cart_adpater_2_0 extends RecyclerView.Adapter<CTMR_see_cart_adpater_2_0.Viewholder> {

    ArrayList<CTMR_cart_model> datlist;
    int total_cart_amount;
    FirebaseAuth auth;
    FirebaseFirestore db;
    public CTMR_see_cart_adpater_2_0(ArrayList<CTMR_cart_model> datlist) {
        this.datlist = datlist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ctmr_cart_des,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.name.setText(datlist.get(position).getItem_name());
        holder.pr.setText(datlist.get(position).getItem_price());
        holder.qn.setText(datlist.get(position).getItem_quantity());
        holder.tpr.setText(String.valueOf(datlist.get(position).getItem_total_price()));
        holder.time.setText(datlist.get(position).getCr_time());
        holder.dt.setText(datlist.get(position).getCr_date());


//        total_cart_amount=total_cart_amount+datlist.get(position).getItem_total_price();
//        Intent intent=new Intent("MyTotalAmount");
//        intent.putExtra("Total_amount",total_cart_amount);
//
//        LocalBroadcastManager.getInstance(holder.tpr.getContext()).sendBroadcast(intent);


        db= FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(view.getContext());
                ab.setTitle("Alert...!");
                ab.setMessage("do you remove this cart item");
                ab.setCancelable(false);
                ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {





//                                String x=holder.name.getText().toString();
//                                db.collection("users")
//                                        .whereEqualTo("item_name",x).get()
//                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                        WriteBatch b=FirebaseFirestore.getInstance().batch();
//                                        List<DocumentSnapshot> s=queryDocumentSnapshots.getDocuments();
//                                        for (DocumentSnapshot snapshot:s)
//                                        {
//                                            b.delete(snapshot.getReference());
//                                        }
//                                        b.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                Toast.makeText(view.getContext(), "cart removed", Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                                    }
//                                });
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                ab.show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return datlist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name,pr,qn,tpr,time,dt;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_name);
            pr=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_price);
            qn=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_quantity);
            tpr=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_total_price);
            time=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_time);
            dt=(TextView) itemView.findViewById(R.id.CTMR_cart_des_item_date);
        }
    }
}
