package com.example.mytherapy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class dialogbox extends AppCompatDialogFragment implements View.OnClickListener {
    private Button doctor, patient, pharmacy;
    Intent intent;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        final View view=layoutInflater.inflate(R.layout.signupdialog,null);
        builder.setView(view);
        doctor=view.findViewById(R.id.maindoctor);
        patient=view.findViewById(R.id.mainpatient);
        pharmacy=view.findViewById(R.id.mainpharmacy);
        doctor.setOnClickListener(this);
        patient.setOnClickListener(this);
        pharmacy.setOnClickListener(this);
        return builder.create();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.maindoctor:
                intent = new Intent(getActivity().getApplication(), doctorsignup.class);
                startActivity(intent);
                break;
            case R.id.mainpatient:
                intent = new Intent(getActivity().getApplication(), patientsignup.class);
                startActivity(intent);
                break;
            case R.id.mainpharmacy:
                intent = new Intent(getActivity().getApplication(), pharmacysignup.class);
                startActivity(intent);
                break;
        }
    }
}
