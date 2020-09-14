package com.example.mytherapy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class doctordialog extends AppCompatDialogFragment implements View.OnClickListener {
    private Button doctor,nearbydoctor;
    Intent intent;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        final View view=layoutInflater.inflate(R.layout.doctordialog,null);
        builder.setView(view);
        doctor=view.findViewById(R.id.doctordialog);
        nearbydoctor=view.findViewById(R.id.nearbydoctordialog);
        doctor.setOnClickListener(this);
        nearbydoctor.setOnClickListener(this);
        return builder.create();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doctordialog:
                intent = new Intent(getActivity().getApplication(), doctor.class);
                startActivity(intent);
                break;
            case R.id.nearbydoctordialog:
                intent = new Intent(getActivity().getApplication(), nearbydoctor1.class);
                startActivity(intent);
                break;
        }
    }
}
