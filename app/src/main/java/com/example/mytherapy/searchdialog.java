package com.example.mytherapy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class searchdialog extends AppCompatDialogFragment implements View.OnClickListener{
    private Button namemed, namedisease, imgwrapper;
    Intent intent;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        final View view=layoutInflater.inflate(R.layout.searchdialog,null);
        builder.setView(view);
        namemed=view.findViewById(R.id.searchnamemed);
        namedisease=view.findViewById(R.id.searchnamedisease);
        imgwrapper=view.findViewById(R.id.searchimgwrapper);
        namemed.setOnClickListener(this);
        namedisease.setOnClickListener(this);
        imgwrapper.setOnClickListener(this);
        return builder.create();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchnamemed:
                intent = new Intent(getActivity().getApplication(), searchmedicine.class);
                startActivity(intent);
                break;
            case R.id.searchnamedisease:
                intent = new Intent(getActivity().getApplication(), searchdisease.class);
                startActivity(intent);
                break;
            case R.id.searchimgwrapper:
                intent = new Intent(getActivity().getApplication(), searchocr.class);
                startActivity(intent);
                break;
        }
    }

}
