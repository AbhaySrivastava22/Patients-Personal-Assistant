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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button feedback;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Intent intent;
    EditText mssg;
    EditText name;
    ProgressDialog dialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Feedback.super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        this.name = (EditText) findViewById(R.id.feduser);
        this.mssg = (EditText) findViewById(R.id.fedmssg);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");
        this.feedback = (Button) findViewById(R.id.feedbackcall);
        FirebaseUser currentUser = this.firebaseAuth.getCurrentUser();
        dialog=new ProgressDialog(Feedback.this);
        this.feedback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.setMessage("Submitting Feedback please wait!!!");
                dialog.show();
                FirebaseDatabase.getInstance().getReference("Feedback")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(new FeedbackChild(Feedback.this.name.getText().toString(),
                                Feedback.this.mssg.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            /* JADX WARNING: type inference failed for: r0v2, types: [com.example.nutritionassistant.feedback, android.content.Context] */
                            /* JADX WARNING: type inference failed for: r0v5, types: [com.example.nutritionassistant.feedback, android.content.Context] */
                            /* JADX WARNING: type inference failed for: r2v3, types: [com.example.nutritionassistant.feedback, android.content.Context] */
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    alertmessage("Feedback submitted successfully!!!");

                                }
                                else
                                {
                                    alertmessage("Error sending feedback!!!");
                                }
                            }
                        });
            }
        });
    }
    public void alertmessage(String mssg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Feedback.this);
        builder.setMessage(mssg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        }).show();
    }
}
class FeedbackChild {
    public String mssg;
    public String name;
    public FeedbackChild()
    {
        //default constructor
    }


    public FeedbackChild(String nm, String ms) {
        this.name = nm;
        this.mssg = ms;
    }
}

