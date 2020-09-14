package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patientsignup extends AppCompatActivity {
    private EditText fullname,age,address,emailid,pass,compass,city,state,country,street;
    private Button button;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Intent intent;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientsignup);
        fullname=findViewById(R.id.patientfullname);
        age=findViewById(R.id.patientage);
        address=findViewById(R.id.patientaddress);
        city=findViewById(R.id.patientcityname);
        state=findViewById(R.id.patientstate);
        country=findViewById(R.id.patientcountry);
        street=findViewById(R.id.patientstreet);
        emailid=findViewById(R.id.patientemail);
        pass=findViewById(R.id.patientpassword);
        compass=findViewById(R.id.patientcompassword);
        button=findViewById(R.id.patientsignuppage);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("PatientDetails");
        dialog=new ProgressDialog(patientsignup.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Registration in process please wait !!!");
                dialog.show();
                if ((fullname.getText().toString()).isEmpty()
                        &(age.getText().toString()).isEmpty()
                        &(address.getText().toString()).isEmpty()
                        &(city.getText().toString()).isEmpty()
                        &(state.getText().toString()).isEmpty()
                        &(country.getText().toString()).isEmpty()
                        &(street.getText().toString()).isEmpty()
                        & (emailid.getText().toString()).isEmpty()
                        & (pass.getText().toString()).isEmpty()
                        &(compass.getText().toString()).isEmpty()) {
                    dialog.dismiss();
                    alertmessage("Some fields are missing !!!");
                }
                else
                {

                    if ((pass.getText().toString()).equals(compass.getText().toString())) {
                        firebaseAuth.createUserWithEmailAndPassword(emailid.getText().toString(),pass.getText().toString())
                                .addOnCompleteListener(patientsignup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful())
                                        {
                                            String name=fullname.getText().toString();
                                            String ag=age.getText().toString();
                                            String add=address.getText().toString();
                                            String cit=city.getText().toString();
                                            String stat=state.getText().toString();
                                            String countr=country.getText().toString();
                                            String stree=street.getText().toString();
                                            String em=emailid.getText().toString();
                                            String pas=pass.getText().toString();
                                            PatienrUser user = new PatienrUser(name,ag,add,cit,stat,countr,em,stree);
                                            databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        firebaseAuth.getCurrentUser().sendEmailVerification().
                                                                addOnCompleteListener(patientsignup.this, new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if(task.isSuccessful())
                                                                        {
                                                                            FirebaseDatabase.getInstance().getReference().child("PatientsList")
                                                                                    .child(firebaseAuth.getCurrentUser().getUid())
                                                                      .setValue(emailid.getText().toString());
                                                                            dialog.dismiss();
                                                                            alertmessage("Verification email sent !!");
                                                                            Toast.makeText(patientsignup.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                                                                            intent=new Intent(patientsignup.this,Login.class);
                                                                            startActivity(intent);
                                                                        }
                                                                        else
                                                                        {
                                                                            alertmessage("Error Sending Verification Email !!");
                                                                        }

                                                                    }
                                                                });
                                                    }

                                                    else
                                                    {
                                                        dialog.dismiss();
                                                        alertmessage("Registration failed due to some fatal error!!");
                                                    }

                                                }
                                            });


                                        }

                                    }
                                });
                    }
                    else
                    {
                        dialog.dismiss();
                        alertmessage("Password and confirm password mismatch");
                    }
                }

            }

        });
    }
    public void alertmessage(String mssg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(patientsignup.this);
        builder.setMessage(mssg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        }).show();
    }
}
class PatienrUser {

    public String username;
    public String email;
    public String age;
    public String address;
    public String city;
    public String state;
    public String country;
    public String street;


    public PatienrUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public PatienrUser(String username, String age,String address,String city,String state,String country,String email,String street) {
        this.username = username;
        this.email = email;
        this.address=address;
        this.city=city;
        this.state=state;
        this.country=country;
        this.age=age;
        this.street=street;
    }

}