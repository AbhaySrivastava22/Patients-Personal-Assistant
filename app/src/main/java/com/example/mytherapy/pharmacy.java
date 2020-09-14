package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class pharmacy extends AppCompatActivity {
    private FirebaseRecyclerOptions<pharmacymodel> options;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private FirebaseRecyclerAdapter<pharmacymodel,PharmacyViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);
        recyclerView=(RecyclerView)findViewById(R.id.pharmacyrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref= FirebaseDatabase.getInstance().getReference("PharmacyDetails");
        options=new FirebaseRecyclerOptions.Builder<pharmacymodel>().setQuery(ref,pharmacymodel.class).build();
        adapter= new FirebaseRecyclerAdapter<pharmacymodel, PharmacyViewHolder>(options) {
            @NonNull
            @Override
            public PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacylayout,parent,false);
                return new PharmacyViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull PharmacyViewHolder holder, int position, @NonNull pharmacymodel model) {
                holder.chemist.setText("Chemist :"+model.getUsername());
                holder.phname.setText("Pharmacy Name :"+model.getPharmacy_name());
                holder.address.setText("Address :"+model.getAddress());
                holder.emailid.setText("Email Id :"+model.getEmail());
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
