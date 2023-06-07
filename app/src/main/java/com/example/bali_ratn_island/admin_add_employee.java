package com.example.bali_ratn_island;

import static com.example.bali_ratn_island.R.drawable.person_logo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.orhanobut.dialogplus.DialogPlus;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class admin_add_employee extends AppCompatActivity {
    String[] ind_states = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
            "Goa", "Gujarat", "Haryana", "Himachal Prades", "Jharkhand", "Karnataka", "Kerala",
            "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland",
            "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura",
            "Uttar Pradesh", "Uttarakhand", "West Bengal"};

    String[] position_emp = {"Chef", "Manager"};

    String item_state, item_pos;
    TextInputLayout fname, lname, age, phone, mail, position, staff_id, address, pincode, state, edu_q,cr_pwd,cnf_pwd;
    CircleImageView face;
    RadioGroup radioGroup;
    AutoCompleteTextView position_dropdown, state_dropdown;
    RadioButton radioButton;
    Button sign_up;
    ArrayAdapter<String> arrayAdapter_for_state, arrayAdapter_for_staff_pos;
    Bitmap bitmap;
    Uri filepath;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    FirebaseAuth mAuth;
//    String otpid;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_employee);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sign_up = findViewById(R.id.sign_up);
        position_dropdown = findViewById(R.id.staff_postition);
        state_dropdown = findViewById(R.id.state_dropdown);

        //mAuth=FirebaseAuth.getInstance();

        sign_up.setEnabled(false);

//        all input layous
        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone_number);
        mail = findViewById(R.id.email_address);
        staff_id = findViewById(R.id.staff_id);
        address = findViewById(R.id.home_address);
        pincode = findViewById(R.id.pin_code);
        edu_q = findViewById(R.id.edu_qualification);
        cr_pwd = findViewById(R.id.create_password);
        cnf_pwd = findViewById(R.id.confirm_password);

//        circle image
        face = findViewById(R.id.employee_img_add);

//        radio group and button
        radioGroup = findViewById(R.id.radio_grp);
//        male_b = findViewById(R.id.radio_btn_male);
//        female_b = findViewById(R.id.radio_btn_female);


        arrayAdapter_for_state = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, ind_states);
        arrayAdapter_for_staff_pos = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, position_emp);

        state_dropdown.setAdapter(arrayAdapter_for_state);
        position_dropdown.setAdapter(arrayAdapter_for_staff_pos);

        state_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item_state = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(admin_add_employee.this, "state is: "+item_state+i, Toast.LENGTH_SHORT).show();
            }
        });

        position_dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item_pos = adapterView.getItemAtPosition(i).toString();
                //item pos is selected item
                //Toast.makeText(admin_add_employee.this, "state is: "+item_pos, Toast.LENGTH_SHORT).show();
            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up.setEnabled(true);
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image File"),1);
//                intent.putExtra("crop", "true");
//                intent.putExtra("scale", true);
//                intent.putExtra("outputX", 256);
//                intent.putExtra("outputY", 256);
//                intent.putExtra("aspectX", 1);
//                intent.putExtra("aspectY", 1);
//                intent.putExtra("return-data", true);
                //startActivityForResult(intent, 1);

            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Fnamen,Lname,Age,Phone,Mail,Staff_id,Address,Pincode,Edu_q,Cr_pwd,Cnf_pwd,Gan,State,Poz,Pimg;


                Fnamen=fname.getEditText().getText().toString();
                Lname=lname.getEditText().getText().toString();
                Age=age.getEditText().getText().toString();
                Phone=phone.getEditText().getText().toString();
                Mail=mail.getEditText().getText().toString();
                Staff_id=staff_id.getEditText().getText().toString();
                Address=address.getEditText().getText().toString();
                Pincode=pincode.getEditText().getText().toString();
                Edu_q=edu_q.getEditText().getText().toString();
                Cr_pwd=cr_pwd.getEditText().getText().toString();
                Cnf_pwd=cnf_pwd.getEditText().getText().toString();


                if (Fnamen != null && Lname != null && Age != null && Phone != null && Mail != null && Staff_id != null && Address != null && Pincode != null && Edu_q != null && Cr_pwd != null && Cnf_pwd != null)
                {

                    if (Phone.length()<11 && Phone.length()>9)
                    {
                        if (Pincode.length()==6)
                        {
                            if (Cnf_pwd.equals(Cr_pwd))
                            {
                                if (!isValidPhoneNumber(Phone))
                                {
                                    Toast.makeText(admin_add_employee.this, "Enter correct Phone number", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    nextpage();

                                    String phoneNumber = phone.getEditText().getText().toString(); // Phone number to send the SMS to
                                    String message = "You're now Bali's family member"; // Message to send
                                    String msg3="Dear "+fname.getEditText().getText().toString()+" "+lname.getEditText().getText().toString()+" Thank you for being a part of our BALI RATN ISLAND, from now on you are our family member \n\n"+"Your Staff id is: "+staff_id.getEditText().getText().toString()+" And Password is: "+cnf_pwd.getEditText().getText().toString()+" \n\n You can change your password after login \n\n Thank You";


                                    try {
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                                        Toast.makeText(getApplicationContext(), "SMS sent successfully!", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "SMS failed to send!", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                }

                            }
                            else {
                                Toast.makeText(admin_add_employee.this, "Enter Correct Details Please", Toast.LENGTH_SHORT).show();
                            }
                            
                        }
                        else {
                            Toast.makeText(admin_add_employee.this, "Enter Correct Details Please", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(admin_add_employee.this, "Enter Correct Details Please", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(admin_add_employee.this, "Enter Correct Details Please", Toast.LENGTH_SHORT).show();
                }

                
            }

            private void nextpage()
            {
                    ProgressDialog dialog=new ProgressDialog(admin_add_employee.this);
                    dialog.setTitle("Data Uploading");
                    dialog.show();


                    FirebaseStorage storage=FirebaseStorage.getInstance();
                    StorageReference uploader=storage.getReference("Staff_Photo"+new Random().nextInt(50));

                    uploader.putFile(filepath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                String Fnamen,Lname,Age,Phone,Mail,Staff_id,Address,Pincode,Edu_q,Cr_pwd,Cnf_pwd,Gan,State,Poz,Pimg;


                                                Fnamen=fname.getEditText().getText().toString();
                                                Lname=lname.getEditText().getText().toString();
                                                Age=age.getEditText().getText().toString();
                                                Phone=phone.getEditText().getText().toString();
                                                Mail=mail.getEditText().getText().toString();
                                                Staff_id=staff_id.getEditText().getText().toString();
                                                Address=address.getEditText().getText().toString();
                                                Pincode=pincode.getEditText().getText().toString();
                                                Edu_q=edu_q.getEditText().getText().toString();
                                                Cr_pwd=cr_pwd.getEditText().getText().toString();
                                                Cnf_pwd=cnf_pwd.getEditText().getText().toString();

                                                int selectedId=radioGroup.getCheckedRadioButtonId();
                                                radioButton=(RadioButton) findViewById(selectedId);
                                                Gan=radioButton.getText().toString();

                                                State = item_state.toString();
                                                Poz=item_pos.toString();


                                                FirebaseDatabase database=FirebaseDatabase.getInstance();
                                                DatabaseReference root=database.getReference("staff_of_bali");


                                                emp_model obbbj=new emp_model(Fnamen,Lname,Age,Phone,Mail,Staff_id,Address,Pincode,Edu_q,Cr_pwd,Cnf_pwd,Gan,State,Poz,uri.toString());
    //                                            emp_model ob=new emp_model(Fname);
                                                root.child(staff_id.getEditText().getText().toString()).setValue(obbbj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {




//                                                        SmsManager mysms=SmsManager.getDefault();
//                                                        String msg="Dear "+Fnamen+" "+Lname+" Thank you for being a part of our BALI RATN ISLAND, from now on you are our family member \n\n"+"Your Staff id is: "+Staff_id+" And Password is: "+Cnf_pwd+" \n\n You can change your password after login \n\n Thank You";
//                                                        mysms.sendTextMessage(Phone,null,msg,null,null);
                                                    }
                                                });

                                                fname.getEditText().setText("");
                                                lname.getEditText().setText("");
                                                age.getEditText().setText("");
                                                phone.getEditText().setText("");
                                                mail.getEditText().setText("");
                                                staff_id.getEditText().setText("");
                                                address.getEditText().setText("");
                                                pincode.getEditText().setText("");
                                                edu_q.getEditText().setText("");
                                                cnf_pwd.getEditText().setText("");
                                                cr_pwd.getEditText().setText("");
                                                face.setImageResource(person_logo);
                                                dialog.dismiss();
                                                sign_up.setEnabled(false);

                                                Toast.makeText(admin_add_employee.this, "uploaded", Toast.LENGTH_SHORT).show();







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


//                mAuth=FirebaseAuth.getInstance();
//                mAuth.createUserWithEmailAndPassword(Mail,"BALI_RATN_ISLAND")
//                        .addOnCompleteListener(admin_add_employee.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful())
//                                {
//                                    FirebaseUser user=mAuth.getCurrentUser();
//                                    user.reload();
//                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void unused) {
//                                            Toast.makeText(admin_add_employee.this, "check mail please", Toast.LENGTH_SHORT).show();
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(admin_add_employee.this, "not send mail", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                    //Toast.makeText(admin_add_employee.this, "complate", Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                {
//                                    Toast.makeText(admin_add_employee.this, "Failed", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
////                            private void add_staff_to_the_database() {
//////                                Map<String ,Object> map5=new HashMap<>();
//////                                map5.put("fnm",fname.getEditText().getText().toString());
//////                                map5.put("lnm",lname.getEditText().getText().toString());
//////
//////                                FirebaseDatabase staff_db_tb=FirebaseDatabase.getInstance();
//////                                DatabaseReference node2=staff_db_tb.getReference("staff_member");
////////                node.child("Table Id: "+Table_Id).setValue(obj2)
//////                                node2.child("Email Id: "+Mail).setValue(map5)
//////                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//////                                            @Override
//////                                            public void onSuccess(Void unused) {
//////                                                Toast.makeText(admin_add_employee.this, "aded", Toast.LENGTH_SHORT).show();
//////                                            }
//////                                        })
//////                                        .addOnFailureListener(new OnFailureListener() {
//////                                            @Override
//////                                            public void onFailure(@NonNull Exception e) {
//////                                                Toast.makeText(admin_add_employee.this, "nahi", Toast.LENGTH_SHORT).show();
//////                                            }
//////                                        });
////                            }
//                        });
//

//                Intent np=new Intent(admin_add_employee.this,admin_add_employee_2.class);
//                np.putExtra("fnm",Fnamen);
//                np.putExtra("lnm",Lname);
//                np.putExtra("age",Age);
//                np.putExtra("pn",Phone);
//                np.putExtra("ml",Mail);
//                np.putExtra("stid",Staff_id);
//                np.putExtra("add",Address);
//                np.putExtra("pin",Pincode);
//                np.putExtra("edu",Edu_q);
//                np.putExtra("gan",gan);
//                np.putExtra("state",item_state);
//                np.putExtra("pos",item_pos);
//                np.putExtra("face",bitmap);
//                np.putExtra("pathimg",filepath);
//
//                startActivity(np);


//                SimpleDateFormat format=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
//                Date now=new Date();
//                String filename=format.format(now);
//                storageReference = FirebaseStorage.getInstance().getReference("staff_img/"+filename);
//                storageReference.putFile(filepath)
//                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                //Toast.makeText(admin_add_employee.this, "img", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                            }
//                        });

//                fname.getEditText().setText("");
//                lname.getEditText().setText("");
//                age.getEditText().setText("");
//                phone.getEditText().setText("");
//                mail.getEditText().setText("");
//                staff_id.getEditText().setText("");
//                address.getEditText().setText("");
//                pincode.getEditText().setText("");
//                edu_q.getEditText().setText("");
//                face.setImageResource(person_logo);

            }
        });

    }

    private boolean isValidPhoneNumber(String phone) {
        String regex = "\\d{10}"; // regular expression to match 10 digit phone numbers
        return phone.matches(regex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode==RESULT_OK)
        {

                filepath = data.getData();
                try
                {
                    InputStream inputStream=getContentResolver().openInputStream(filepath);
                    bitmap=BitmapFactory.decodeStream(inputStream);
                    face.setImageBitmap(bitmap);
                }catch (Exception e)
                {

                }

//                final Bundle extras = data.getExtras();
//                //Get image
//                bitmap = extras.getParcelable("data");
//                face.setImageBitmap(bitmap);
                //face.setImageURI(filepath);

        }
        super.onActivityResult(requestCode, resultCode, data);



    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        moveTaskToBack(false);


                startActivity(new Intent(admin_add_employee.this,admin_employee.class));
                finish();
    }
}