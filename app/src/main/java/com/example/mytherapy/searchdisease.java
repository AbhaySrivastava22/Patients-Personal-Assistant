package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class searchdisease extends AppCompatActivity {
    private EditText medicinename;
    private ImageView button;
    private FirebaseRecyclerOptions<searchmedname> options;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private FirebaseRecyclerAdapter<searchmedname,searchmdnameviewholder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdisease);
        medicinename=(EditText)findViewById(R.id.searchmeddiseasebyname);
        button=(ImageView)findViewById(R.id.searchmeddiseasebynamebutton);
        recyclerView=(RecyclerView)findViewById(R.id.searchdisrecylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref= FirebaseDatabase.getInstance().getReference("Medicine");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search=medicinename.getText().toString();
                firebasemedicinesearch(search);
            }
        });
    }
    private void firebasemedicinesearch(String search) {
        Query firebasesearchquery=ref.orderByChild("Disease").startAt(search).endAt(search+"\uf8ff");
        options=new FirebaseRecyclerOptions.Builder<searchmedname>().setQuery(firebasesearchquery,searchmedname.class).build();
        adapter=new FirebaseRecyclerAdapter<searchmedname,searchmdnameviewholder>(options)
        {

            @NonNull
            @Override
            public searchmdnameviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchlayout,parent,false);
                return new searchmdnameviewholder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull searchmdnameviewholder holder, int position, @NonNull searchmedname model) {
                holder.medicinename.setText("Medicine Name:"+model.getName());
                holder.diseasename.setText("Disease Name:"+model.getDisease());
                holder.cardsalt.setText("Salt Composition:"+model.getChemical_Composition());

            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
