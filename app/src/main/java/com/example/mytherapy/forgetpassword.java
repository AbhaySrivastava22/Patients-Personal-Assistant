package com.example.mytherapy;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatDialogFragment {
    private EditText resetemail;
    private FirebaseAuth firebaseAuth;
    private Button okbutton;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        final View view=layoutInflater.inflate(R.layout.forgetpassword,null);
        resetemail=(EditText)view.findViewById(R.id.resetemail);
        okbutton=(Button)view.findViewById(R.id.okbutton);
        builder.setView(view);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resetemail.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"Enter the email id !!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.getInstance().sendPasswordResetEmail(resetemail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                getDialog().dismiss();
                                Toast.makeText(getContext(), "Password reset email has been sent !!!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getContext(), "Invalid Email!!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
        return builder.create();
    }
}
