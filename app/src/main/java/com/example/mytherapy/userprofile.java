package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userprofile extends AppCompatActivity {
    private ImageView imageView;
    private TextView name,emailid,age,country,state,address,street,city;
    private Button logout;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        imageView=(ImageView)findViewById(R.id.profiledp);
        name=(TextView)findViewById(R.id.profilename);
        emailid=(TextView)findViewById(R.id.profileemail);
        age=(TextView)findViewById(R.id.profiledob);
        country=(TextView)findViewById(R.id.profilecou);
        state=(TextView)findViewById(R.id.profilestate);
        address=(TextView)findViewById(R.id.profileaddress);
        city=(TextView)findViewById(R.id.profilecity);
        street=(TextView)findViewById(R.id.userprofilestreet);
        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent=new Intent(userprofile.this,MainActivity.class);
                startActivity(intent);
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("PatientDetails");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child("username").getValue().toString());
                emailid.setText(dataSnapshot.child("email").getValue().toString());
                age.setText(dataSnapshot.child("age").getValue().toString());
                address.setText(dataSnapshot.child("address").getValue().toString());
                street.setText(dataSnapshot.child("street").getValue().toString());
                city.setText(dataSnapshot.child("city").getValue().toString());
                state.setText(dataSnapshot.child("state").getValue().toString());
                country.setText(dataSnapshot.child("country").getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}
