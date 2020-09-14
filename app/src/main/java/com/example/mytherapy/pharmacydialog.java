package com.example.mytherapy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class pharmacydialog extends AppCompatDialogFragment implements View.OnClickListener {
    private Button pharmacy,nearbypharmacy;
    Intent intent;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        final View view=layoutInflater.inflate(R.layout.pharmacydialog,null);
        builder.setView(view);
        pharmacy=view.findViewById(R.id.pharmacydialog);
        nearbypharmacy=view.findViewById(R.id.nearbypharmacydialog);
        pharmacy.setOnClickListener(this);
        nearbypharmacy.setOnClickListener(this);
        return builder.create();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pharmacydialog:
                intent = new Intent(getActivity().getApplication(), pharmacy.class);
                startActivity(intent);
                break;
            case R.id.nearbypharmacydialog:
                intent = new Intent(getActivity().getApplication(), nearbypharmacy1.class);
                startActivity(intent);
                break;
        }
    }
}
