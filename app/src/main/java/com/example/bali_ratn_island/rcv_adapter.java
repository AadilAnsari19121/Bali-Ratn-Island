package com.example.bali_ratn_island;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class rcv_adapter extends FirebaseRecyclerAdapter<rcv_model,rcv_adapter.myviewholder001>{
    public rcv_adapter(@NonNull FirebaseRecyclerOptions<rcv_model> options) {
        super(options);
    }


    protected void onBindViewHolder(@NonNull final myviewholder001 holder001, @SuppressLint("RecyclerView") final int position, @NonNull final rcv_model model) {
        holder001.cat_name.setText(model.getCat_name());
        Glide.with(holder001.cat_img_url.getContext()).load(model.getCat_img_url()).into(holder001.cat_img_url);

        holder001.cat_name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), holder001.cat_name);
                //inflating menu from xml resource
                popup.inflate(R.menu.cat_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.edit_cat_menu) {
                            //edit code
                            final DialogPlus dialogPlus=DialogPlus.newDialog(holder001.cat_img_url.getContext())
                                            .setContentHolder(new ViewHolder(R.layout.admin_edit_cat))
                                            .setExpanded(true,700)
                                            .setGravity(Gravity.CENTER)
                                            .create();

                            View myview=dialogPlus.getHolderView();
                            EditText edit_cat_url=myview.findViewById(R.id.edit_img_url);
                            EditText edit_cat_name=myview.findViewById(R.id.edit_cat_name);
                            CircleImageView edit_cat_image=myview.findViewById(R.id.edit_cat_image);
                            Button edit_cat_btn=myview.findViewById(R.id.edit_cat_add_btn);


                            edit_cat_url.setText(model.getCat_img_url());
                            edit_cat_name.setText(model.getCat_name());
                            edit_cat_url.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                    }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    String x=edit_cat_url.getText().toString();
                                    Glide.with(holder001.cat_img_url.getContext()).load(x).into(edit_cat_image);

                                }

                                @Override
                                public void afterTextChanged(Editable editable) {

                                }
                            });

                            dialogPlus.show();

                            edit_cat_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Map<String ,Object> map=new HashMap<>();
                                    map.put("cat_img_url",edit_cat_url.getText().toString());
                                    map.put("cat_name",edit_cat_name.getText().toString());
                                    FirebaseDatabase.getInstance().getReference().child("category_table").child(getRef(position).getKey())
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

                                    FirebaseDatabase.getInstance().getReference().child("category_for_menu").child(getRef(position).getKey()).updateChildren(map);
                                }
                            });
                        }

                        else if (item.getItemId()==R.id.delete_cat_menu)
                        {
                            //delete code
                            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                            builder.setTitle("Delete..")
                                    .setMessage("Do  you  wan't delete "+ model.cat_name+" category...?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    FirebaseDatabase.getInstance().getReference().child("category_table").child(getRef(position).getKey()).removeValue();
                                    FirebaseDatabase.getInstance().getReference().child("category_for_menu").child(getRef(position).getKey()).removeValue();

                                    Toast.makeText(view.getContext(), model.cat_name+" Deleted", Toast.LENGTH_SHORT).show();
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
    public myviewholder001 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view16 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_recyclerview_design,parent, false);
        return new rcv_adapter.myviewholder001(view16);
    }

    class myviewholder001 extends RecyclerView.ViewHolder {
        CircleImageView cat_img_url;
        TextView cat_name;
        //ImageButton menu_btn;

        public myviewholder001(@NonNull View itemView) {
            super(itemView);
            cat_img_url = (CircleImageView) itemView.findViewById(R.id.circleImageView_for_rcv_design);
            cat_name = (TextView) itemView.findViewById(R.id.textView_for_rcv_design);
            //menu_btn = (ImageButton) itemView.findViewById(R.id.menu_bt);

        }


    }



}
