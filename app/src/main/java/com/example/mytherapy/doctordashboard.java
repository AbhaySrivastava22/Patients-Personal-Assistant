package com.example.mytherapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class doctordashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView reminder,report,doctor,pharmacy,search,aboutus,feedback,appointments,add_medicine;
    private TextView textView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordashboard);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        textView=findViewById(R.id.dcdashprofile);
        reminder=(CardView)findViewById(R.id.dcdashreminder);
        report=(CardView)findViewById(R.id.dcdashreport);
        doctor=(CardView)findViewById(R.id.dcdashdoctor);
        pharmacy=(CardView)findViewById(R.id.dcdashpharmacy);
        search=(CardView)findViewById(R.id.dcdashsearch);
        aboutus=(CardView)findViewById(R.id.dcdashaboutus);
        feedback=(CardView)findViewById(R.id.dcdashfeedback);
        appointments=(CardView)findViewById(R.id.dcdashboardAppointments);
        add_medicine=(CardView)findViewById(R.id.dcdashaddmedicine) ;
        textView.setOnClickListener(this);
        reminder.setOnClickListener(this);
        report.setOnClickListener(this);
        doctor.setOnClickListener(this);
        pharmacy.setOnClickListener(this);
        search.setOnClickListener(this);
        aboutus.setOnClickListener(this);
        feedback.setOnClickListener(this);
        add_medicine.setOnClickListener(this);
        appointments.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.dcdashprofile:
                intent=new Intent(doctordashboard.this,userprofile.class);
                startActivity(intent);
                break;
            case R.id.dcdashreminder:
                intent=new Intent(doctordashboard.this,reminder.class);
                startActivity(intent);
                break;
            case R.id.dcdashreport:
                intent=new Intent(doctordashboard.this,Report.class);
                startActivity(intent);
                break;
            case  R.id.dcdashdoctor:
                opendialog(1);
                break;
            case R.id.dcdashpharmacy:
                opendialog(2);
                break;
            case R.id.dcdashsearch:
                opendialog(3);
                break;
            case R.id.dcdashaboutus:
                intent=new Intent(doctordashboard.this,AboutUs.class);
                startActivity(intent);
                break;
            case R.id.dcdashfeedback:
                intent=new Intent(doctordashboard.this,Feedback.class);
                startActivity(intent);
                break;
            case R.id.dcdashboardAppointments:
                intent=new Intent(doctordashboard.this,doctorsappointment.class);
                startActivity(intent);
                break;
            case R.id.dcdashaddmedicine:
                intent=new Intent(doctordashboard.this,addmedicine.class);
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
