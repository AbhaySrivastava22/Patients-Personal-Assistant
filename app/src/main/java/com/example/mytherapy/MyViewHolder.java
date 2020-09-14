package com.example.mytherapy;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

class MyViewHolder extends ViewHolder {
     TextView name,clinic_name,experience,specilization,email;
     Button button;
    public MyViewHolder(@NonNull final View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.doctorcardname);
        clinic_name=itemView.findViewById(R.id.doctorcardclname);
        experience=itemView.findViewById(R.id.doctorcardclexp);
        specilization=itemView.findViewById(R.id.doctorcardsoec);
        email=itemView.findViewById(R.id.doctorcardemail);
        button=itemView.findViewById(R.id.takeappoinment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8423902029"));
                itemView.getContext().startActivity(intent);
                Toast.makeText(itemView.getContext(), "Appointment taken your appointment no is 1", Toast.LENGTH_LONG).show();
            }
        });


    }
}
