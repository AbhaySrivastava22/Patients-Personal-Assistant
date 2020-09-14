package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView doctor;
    private TextView pharmacy;
    private TextView patient;
    private Button login;
    private Intent intent;
    private EditText user;
    private EditText pass;
    private TextView forgetpassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog dialog;
    private DatabaseReference databaseReference;
    private static String[] user_type={"doctor","patient","pharmacy"};
    private AutoCompleteTextView autoCompleteTextView;
    private String usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        doctor=findViewById(R.id.doctorcall);
        pharmacy=findViewById(R.id.pharmacycall);
        patient=findViewById(R.id.patientcall);
        doctor.setOnClickListener(this);
        pharmacy.setOnClickListener(this);
        patient.setOnClickListener(this);
        user=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.login);
        forgetpassword=findViewById(R.id.forgetpassword);
        firebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(this);
        forgetpassword.setOnClickListener(this);
        dialog=new ProgressDialog(Login.this);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.usertype);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,user_type);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usertype=parent.getItemAtPosition(position).toString();
                autoCompleteTextView.setText(usertype);
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.doctorcall:
                intent=new Intent(this,doctorsignup.class);
                startActivity(intent);
                break;
            case R.id.pharmacycall:
                intent=new Intent(this,pharmacysignup.class);
                startActivity(intent);
                break;
            case R.id.patientcall:
                intent=new Intent(this,patientsignup.class);
                startActivity(intent);
                break;
            case R.id.login:
                dialog.setMessage("Verifying details and logging you in !!!");
                dialog.show();
                if ((user.getText().toString()).isEmpty() & (pass.getText().toString()).isEmpty())
                {
                    dialog.dismiss();
                    alertmessage("Email Id and Password is required !!!");

                }
                else {

                    firebaseAuth.signInWithEmailAndPassword(user.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                                    if (usertype=="doctor")
                                    {
                                        databaseReference=FirebaseDatabase.getInstance().getReference("DoctorsList");
                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(firebaseAuth.getCurrentUser().getUid()))
                                                {
                                                    intent = new Intent(Login.this, doctordashboard.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(intent);
                                                }
                                                else
                                                {
                                                    dialog.dismiss();
                                                    alertmessage("Not a registered doctor !!!");
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                             alertmessage("Server Error !!!");
                                            }
                                        });
                                    }
                                    else if(usertype=="patient")
                                    {
                                        databaseReference=FirebaseDatabase.getInstance().getReference("PatientsList");
                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(firebaseAuth.getCurrentUser().getUid()))
                                                {
                                                   intent = new Intent(Login.this, dashboard.class);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                   startActivity(intent);

                                                }
                                                else
                                                {
                                                    dialog.dismiss();
                                                    alertmessage("Not a registered patient !!!");
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                alertmessage("Server Error !!!");

                                            }
                                        });
                                    }
                                    else if(usertype=="pharmacy")
                                    {
                                        databaseReference=FirebaseDatabase.getInstance().getReference("PharmacyList");
                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(firebaseAuth.getCurrentUser().getUid()))
                                                {
                                                   intent = new Intent(Login.this, pharmacydashboard.class);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                   startActivity(intent);
                                                }
                                                else
                                                {
                                                    dialog.dismiss();
                                                    alertmessage("Not a registered doctor !!!");
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                alertmessage("Server Error !!!");

                                            }
                                        });
                                    }
                                    else
                                    {
                                        dialog.dismiss();
                                        alertmessage("Invalid UserType");
                                    }


                                }
                                else
                                {
                                    dialog.dismiss();
                                    alertmessage("Email not Verified. Verify the email to get login !!!");
                                }
                            } else {
                                dialog.dismiss();
                                alertmessage("Invalid login credentials");

                            }
                        }
                    });
                }
                break;
            case R.id.forgetpassword:
                opendialog();
                break;

        }
    }
    public void alertmessage(String mssg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
        builder.setMessage(mssg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        }).show();
    }
    public void opendialog(){
        forgetpassword dialog=new forgetpassword();
        dialog.show(getSupportFragmentManager(),"SignUp Dialog");
    }
}

