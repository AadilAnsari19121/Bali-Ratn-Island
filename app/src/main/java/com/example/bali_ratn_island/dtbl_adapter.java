package com.example.bali_ratn_island;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class dtbl_adapter extends FirebaseRecyclerAdapter<dtbl_model,dtbl_adapter.my_viewholder>{

    public dtbl_adapter(@NonNull FirebaseRecyclerOptions<dtbl_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final my_viewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final dtbl_model model) {
        holder.dining_table__id.setText(model.getTable_id());
        holder.dining_table__cap.setText(model.getTable_capacity());

        holder.menu_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), holder.menu_btn);
                //inflating menu from xml resource
                popup.inflate(R.menu.cat_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.edit_cat_menu) {
                            //edit code
                            final DialogPlus dialogPlus=DialogPlus.newDialog(holder.dining_table__id.getContext())
                                    .setContentHolder(new ViewHolder(R.layout.admin_edit_dining_table))
                                    .setExpanded(true,1200)
                                    .setGravity(Gravity.CENTER)
                                    .create();



                            View myview=dialogPlus.getHolderView();
                            TextView edit_dining_table_id=myview.findViewById(R.id.edit_dining_id);
                            EditText edit_dining_table_capacity=myview.findViewById(R.id.edit_dining_table_capacity);
                            //CircleImageView edit_cat_image=myview.findViewById(R.id.edit_cat_image);
                            Button edit_dining_table_add_btn=myview.findViewById(R.id.edit_dining_table_add_btn);


                            edit_dining_table_id.setText("Table Id : "+model.getTable_id());
                            edit_dining_table_capacity.setText(model.getTable_capacity());
                            dialogPlus.show();

                            edit_dining_table_add_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Map<String ,Object> map=new HashMap<>();
                                    map.put("table_id",edit_dining_table_id.getText().toString());
                                    map.put("table_capacity",edit_dining_table_capacity.getText().toString());
                                    FirebaseDatabase.getInstance().getReference().child("dining_table").child(getRef(position).getKey())
                                            .updateChildren(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>()
                                            {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(view.getContext(), "update", Toast.LENGTH_SHORT).show();
                                                    dialogPlus.dismiss();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener()
                                            {
                                                @Override
                                                public void onFailure(@NonNull Exception e)
                                                {
                                                    dialogPlus.dismiss();
                                                }
                                            });

//                                    FirebaseDatabase.getInstance().getReference().child("category_for_menu").child(getRef(position).getKey())
//                                            .updateChildren(map);
                                }
                            });
                        }

                        else if (item.getItemId()==R.id.delete_cat_menu)
                        {
                            //delete code
                            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                            builder.setTitle("Delete..")
                                    .setMessage("Do  you  wan't delete table number "+ model.getTable_id()+" ...?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    FirebaseDatabase.getInstance().getReference().child("dining_table").child(getRef(position).getKey()).removeValue();
                                    //FirebaseDatabase.getInstance().getReference().child("category_for_menu").child(getRef(position).getKey()).removeValue();
                                    Toast.makeText(view.getContext(), model.getTable_id()+" Deleted", Toast.LENGTH_SHORT).show();
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                        }

                        else {
                            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });

    }

    @NonNull
    @Override
    public my_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.dining_table_recyclerview_design,parent,false);
        return new my_viewholder(view2);
    }

    class my_viewholder extends RecyclerView.ViewHolder
    {

        TextView dining_table__id,dining_table__cap;
        ImageButton menu_btn;
        public my_viewholder(@NonNull View itemView) {
            super(itemView);
            dining_table__id=(TextView) itemView.findViewById(R.id.dining_table_id);
            dining_table__cap=(TextView) itemView.findViewById(R.id.dining_table_capcity);
            menu_btn = (ImageButton) itemView.findViewById(R.id.dining_table_menu_button);
        }
    }
}
