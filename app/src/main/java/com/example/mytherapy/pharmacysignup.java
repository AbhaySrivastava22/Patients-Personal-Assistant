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

public class pharmacysignup extends AppCompatActivity {
    private EditText fullname,pharmacyname,emailid,pass,compass,address,city,state,country,street;
    private Button button;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Intent intent;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacysignup);
        fullname=findViewById(R.id.phafullname);
        pharmacyname=findViewById(R.id.pharmacy_name);
        emailid=findViewById(R.id.phaemail);
        pass=findViewById(R.id.phapassword);
        address=findViewById(R.id.phaaddress);
        city=findViewById(R.id.phcity);
        state=findViewById(R.id.pharstate);
        country=findViewById(R.id.phacountry);
        street=findViewById(R.id.phastreet);
        compass=findViewById(R.id.phacompassword);
        button=findViewById(R.id.pharmacysignuppage);
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("PharmacyDetails");
        dialog=new ProgressDialog(pharmacysignup.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Registration in process please wait !!!");
                dialog.show();
                if ((fullname.getText().toString()).isEmpty()
                        &(pharmacyname.getText().toString()).isEmpty()
                        & (emailid.getText().toString()).isEmpty()
                        & (pass.getText().toString()).isEmpty()
                        & (address.getText().toString()).isEmpty()
                        &(city.getText().toString()).isEmpty()
                        &(street.getText().toString()).isEmpty()
                        &(state.getText().toString()).isEmpty()
                        &(country.getText().toString()).isEmpty()
                        &(compass.getText().toString()).isEmpty()) {
                    dialog.dismiss();
                    alertmessage("Some fields are missing !!!");
                }
                else
                {

                    if ((pass.getText().toString()).equals(compass.getText().toString())) {
                        firebaseAuth.createUserWithEmailAndPassword(emailid.getText().toString(),pass.getText().toString())
                                .addOnCompleteListener(pharmacysignup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful())
                                        {
                                            String name=fullname.getText().toString();
                                            String phar=pharmacyname.getText().toString();
                                            String em=emailid.getText().toString();
                                            String pas=pass.getText().toString();
                                            String add=address.getText().toString();
                                            String cit=city.getText().toString();
                                            String stree=street.getText().toString();
                                            String stat=state.getText().toString();
                                            String countr=country.getText().toString();
                                            PharmacyUser user = new PharmacyUser(name, em,phar,add,cit,stat,countr,stree);
                                            databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        firebaseAuth.getCurrentUser().sendEmailVerification().
                                                                addOnCompleteListener(pharmacysignup.this, new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if(task.isSuccessful())
                                                                        {
                                                                            FirebaseDatabase.getInstance().getReference().child("PharmacyList")
                                                                                    .child(firebaseAuth.getCurrentUser().getUid())
                                                                      .setValue(emailid.getText().toString());
                                                                            dialog.dismiss();
                                                                            alertmessage("Verification email sent !!");
                                                                            Toast.makeText(pharmacysignup.this,"Registered Successfully"
                                                                                    ,Toast.LENGTH_LONG).show();
                                                                            intent=new Intent(pharmacysignup.this,Login.class);
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
        AlertDialog.Builder builder=new AlertDialog.Builder(pharmacysignup.this);
        builder.setMessage(mssg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        }).show();
    }
}
class PharmacyUser {

    public String username;
    public String email;
    public String pharmacy_name;
    public String address;
    public String city;
    public String state;
    public String country;
    public String street;

    public PharmacyUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public PharmacyUser(String username, String email,String pharmacy_name,String address,String city,String state,String country, String street) {
        this.username = username;
        this.email = email;
        this.address=address;
        this.pharmacy_name=pharmacy_name;
        this.city=city;
        this.state=state;
        this.country=country;
        this.street=street;

    }

}
