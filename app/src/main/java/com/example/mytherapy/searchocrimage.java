package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class searchocrimage extends AppCompatActivity {
    private FirebaseRecyclerOptions<searchmedname> options;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private EditText text;
    private ImageView button;
    private FirebaseRecyclerAdapter<searchmedname,searchmdnameviewholder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchocrimage);
        recyclerView=(RecyclerView)findViewById(R.id.searchocrrecylerview);
       text=(EditText) findViewById(R.id.text_view1);
        button = (ImageView) findViewById(R.id.searchocrbutton);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref= FirebaseDatabase.getInstance().getReference("Medicine");
        Intent intent=getIntent();
        text.setText(intent.getStringExtra("search").trim());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search=text.getText().toString().trim();
                firebasemedicinesearch(search);
            }
        });

    }
    private void firebasemedicinesearch(String search) {
        Query firebasesearchquery=ref.orderByChild("Name").startAt(search).endAt(search+"\uf8ff");
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