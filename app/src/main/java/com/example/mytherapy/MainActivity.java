package com.example.mytherapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private ImageView image;
    private Button login , signup;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.mainlogin);
        signup=(Button)findViewById(R.id.mainsignup);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.mainlogin:
                intent=new Intent(this,Login.class);
                startActivity(intent);
                break;
            case R.id.mainsignup:
                opendialog();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void opendialog(){
        dialogbox dialog=new dialogbox();
        dialog.show(getSupportFragmentManager(),"SignUp Dialog");
    }
}

