package com.example.mytherapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboard extends AppCompatActivity implements View.OnClickListener {
     private CardView reminder,report,doctor,pharmacy,search,aboutus,feedback,profile;
     private FirebaseAuth firebaseAuth;
     private FirebaseUser firebaseUser;
     private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        profile=findViewById(R.id.dashprofile);
        reminder=(CardView)findViewById(R.id.dashreminder);
        report=(CardView)findViewById(R.id.dashreport);
        doctor=(CardView)findViewById(R.id.dashdoctor);
        pharmacy=(CardView)findViewById(R.id.dashpharmacy);
        search=(CardView)findViewById(R.id.dashsearch);
        aboutus=(CardView)findViewById(R.id.dashaboutus);
        feedback=(CardView)findViewById(R.id.dashfeedback);
        profile.setOnClickListener(this);
        reminder.setOnClickListener(this);
        report.setOnClickListener(this);
        doctor.setOnClickListener(this);
        pharmacy.setOnClickListener(this);
        search.setOnClickListener(this);
        aboutus.setOnClickListener(this);
        feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.dashprofile:
                intent=new Intent(dashboard.this,userprofile.class);
                startActivity(intent);
                break;
            case R.id.dashreminder:
                intent=new Intent(dashboard.this,reminder.class);
                startActivity(intent);
                break;
            case R.id.dashreport:
                intent=new Intent(dashboard.this,Report.class);
                startActivity(intent);
                break;
            case  R.id.dashdoctor:
                opendialog(1);
                break;
            case R.id.dashpharmacy:
                opendialog(2);
                break;
            case R.id.dashsearch:
             opendialog(3);
                break;
            case R.id.dashaboutus:
                intent=new Intent(dashboard.this,AboutUs.class);
                startActivity(intent);
                break;
            case R.id.dashfeedback:
                intent=new Intent(dashboard.this,Feedback.class);
                startActivity(intent);
                break;
        }

    }

    public  void  opendialog(int n)
    {
        if (n==1){
            doctordialog dialog=new doctordialog();
            dialog.show(getSupportFragmentManager(),"SignUp Dialog");

        }
        if (n==2)
        {
            pharmacydialog dialog=new pharmacydialog();
            dialog.show(getSupportFragmentManager(),"SignUp Dialog");
        }
        if (n==3)
        {
            searchdialog dialog=new searchdialog();
            dialog.show(getSupportFragmentManager(),"SignUp Dialog");
        }
    }
}


