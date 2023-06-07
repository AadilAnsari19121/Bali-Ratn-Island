package com.example.bali_ratn_island;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class menu_adapter extends FirebaseRecyclerAdapter<menu_item_model,menu_adapter.myviewholder>{
    public menu_adapter(@NonNull FirebaseRecyclerOptions<menu_item_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull menu_item_model model) {
        holder.menu_id_adp.setText(model.getMenu_item_Id());
        holder.menu_name_adp.setText(model.getMenu_item_Name());
        holder.menu_price_adp.setText(model.getMenu_item_Price());
        holder.menu_cat_adp.setText(model.getMenu_item_Cat());

        holder.menu_btn.setOnClickListener(new View.OnClickListener() {
            String item_edit_cat;
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), holder.menu_btn);
                //inflating menu from xml resource
                popup.inflate(R.menu.cat_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.edit_cat_menu) {
                            //edit code
                            final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                                    .setContentHolder(new ViewHolder(R.layout.admin_edit_menu))
                                    .setExpanded(true, LinearLayout.LayoutParams.MATCH_PARENT)
                                    .setGravity(Gravity.CENTER)
                                    .create();
                            View myview=dialogPlus.getHolderView();

                            TextInputLayout edit_menu_name=myview.findViewById(R.id.menu_edit_name);
                            TextInputLayout edit_menu_price=myview.findViewById(R.id.menu_edit_price);
                            TextInputLayout edit_menu_category=myview.findViewById(R.id._editxyz);
                            AutoCompleteTextView edit_cat_menu=myview.findViewById(R.id.cat_menu_edit);
                            TextView edit_menu_id=myview.findViewById(R.id.menu_edit_id);
                            //String item_edit_cat;

                            ArrayList<String> arlist;
                            ArrayAdapter<String> dataAdapter;
                            ValueEventListener listener;


                            arlist = new ArrayList<String>();
                            DatabaseReference ref=FirebaseDatabase.getInstance().getReference("category_table");


                            Button edit_menu_btn=myview.findViewById(R.id.add_menu_edit);


                            edit_menu_id.setText(model.getMenu_item_Id());
                            edit_menu_name.getEditText().setText(model.getMenu_item_Name());
                            edit_menu_price.getEditText().setText(model.getMenu_item_Price());
                            edit_menu_category.getEditText().setText(model.getMenu_item_Cat());


                            dialogPlus.show();


                            dataAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.menu_sppnr, arlist);
                            edit_cat_menu.setAdapter(dataAdapter);
                            listener = ref.addValueEventListener(new ValueEventListener() {
                                @SuppressLint("SuspiciousIndentation")
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot mydata : snapshot.getChildren())
                                        arlist.add(mydata.child("cat_name").getValue().toString());
                                    dataAdapter.notifyDataSetChanged();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }

                            });

                            edit_cat_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    item_edit_cat =adapterView.getItemAtPosition(i).toString();
                                    //Toast.makeText(admin_add_employee.this, "state is: "+item_state+i, Toast.LENGTH_SHORT).show();
                                }
                            });

                            edit_menu_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Map<String ,Object> map=new HashMap<>();
                                    map.put("menu_item_Id",edit_menu_id.getText().toString());
                                    map.put("menu_item_Name",edit_menu_name.getEditText().getText().toString());
                                    map.put("menu_item_Price",edit_menu_price.getEditText().getText().toString());
                                    map.put("menu_item_Cat",item_edit_cat.toString());
                                    FirebaseDatabase.getInstance().getReference().child("menu_items").child(getRef(position).getKey())
                                            .updateChildren(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>()
                                            {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(view.getContext(), "category update", Toast.LENGTH_SHORT).show();
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

                                    FirebaseDatabase.getInstance().getReference().child("category_for_menu").child(getRef(position).getKey())
                                            .updateChildren(map);
                                }
                            });
                        }

                        else if (menuItem.getItemId()==R.id.delete_cat_menu)
                        {
                            //delete code
                            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                            builder.setTitle("Delete..")
                                    .setMessage("Do  you  wan't delete "+ model.menu_item_Name+" item...?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    FirebaseDatabase.getInstance().getReference().child("menu_items").child(getRef(position).getKey()).removeValue();
                                    Toast.makeText(view.getContext(), model.menu_item_Name+" Deleted", Toast.LENGTH_SHORT).show();
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
                popup.show();
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_recyclerview_design, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView menu_id_adp;
        TextView menu_name_adp;
        TextView menu_price_adp;
        TextView menu_cat_adp;

        ImageButton menu_btn;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            menu_id_adp=itemView.findViewById(R.id.rcv_design_menu_id);
            menu_name_adp=itemView.findViewById(R.id.rcv_design_menu_name);
            menu_price_adp=itemView.findViewById(R.id.rcv_design_menu_price);
            menu_cat_adp=itemView.findViewById(R.id.rcv_design_menu_cat);

            menu_btn=itemView.findViewById(R.id.menu_bt_menu);
        }
    }
}
