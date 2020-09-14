package com.example.mytherapy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class reminderviewholder extends RecyclerView.ViewHolder {
    TextView title,des,enddate;
    public reminderviewholder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.cardtitle);
        des=itemView.findViewById(R.id.carddes);
        enddate=itemView.findViewById(R.id.cardenddate);
    }
}
