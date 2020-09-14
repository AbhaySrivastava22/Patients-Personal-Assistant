package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class doctorsignup extends AppCompatActivity {
    private EditText fullname,specialization,experience,clinickname,emailid,pass,compass,address,city,state,country,street;
    private Button button;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Intent intent;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsignup);
        fullname=findViewById(R.id.fullname);
        specialization=findViewById(R.id.specialization);
        experience=findViewById(R.id.experience);
        clinickname=findViewById(R.id.clinic_name);
        address=findViewById(R.id.patientaddress);
        city=findViewById(R.id.patientcityname);
        state=findViewById(R.id.patientstate);
        country=findViewById(R.id.patientcountry);
        street=findViewById(R.id.doctorstreet);
        emailid=findViewById(R.id.doctoremailid);
        pass=findViewById(R.id.doctorpassword);
        compass=findViewById(R.id.conpassword);
        button=findViewById(R.id.doctorsignuppage);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("DoctorsDetails");
        dialog=new ProgressDialog(doctorsignup.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Registration in process please wait !!!");
                dialog.show();
                if ((fullname.getText().toString()).isEmpty() & (specialization.getText().toString()).isEmpty()
                        &(experience.getText().toString()).isEmpty()
                        &(clinickname.getText().toString()).isEmpty()
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
                          .addOnCompleteListener(doctorsignup.this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful())
                                  {
                                      String name=fullname.getText().toString();
                                      String spec=specialization.getText().toString();
                                      String clinic=clinickname.getText().toString();
                                      String exp=experience.getText().toString();
                                      String add=address.getText().toString();
                                      String cit=city.getText().toString();
                                      String stat=state.getText().toString();
                                      String countr=country.getText().toString();
                                      String stree=street.getText().toString();
                                      String em=emailid.getText().toString();
                                      String pas=pass.getText().toString();
                                      User user = new User(name, em,clinic,spec,exp,add,cit,stat,countr,stree);
                                      databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                                              .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {
                                              if(task.isSuccessful())
                                              {
                                                  firebaseAuth.getCurrentUser().sendEmailVerification().
                                                          addOnCompleteListener(doctorsignup.this, new OnCompleteListener<Void>() {
                                                      @Override
                                                      public void onComplete(@NonNull Task<Void> task) {
                                                          if(task.isSuccessful())
                                                          {
                                                              FirebaseDatabase.getInstance().getReference().child("DoctorsList")
                                                                      .child((firebaseAuth.getCurrentUser().getUid()))
                                                                      .setValue(emailid.getText().toString());
                                                              dialog.dismiss();
                                                              Toast.makeText(doctorsignup.this,"Registered Successfully and verification " +
                                                                              "email has been sent!!!",Toast.LENGTH_LONG).show();
                                                              intent=new Intent(doctorsignup.this,Login.class);
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
     AlertDialog.Builder builder=new AlertDialog.Builder(doctorsignup.this);
     builder.setMessage(mssg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();

         }
     }).show();
 }
}
class User {

    public String username;
    public String email;
    public String specialization;
    public String Clinic_Name;
    public String Experience;
    public String clinic_address;
    public String city;
    public String state;
    public String country;
    public String street;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String specialization,String Clinic_Name,String Experience,
                String clinic_address,String city,String state,String country,String  street) {
        this.username = username;
        this.email = email;
        this.Clinic_Name=Clinic_Name;
        this.specialization=specialization;
        this.Experience=Experience;
        this.clinic_address=clinic_address;
        this.city=city;
        this.state=state;
        this.country=country;
        this.street=street;
    }

}