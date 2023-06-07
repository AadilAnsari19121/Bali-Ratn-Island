package com.example.bali_ratn_island;

import static android.content.Context.MODE_PRIVATE;

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
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class xyz_adapter extends FirebaseRecyclerAdapter<menu_item_model,xyz_adapter.myviewholder8>{

    String [] qun={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    String nm,pr,ct;
    String qunity;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ArrayList<String> order_list;

//    implements AdapterView.OnItemSelectedListener

    public xyz_adapter(@NonNull FirebaseRecyclerOptions<menu_item_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull xyz_adapter.myviewholder8 holder5, int position, @NonNull menu_item_model model5) {
        holder5.a1.setText(model5.getMenu_item_Name());
        holder5.b2.setText(model5.getMenu_item_Price().trim());
        holder5.c3.setText(model5.getMenu_item_Cat());
       // holder5.xyz_sp3.setOnItemSelectedListener(this);

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        FirebaseUser User=FirebaseAuth.getInstance().getCurrentUser();


        //nm=holder5.a1.getText().toString();

        holder5.a1.setOnClickListener(new View.OnClickListener() {
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
                Button popup_add_to_cart_btn=myview.findViewById(R.id.popup_menu_cart_btn);

//                ArrayAdapter<String> sppnr=new ArrayAdapter<String>(view.getContext(), R.layout.menu_sppnr,qun);
//                popup_menu_spnr.setAdapter(sppnr);
//
//                popup_menu_spnr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        qunity =qun[i];
//                    }
//                });


                popup_menu_name.setText(model5.getMenu_item_Name());
                popup_menu_price.setText(model5.getMenu_item_Price().trim());
                popup_menu_spnr.setText("0");



                //qunity=popup_menu_spnr.getSelectedItem().toString();

                popup_add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
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

                            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
                            String Table_number = sharedPreferences.getString("table_number_ctmr","");

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
                            DatabaseReference root2=database.getReference("order_list_2");
                            DatabaseReference root3=database.getReference("order_list_3");

                            root.child(Table_number).child(item_name).setValue(obj)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(view.getContext(), "cart added", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
//                                            order_list.add(item_name_X_item_quantity);
//                                            SharedPreferences sharedPref = view.getContext().getSharedPreferences("order_array", MODE_PRIVATE);
//                                            SharedPreferences.Editor editor = sharedPref.edit();
//                                            editor.putString("order_array_list", String.valueOf(order_list));
//                                            editor.apply();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                            Map<String ,Object> map2=new HashMap<>();
                            //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                            map2.put("item_name_X_item_quantity",item_name_X_item_quantity);
                            map2.put("item_price_X_item_quantity_eq_item_total_pr",item_price_X_item_quantity_eq_item_total_pr);

                            root2.child(Table_number).push().setValue(map2);


                            Map<String ,Object> map3=new HashMap<>();
                            //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                            map3.put("item_name_X_item_quantity",item_name_X_item_quantity);
                            map3.put("item_price_X_item_quantity_eq_item_total_pr",item_price_X_item_quantity_eq_item_total_pr);
                            root3.child(Table_number).push().setValue(map3);


//                            firestore.collection("Cart_item").document(auth.getCurrentUser().getUid())
//                                    .collection("users").add(obj).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<DocumentReference> task) {
//                                            if (task.isSuccessful())
//                                            {
//                                                Toast.makeText(view.getContext(), "cart added" +
//                                                        "", Toast.LENGTH_SHORT).show();
//                                                dialogPlus.dismiss();
//                                            }
//                                            else
//                                            {
//                                                Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });







//                                    addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(view.getContext(), "failed to add cart, please try again", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });

                            //addtocart(x,y,z);

//                            SharedPreferences sharedPref = view.getContext().getSharedPreferences("myKey", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPref.edit();
//                            editor.putString("menuname", x);
//                            editor.putString("menuprice", y);
//                            editor.putString("menuQ", z);
//                            editor.apply();

//                            Intent i=new Intent(view.getContext(),customer_see_cart.class);
//                            i.putExtra("menuname",x);
//                            i.putExtra("menuprice",y);
//                            i.putExtra("menuQ",z);
//                            view.getContext().startActivity(i);
                            //Toast.makeText(view.getContext(), "cart added", Toast.LENGTH_SHORT).show();

                        }
                        else if (b>20)
                        {
                            Toast.makeText(view.getContext(), "Enter The Quantity Between 0 to 20", Toast.LENGTH_SHORT).show();
                        }


                    }

//                    private void addtocart(String x, String y, String z) {
//
//
//                    }
                });

                dialogPlus.show();
            }

        });
        holder5.b2.setOnClickListener(new View.OnClickListener() {
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
                Button popup_add_to_cart_btn=myview.findViewById(R.id.popup_menu_cart_btn);

//                ArrayAdapter<String> sppnr=new ArrayAdapter<String>(view.getContext(), R.layout.menu_sppnr,qun);
//                popup_menu_spnr.setAdapter(sppnr);
//
//                popup_menu_spnr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        qunity =qun[i];
//                    }
//                });


                popup_menu_name.setText(model5.getMenu_item_Name());
                popup_menu_price.setText(model5.getMenu_item_Price().trim());
                popup_menu_spnr.setText("0");



                //qunity=popup_menu_spnr.getSelectedItem().toString();

                popup_add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
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

                            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
                            String Table_number = sharedPreferences.getString("table_number_ctmr","");

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
                            DatabaseReference root2=database.getReference("order_list_2");
                            DatabaseReference root3=database.getReference("order_list_3");

                            root.child(Table_number).child(item_name).setValue(obj)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(view.getContext(), "cart added", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
//                                            order_list.add(item_name_X_item_quantity);
//                                            SharedPreferences sharedPref = view.getContext().getSharedPreferences("order_array", MODE_PRIVATE);
//                                            SharedPreferences.Editor editor = sharedPref.edit();
//                                            editor.putString("order_array_list", String.valueOf(order_list));
//                                            editor.apply();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                            Map<String ,Object> map2=new HashMap<>();
                            //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                            map2.put("item_name_X_item_quantity",item_name_X_item_quantity);
                            map2.put("item_price_X_item_quantity_eq_item_total_pr",item_price_X_item_quantity_eq_item_total_pr);

                            root2.child(Table_number).push().setValue(map2);


                            Map<String ,Object> map3=new HashMap<>();
                            //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                            map3.put("item_name_X_item_quantity",item_name_X_item_quantity);
                            map3.put("item_price_X_item_quantity_eq_item_total_pr",item_price_X_item_quantity_eq_item_total_pr);
                            root3.child(Table_number).push().setValue(map3);


//                            firestore.collection("Cart_item").document(auth.getCurrentUser().getUid())
//                                    .collection("users").add(obj).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<DocumentReference> task) {
//                                            if (task.isSuccessful())
//                                            {
//                                                Toast.makeText(view.getContext(), "cart added" +
//                                                        "", Toast.LENGTH_SHORT).show();
//                                                dialogPlus.dismiss();
//                                            }
//                                            else
//                                            {
//                                                Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });







//                                    addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(view.getContext(), "failed to add cart, please try again", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });

                            //addtocart(x,y,z);

//                            SharedPreferences sharedPref = view.getContext().getSharedPreferences("myKey", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPref.edit();
//                            editor.putString("menuname", x);
//                            editor.putString("menuprice", y);
//                            editor.putString("menuQ", z);
//                            editor.apply();

//                            Intent i=new Intent(view.getContext(),customer_see_cart.class);
//                            i.putExtra("menuname",x);
//                            i.putExtra("menuprice",y);
//                            i.putExtra("menuQ",z);
//                            view.getContext().startActivity(i);
                            //Toast.makeText(view.getContext(), "cart added", Toast.LENGTH_SHORT).show();

                        }
                        else if (b>20)
                        {
                            Toast.makeText(view.getContext(), "Enter The Quantity Between 0 to 20", Toast.LENGTH_SHORT).show();
                        }


                    }

//                    private void addtocart(String x, String y, String z) {
//
//
//                    }
                });

                dialogPlus.show();
            }

        });
        holder5.c3.setOnClickListener(new View.OnClickListener() {
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
                Button popup_add_to_cart_btn=myview.findViewById(R.id.popup_menu_cart_btn);

//                ArrayAdapter<String> sppnr=new ArrayAdapter<String>(view.getContext(), R.layout.menu_sppnr,qun);
//                popup_menu_spnr.setAdapter(sppnr);
//
//                popup_menu_spnr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        qunity =qun[i];
//                    }
//                });


                popup_menu_name.setText(model5.getMenu_item_Name());
                popup_menu_price.setText(model5.getMenu_item_Price().trim());
                popup_menu_spnr.setText("0");



                //qunity=popup_menu_spnr.getSelectedItem().toString();

                popup_add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
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

                            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("ctmr_tb_num", MODE_PRIVATE);
                            String Table_number = sharedPreferences.getString("table_number_ctmr","");

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
                            DatabaseReference root2=database.getReference("order_list_2");
                            DatabaseReference root3=database.getReference("order_list_3");

                            root.child(Table_number).child(item_name).setValue(obj)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(view.getContext(), "cart added", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
//                                            order_list.add(item_name_X_item_quantity);
//                                            SharedPreferences sharedPref = view.getContext().getSharedPreferences("order_array", MODE_PRIVATE);
//                                            SharedPreferences.Editor editor = sharedPref.edit();
//                                            editor.putString("order_array_list", String.valueOf(order_list));
//                                            editor.apply();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                            Map<String ,Object> map2=new HashMap<>();
                            //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                            map2.put("item_name_X_item_quantity",item_name_X_item_quantity);
                            map2.put("item_price_X_item_quantity_eq_item_total_pr",item_price_X_item_quantity_eq_item_total_pr);

                            root2.child(Table_number).push().setValue(map2);


                            Map<String ,Object> map3=new HashMap<>();
                            //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
                            map3.put("item_name_X_item_quantity",item_name_X_item_quantity);
                            map3.put("item_price_X_item_quantity_eq_item_total_pr",item_price_X_item_quantity_eq_item_total_pr);
                            root3.child(Table_number).push().setValue(map3);


//                            firestore.collection("Cart_item").document(auth.getCurrentUser().getUid())
//                                    .collection("users").add(obj).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<DocumentReference> task) {
//                                            if (task.isSuccessful())
//                                            {
//                                                Toast.makeText(view.getContext(), "cart added" +
//                                                        "", Toast.LENGTH_SHORT).show();
//                                                dialogPlus.dismiss();
//                                            }
//                                            else
//                                            {
//                                                Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(view.getContext(), "Failed, Please Try Again", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });







//                                    addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(view.getContext(), "failed to add cart, please try again", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });

                            //addtocart(x,y,z);

//                            SharedPreferences sharedPref = view.getContext().getSharedPreferences("myKey", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPref.edit();
//                            editor.putString("menuname", x);
//                            editor.putString("menuprice", y);
//                            editor.putString("menuQ", z);
//                            editor.apply();

//                            Intent i=new Intent(view.getContext(),customer_see_cart.class);
//                            i.putExtra("menuname",x);
//                            i.putExtra("menuprice",y);
//                            i.putExtra("menuQ",z);
//                            view.getContext().startActivity(i);
                            //Toast.makeText(view.getContext(), "cart added", Toast.LENGTH_SHORT).show();

                        }
                        else if (b>20)
                        {
                            Toast.makeText(view.getContext(), "Enter The Quantity Between 0 to 20", Toast.LENGTH_SHORT).show();
                        }


                    }

//                    private void addtocart(String x, String y, String z) {
//
//
//                    }
                });

                dialogPlus.show();
            }

        });






    }

    @NonNull
    @Override
    public xyz_adapter.myviewholder8 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view8= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_menu_item_recyclerview_design,parent,false);
        return new myviewholder8(view8);
    }



//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        //Toast.makeText(view.getContext(), qun[i], Toast.LENGTH_SHORT).show();
////        if (i>0)
////        {
////
////            //Toast.makeText(view.getContext(),nm+qun[i], Toast.LENGTH_SHORT).show();
////
////
////
//////            nm=adapterView.getItemAtPosition(i).toString();
//////
//////            firebaseDatabase=FirebaseDatabase.getInstance();
//////            auth=FirebaseAuth.getInstance();
//////            String cuurenttime,currentdate;
//////            Calendar calendar=Calendar.getInstance();
//////
//////            SimpleDateFormat date=new SimpleDateFormat("MM dd, yyyy");
//////            currentdate=date.format(calendar.getTime());
//////
//////            SimpleDateFormat time=new SimpleDateFormat("HH:mm:ss a");
//////            cuurenttime=time.format(calendar.getTime());
//////
//////            HashMap<String,Object> cartmap=new HashMap<>();
//////            cartmap.put("menuname",models.getMenu_item_Name());
//////            cartmap.put("menuprice",models.getMenu_item_Price());
//////            cartmap.put("menucat",models.getMenu_item_Cat());
//////            cartmap.put("quantity",qun[i]);
//////            cartmap.put("Cdate",currentdate);
//////            cartmap.put("Ctime",cuurenttime);
//////
//////            FirebaseDatabase.getInstance().getReference("cart").setValue(cartmap)
//////                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//////                        @Override
//////                        public void onComplete(@NonNull Task<Void> task) {
//////                            Toast.makeText(view.getContext(), "added", Toast.LENGTH_SHORT).show();
//////                        }
//////                    })
//////                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//////                        @Override
//////                        public void onSuccess(Void unused) {
//////                            Toast.makeText(view.getContext(), "add", Toast.LENGTH_SHORT).show();
//////                        }
//////                    })
//////                    .addOnFailureListener(new OnFailureListener() {
//////                        @Override
//////                        public void onFailure(@NonNull Exception e) {
//////                            Toast.makeText(view.getContext(), "nahi hua", Toast.LENGTH_SHORT).show();
//////                        }
//////                    });
////
////
////        }
////        else
////        {
////
////        }
//
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }





    class myviewholder8 extends RecyclerView.ViewHolder {
        TextView a1,b2,c3;

        public myviewholder8(@NonNull View itemView3) {
            super(itemView3);
            a1=(TextView) itemView3.findViewById(R.id.customer_rcv_design_menu_name);
            b2=(TextView) itemView3.findViewById(R.id.customer_rcv_design_menu_price);
            c3=(TextView) itemView3.findViewById(R.id.customer_rcv_design_menu_cat);

        }
    }
//                    public xyz_adapter(@NonNull FirebaseRecyclerOptions<menu_item_model> options) {
//                        super(options);
//                    }
//
//                    @Override
//                    protected void onBindViewHolder(@NonNull xyz_adapter.myviewholder7 holder2, int position, @NonNull menu_item_model model2) {
//                        holder2.a.setText(model2.getMenu_item_Name());
//                        holder2.b.setText(model2.getMenu_item_Price());
//                    }
//
//                    @NonNull
//                    @Override
//                    public xyz_adapter.myviewholder7 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_menu_item_recyclerview_design,parent,false);
//                        return new myviewholder7(view2);
//                    }


}

