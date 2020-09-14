package com.example.mytherapy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.squareup.picasso.Picasso;

public class Report extends AppCompatActivity implements OnClickListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private FirebaseAuth firebaseAuth;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    /* access modifiers changed from: private */
    public DatabaseReference mDatabaseRef;
    /* access modifiers changed from: private */
    public EditText mEditTextFileName;
    private Uri mImageUri;
    private ImageView mImageView;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private StorageReference mStorageRef;
    private TextView mTextViewShowUploads;
    private StorageTask mUploadTask;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        this.mButtonChooseImage = (Button) findViewById(R.id.button_choose_image);
        this.mButtonUpload = (Button) findViewById(R.id.button_upload);
        this.mTextViewShowUploads = (TextView) findViewById(R.id.text_view_show_uploads);
        this.mEditTextFileName = (EditText) findViewById(R.id.edit_text_file_name);
        this.mImageView = (ImageView) findViewById(R.id.image_view);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mButtonChooseImage.setOnClickListener(this);
        this.mTextViewShowUploads.setOnClickListener(this);
        this.mButtonUpload.setOnClickListener(this);
        this.firebaseAuth = FirebaseAuth.getInstance();
        String str = "uploads";
        this.mStorageRef = FirebaseStorage.getInstance().getReference(str);
        this.mDatabaseRef = FirebaseDatabase.getInstance().getReference(str);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_choose_image /*2131165272*/:
                openFileChooser();
                return;
            case R.id.button_upload /*2131165273*/:
                StorageTask storageTask = this.mUploadTask;
                if (storageTask == null || !storageTask.isInProgress()) {
                    uploadFile();
                    return;
                } else {
                    Toast.makeText(this, "Upload in progress", Toast.LENGTH_LONG).show();
                    return;
                }
            case R.id.text_view_show_uploads /*2131165477*/:
                openImagesActivity();
                return;
            default:
                return;
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(intent, 1);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null && data.getData() != null) {
            this.mImageUri = data.getData();
            Picasso.get().load(this.mImageUri).into(this.mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }

    private void uploadFile() {
        if (this.mImageUri != null) {
            StorageReference storageReference = this.mStorageRef;
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append(".");
            sb.append(getFileExtension(this.mImageUri));
            this.mUploadTask = storageReference.child(sb.toString()).putFile(this.mImageUri).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<TaskSnapshot>() {
                public void onSuccess(TaskSnapshot taskSnapshot) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            Report.this.mProgressBar.setProgress(0);
                        }
                    }, 500);
                    Toast.makeText(Report.this, "Upload successful",Toast.LENGTH_LONG).show();
                    Report.this.mDatabaseRef.child(Report.this.mDatabaseRef.push().getKey()).setValue(new Upload(Report.this.mEditTextFileName.getText().toString().trim(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString()));
                }
            }).addOnFailureListener((OnFailureListener) new OnFailureListener() {
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Report.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener((OnProgressListener) new OnProgressListener<TaskSnapshot>() {
                public void onProgress(TaskSnapshot taskSnapshot) {
                    Report.this.mProgressBar.setProgress((int) ((((double) taskSnapshot.getBytesTransferred()) * 100.0d) / ((double) taskSnapshot.getTotalByteCount())));
                }
            });
            return;
        }
        Toast.makeText(this, "No file selected",Toast.LENGTH_LONG).show();
    }

    private void openImagesActivity() {
        startActivity(new Intent(this, ImageActivity.class));
    }
}
