package com.example.mytherapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class pharmacydashboard extends AppCompatActivity implements View.OnClickListener{

    private CardView reminder,report,doctor,pharmacy,search,aboutus,feedback;
    private TextView textView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacydashboard);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        textView=findViewById(R.id.phdashprofile);
        reminder=(CardView)findViewById(R.id.phdashreminder);
        report=(CardView)findViewById(R.id.phdashreport);
        doctor=(CardView)findViewById(R.id.phdashdoctor);
        pharmacy=(CardView)findViewById(R.id.phdashpharmacy);
        search=(CardView)findViewById(R.id.phdashsearch);
        aboutus=(CardView)findViewById(R.id.phdashaboutus);
        feedback=(CardView)findViewById(R.id.phdashfeedback);
        textView.setOnClickListener(this);
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
            case R.id.phdashprofile:
                intent=new Intent(pharmacydashboard.this,userprofile.class);
                startActivity(intent);
                break;
            case R.id.phdashreminder:
                intent=new Intent(pharmacydashboard.this,reminder.class);
                startActivity(intent);
                break;
            case R.id.phdashreport:
                intent=new Intent(pharmacydashboard.this,Report.class);
                startActivity(intent);
                break;
            case  R.id.phdashdoctor:
                opendialog(1);
                break;
            case R.id.phdashpharmacy:
                opendialog(2);
                break;
            case R.id.phdashsearch:
              opendialog(3);
                break;
            case R.id.phdashaboutus:
                intent=new Intent(pharmacydashboard.this,AboutUs.class);
                startActivity(intent);
                break;
            case R.id.phdashfeedback:
                intent=new Intent(pharmacydashboard.this,Feedback.class);
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
