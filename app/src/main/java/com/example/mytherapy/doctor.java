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
import com.google.android.gms.vision.text.Line;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doctor extends AppCompatActivity {
private FirebaseRecyclerOptions<model>options;
private RecyclerView recyclerView;
private DatabaseReference ref;
private FirebaseRecyclerAdapter<model,MyViewHolder>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        recyclerView=(RecyclerView)findViewById(R.id.doctorrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref= FirebaseDatabase.getInstance().getReference("DoctorsDetails");
        options=new FirebaseRecyclerOptions.Builder<model>().setQuery(ref,model.class).build();
        adapter=new FirebaseRecyclerAdapter<model, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull model model) {
                holder.name.setText("Doctor Name:"+model.getUsername());
                holder.clinic_name.setText("Clinic Name:"+model.getClinic_Name());
                holder.specilization.setText("Specialization:"+model.getSpecialization());
                holder.experience.setText("Experience:"+model.getExperience());
                holder.email.setText("Email Id:"+model.getEmail());

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card_view,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
