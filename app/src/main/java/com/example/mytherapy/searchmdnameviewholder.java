package com.example.mytherapy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class searchmdnameviewholder  extends RecyclerView.ViewHolder {
 TextView medicinename,diseasename,cardsalt;
    public searchmdnameviewholder(@NonNull View itemView) {
        super(itemView);
        medicinename=itemView.findViewById(R.id.cardmedname);
        diseasename=itemView.findViewById(R.id.carddiseasename);
        cardsalt=itemView.findViewById(R.id.cardsalt);

    }
}
