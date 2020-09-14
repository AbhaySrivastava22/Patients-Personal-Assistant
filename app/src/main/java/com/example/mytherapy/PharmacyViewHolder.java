package com.example.mytherapy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class PharmacyViewHolder extends RecyclerView.ViewHolder {
    TextView chemist,address,phname,emailid;
    public PharmacyViewHolder(@NonNull View itemView) {
        super(itemView);
        chemist=itemView.findViewById(R.id.pharmacycardchemist);
        address=itemView.findViewById(R.id.pharmacycardaddress);
        phname=itemView.findViewById(R.id.pharmacycardname);
        emailid=itemView.findViewById(R.id.pharmacyrcardemail);
    }
}
