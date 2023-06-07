package com.example.bali_ratn_island;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class emp_adapter extends FirebaseRecyclerAdapter<emp_model,emp_adapter.myviewholder2>{
    private Context context;
    public emp_adapter(@NonNull FirebaseRecyclerOptions<emp_model> options2) {
        super(options2);

    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder2 holder, @SuppressLint("RecyclerView") final int position, @NonNull emp_model model) {

        holder.emp_id.setText(model.getStaff_id());
        holder.emp_name.setText(model.getFnamen());
        Glide.with(holder.emp_face_img_rcv.getContext()).load(model.getPimg()).into(holder.emp_face_img_rcv);

        holder.emp_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),emp_full_info.class);
                intent.putExtra("em_fnm",model.getFnamen());
                intent.putExtra("em_lnm",model.getLname());
                intent.putExtra("em_age",model.getAge());
                intent.putExtra("em_pn",model.getPhone());
                intent.putExtra("em_ml",model.getMail());
                intent.putExtra("em_id",model.getStaff_id());
                intent.putExtra("em_add",model.getAddress());
                intent.putExtra("em_pin",model.getPincode());
                intent.putExtra("em_ed",model.getEdu_q());
                intent.putExtra("em_stat",model.getState());
                intent.putExtra("em_poz",model.getPoz());
                intent.putExtra("em_face",model.getPimg());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);

            }
        });

        holder.emp_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),emp_full_info.class);
                intent.putExtra("em_fnm",model.getFnamen());
                intent.putExtra("em_lnm",model.getLname());
                intent.putExtra("em_age",model.getAge());
                intent.putExtra("em_pn",model.getPhone());
                intent.putExtra("em_ml",model.getMail());
                intent.putExtra("em_id",model.getStaff_id());
                intent.putExtra("em_add",model.getAddress());
                intent.putExtra("em_pin",model.getPincode());
                intent.putExtra("em_ed",model.getEdu_q());
                intent.putExtra("em_stat",model.getState());
                intent.putExtra("em_poz",model.getPoz());
                intent.putExtra("em_face",model.getPimg());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);

            }
        });

        holder.emp_face_img_rcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),emp_full_info.class);
                intent.putExtra("em_fnm",model.getFnamen());
                intent.putExtra("em_lnm",model.getLname());
                intent.putExtra("em_age",model.getAge());
                intent.putExtra("em_pn",model.getPhone());
                intent.putExtra("em_ml",model.getMail());
                intent.putExtra("em_id",model.getStaff_id());
                intent.putExtra("em_add",model.getAddress());
                intent.putExtra("em_pin",model.getPincode());
                intent.putExtra("em_ed",model.getEdu_q());
                intent.putExtra("em_stat",model.getState());
                intent.putExtra("em_poz",model.getPoz());
                intent.putExtra("em_face",model.getPimg());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);

            }
        });


        holder.menu_btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), holder.menu_btn3);
                //inflating menu from xml resource
                popup.inflate(R.menu.cat_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.edit_cat_menu) {
                            //edit code
                            final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                                    .setContentHolder(new ViewHolder(R.layout.admin_edit_staff_info))
                                    .setExpanded(true, LinearLayout.LayoutParams.WRAP_CONTENT)
                                    .setGravity(Gravity.CENTER)
                                    .create();

                            View myview=dialogPlus.getHolderView();
                            TextInputLayout fnm5=myview.findViewById(R.id.edit_st_info_first_name);
                            TextInputLayout lnm5=myview.findViewById(R.id.edit_st_info_last_name);
                            TextInputLayout age5=myview.findViewById(R.id.edit_st_info_age);
                            TextInputLayout phone5=myview.findViewById(R.id.edit_st_info_phone_number);
                            TextInputLayout mail5=myview.findViewById(R.id.edit_st_info_email_address);
                            TextInputLayout address5=myview.findViewById(R.id.edit_st_info_home_address);
                            TextInputLayout pincode5=myview.findViewById(R.id.edit_st_info_pin_code);
                            TextInputLayout state5=myview.findViewById(R.id.edit_st_info_state);
                            TextInputLayout edu_q5=myview.findViewById(R.id.edit_st_info_edu_qualification);

                            TextView stid5=myview.findViewById(R.id.edit_st_info_st_id);

                            EditText pimg_url5=myview.findViewById(R.id.edit_st_info_img_url);
                            CircleImageView face5=myview.findViewById(R.id.edit_st_info_face);
                            Button edit_emp_btn=myview.findViewById(R.id.edit_empl_btn);


                            pimg_url5.setText(model.getPimg());
                            stid5.setText(model.getStaff_id());

                            fnm5.getEditText().setText(model.getFnamen());
                            lnm5.getEditText().setText(model.getLname());
                            age5.getEditText().setText(model.getAge());
                            phone5.getEditText().setText(model.getPhone());
                            mail5.getEditText().setText(model.getMail());
                            address5.getEditText().setText(model.getAddress());
                            pincode5.getEditText().setText(model.getPincode());
                            edu_q5.getEditText().setText(model.getEdu_q());
                            state5.getEditText().setText(model.getState());

                            pimg_url5.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                    String x=pimg_url5.getText().toString();
                                    Glide.with(view.getContext()).load(x).into(face5);
                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    String x=pimg_url5.getText().toString();
                                    Glide.with(view.getContext()).load(x).into(face5);

                                }

                                @Override
                                public void afterTextChanged(Editable editable) {

                                }
                            });

                            dialogPlus.show();


                            edit_emp_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Map<String ,Object> map=new HashMap<>();
                                    map.put("fnamen",fnm5.getEditText().getText().toString());
                                    map.put("lname",lnm5.getEditText().getText().toString());
                                    map.put("age",age5.getEditText().getText().toString());
                                    map.put("phone",phone5.getEditText().getText().toString());
                                    map.put("mail",mail5.getEditText().getText().toString());
                                    map.put("address",address5.getEditText().getText().toString());
                                    map.put("pincode",pincode5.getEditText().getText().toString());
                                    map.put("edu_q",edu_q5.getEditText().getText().toString());
                                    map.put("state",state5.getEditText().getText().toString());
                                    map.put("staff_id",stid5.getText().toString());
                                    map.put("pimg",pimg_url5.getText().toString());

                                    FirebaseDatabase.getInstance().getReference().child("staff_of_bali").child(getRef(position).getKey())
                                            .updateChildren(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>()
                                            {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(view.getContext(), "staff profile update", Toast.LENGTH_SHORT).show();
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
                                }
                            });
                        }

                        else if (item.getItemId()==R.id.delete_cat_menu)
                        {
                            //delete code
                            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                            builder.setTitle("Delete..")
                                    .setMessage("Do  you  wan't delete "+ model.getFnamen()+" "+model.getLname()+" Employee profile...?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    FirebaseDatabase.getInstance().getReference().child("staff_of_bali").child(getRef(position).getKey()).removeValue();
                                    //FirebaseDatabase.getInstance().getReference().child("category_for_menu").child(getRef(position).getKey()).removeValue();

                                    Toast.makeText(view.getContext(), model.getFnamen()+" Deleted", Toast.LENGTH_SHORT).show();
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
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_recyclerview_design,parent,false);
        return new myviewholder2(view);
    }

    class myviewholder2 extends RecyclerView.ViewHolder {
        CircleImageView emp_face_img_rcv;
        TextView emp_name,emp_id;
        ImageButton menu_btn3;

        public myviewholder2(@NonNull View itemView) {
            super(itemView);
            emp_face_img_rcv = (CircleImageView) itemView.findViewById(R.id.face_emp_rcv);
            emp_id = (TextView) itemView.findViewById(R.id.staff_id_emp_rcv);
            emp_name = (TextView) itemView.findViewById(R.id.staff_name_emp_rcv);
            menu_btn3 = (ImageButton) itemView.findViewById(R.id.menu_bt_emp);

        }


    }
}
