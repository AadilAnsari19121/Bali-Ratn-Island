package com.example.bali_ratn_island;

import static com.example.bali_ratn_island.R.drawable.person_logo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class admin_add_cat extends AppCompatActivity {

    CircleImageView cat_img;
    EditText cat_name_tb,cat_img_url_tb;
    Button cat_add_btn;
    DatabaseReference root=FirebaseDatabase.getInstance().getReference("category_table");
    DatabaseReference root2=FirebaseDatabase.getInstance().getReference("category_for_menu");
    StorageReference reference = FirebaseStorage.getInstance().getReference("category_image");
    StorageReference reference2 = FirebaseStorage.getInstance().getReference("category_image");
    Uri filepath;
    Bitmap bitmap;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_cat);
        //cat_img_url_tb=findViewById(R.id.img_url);
        cat_name_tb=findViewById(R.id.cat_name);
        cat_add_btn=findViewById(R.id.cat_add_btn);
        cat_img=findViewById(R.id.cat_image);
        String category__name=cat_name_tb.getText().toString();

        cat_add_btn.setEnabled(false);
        cat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image File"),1);
                cat_add_btn.setEnabled(true);
//
            }
        });

        cat_add_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String category__name=cat_name_tb.getText().toString();
                if (cat_name_tb.getText().toString().isEmpty())
                {
                    Toast.makeText(admin_add_cat.this, "enter the category name and url please", Toast.LENGTH_SHORT).show();
                    cat_img.setImageResource(R.drawable.food_bg);
                }

                else if (!cat_name_tb.getText().toString().isEmpty())
                {
                    processinsertcat();
                }

                else
                {
                    Toast.makeText(admin_add_cat.this, "enter the category name or url please", Toast.LENGTH_SHORT).show();
                }
            }

            private void processinsertcat()
            {
                ProgressDialog dialog=new ProgressDialog(admin_add_cat.this);
                dialog.setTitle("Data Uploading");
                dialog.show();

                FirebaseStorage storage=FirebaseStorage.getInstance();
                StorageReference uploader=storage.getReference("cat_imgs_pics/"+new Random().nextInt(50));

                uploader.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        Map<String ,Object> map=new HashMap<>();
                                        map.put("cat_img_url",uri.toString());
                                        map.put("cat_name",cat_name_tb.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("category_table").push()
                                                .setValue(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>()
                                                {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(admin_add_cat.this, "category add successfully", Toast.LENGTH_SHORT).show();
                                                        cat_name_tb.setText("");
                                                        // cat_img_url_tb.setText("");
                                                        cat_img.setImageResource(R.drawable.food_bg);
                                                        dialog.dismiss();
                                                        cat_add_btn.setEnabled(false);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener()
                                                {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e)
                                                    {
                                                        Toast.makeText(admin_add_cat.this, "could not insert please try again", Toast.LENGTH_SHORT).show();
                                                    }
                                                });



                                        Map<String ,Object> map2=new HashMap<>();
                                        map2.put("cat_img_url",uri.toString());
                                        map2.put("cat_name",cat_name_tb.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("category_for_menu").push()
                                                .setValue(map2)
                                                .addOnSuccessListener(new OnSuccessListener<Void>()
                                                {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(admin_add_cat.this, "category add successfully", Toast.LENGTH_SHORT).show();
                                                        cat_name_tb.setText("");
                                                        //cat_img_url_tb.setText("");
                                                        //cat_img.setImageResource(R.mipmap.camera);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener()
                                                {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e)
                                                    {
                                                        Toast.makeText(admin_add_cat.this, "could not insert please try again", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
}
                                });
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                float prc=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                                dialog.setMessage("Uploaded : "+(int)prc+" %");
                            }
                        });



//                Map<String ,Object> map=new HashMap<>();
//               // map.put("cat_img_url",cat_img_url_tb.getText().toString());
//                map.put("cat_name",cat_name_tb.getText().toString());
//                FirebaseDatabase.getInstance().getReference().child("category_table").push()
//                        .setValue(map)
//                        .addOnSuccessListener(new OnSuccessListener<Void>()
//                        {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(admin_add_cat.this, "category add successfully", Toast.LENGTH_SHORT).show();
//                                cat_name_tb.setText("");
//                               // cat_img_url_tb.setText("");
//                                cat_img.setImageResource(R.mipmap.camera);
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener()
//                        {
//                            @Override
//                            public void onFailure(@NonNull Exception e)
//                            {
//                                Toast.makeText(admin_add_cat.this, "could not insert please try again", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//
//                Map<String ,Object> map2=new HashMap<>();
//                //map2.put("cat_img_url",cat_img_url_tb.getText().toString());
//                map2.put("cat_name",cat_name_tb.getText().toString());
//                FirebaseDatabase.getInstance().getReference().child("category_for_menu").push()
//                        .setValue(map2)
//                        .addOnSuccessListener(new OnSuccessListener<Void>()
//                        {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(admin_add_cat.this, "category add successfully", Toast.LENGTH_SHORT).show();
//                                cat_name_tb.setText("");
//                                //cat_img_url_tb.setText("");
//                                cat_img.setImageResource(R.mipmap.camera);
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener()
//                        {
//                            @Override
//                            public void onFailure(@NonNull Exception e)
//                            {
//                                Toast.makeText(admin_add_cat.this, "could not insert please try again", Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }

        });


//        cat_img_url_tb.addTextChangedListener(new TextWatcher()
//        {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
//            {
//                cat_img.setImageResource(R.mipmap.camera);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
//            {
//                String a=cat_img_url_tb.getText().toString();
//                Glide.with(admin_add_cat.this).load(a).into(cat_img);
//
//            }
//            @Override
//            public void afterTextChanged(Editable editable) { }
//        });
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want add more items...?").setTitle("Add more...?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                startActivity(new Intent(admin_add_cat.this,admin_cart.class));
               // finish();
                dialogInterface.dismiss();
            }
        });
        builder.show();
//        super.onBackPressed();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1 && resultCode==RESULT_OK)
        {
            filepath=data.getData();

            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap=BitmapFactory.decodeStream(inputStream);
                cat_img.setImageBitmap(bitmap);
            }catch (Exception e)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}