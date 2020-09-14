package com.example.mytherapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.KeyStore;

public class reminder extends AppCompatActivity  {
    private Button button;
    private FirebaseRecyclerOptions<reminderdata> options;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private FirebaseRecyclerAdapter<reminderdata,reminderviewholder>adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        button = (Button) findViewById(R.id.remadd);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        recyclerView=(RecyclerView)findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref= FirebaseDatabase.getInstance().getReference("Reminder").child(firebaseAuth.getCurrentUser().getUid());
        options=new FirebaseRecyclerOptions.Builder<reminderdata>().setQuery(ref,reminderdata.class).build();
        adapter=new FirebaseRecyclerAdapter<reminderdata, reminderviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull reminderviewholder holder, int position, @NonNull reminderdata model) {
                holder.title.setText("Title:"+model.getTitle());
                holder.des.setText(""+model.getDescription());
                holder.enddate.setText(""+model.getEnd_date());
            }

            @NonNull
            @Override
            public reminderviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewforreminders,parent,false);
                return new reminderviewholder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });

    }

    private void opendialog() {
        setreminder dialog=new setreminder();
        dialog.show(getSupportFragmentManager(),"SignUp Dialog");
    }
}
