package com.example.mytherapy;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytherapy.ImageAdapter.OnItemClickListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity implements OnItemClickListener {
    /* access modifiers changed from: private */
    public ImageAdapter mAdapter;
    private ValueEventListener mDBListener;
    /* access modifiers changed from: private */
    public DatabaseReference mDatabaseRef;
    /* access modifiers changed from: private */
    public ProgressBar mProgressCircle;
    private RecyclerView mRecyclerView;
    private FirebaseStorage mStorage;
    /* access modifiers changed from: private */
    public List<Upload> mUploads;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle);
        this.mUploads = new ArrayList();
        this.mAdapter = new ImageAdapter(this, this.mUploads);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
        this.mStorage = FirebaseStorage.getInstance();
        this.mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        this.mDBListener = this.mDatabaseRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                ImageActivity.this.mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = (Upload) postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    ImageActivity.this.mUploads.add(upload);
                }
                ImageActivity.this.mAdapter.notifyDataSetChanged();
                ImageActivity.this.mProgressCircle.setVisibility(View.GONE);
            }

            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImageActivity.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                ImageActivity.this.mProgressCircle.setVisibility(View.GONE);
            }
        });
    }

    public void onItemClick(int position) {
        StringBuilder sb = new StringBuilder();
        sb.append("Normal click at position: ");
        sb.append(position);
        Toast.makeText(this, sb.toString(),Toast.LENGTH_SHORT).show();
    }

    public void onWhatEverClick(int position) {
        StringBuilder sb = new StringBuilder();
        sb.append("Whatever click at position: ");
        sb.append(position);
        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onDeleteClick(int position) {
        Upload selectedItem = (Upload) this.mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        this.mStorage.getReference().child(selectedItem.getImageUrl()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            public void onSuccess(Void aVoid) {
                ImageActivity.this.mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ImageActivity.this, "Item deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mDatabaseRef.removeEventListener(this.mDBListener);
    }
}
