package com.example.mytherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.squareup.picasso.Picasso;

public class searchocr extends AppCompatActivity {
private ImageView imageView;
private Uri imageUri;
private Button button,choosebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchocr);
        imageView=(ImageView)findViewById(R.id.image_view);
        choosebutton=(Button)findViewById(R.id.choose_image);
        button=(Button)findViewById(R.id.findmedicine);
        choosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                startActivityForResult(intent, 1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable=(BitmapDrawable)imageView.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                TextRecognizer textRecognizer=new TextRecognizer.Builder(getApplicationContext()).build();
                if(!textRecognizer.isOperational())
                {
                    Toast.makeText(getApplicationContext(),"Could not get the text",Toast.LENGTH_LONG);
                }
                else
                {
                    Frame frame=new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items=textRecognizer.detect(frame);
                    StringBuilder stringBuilder=new StringBuilder();
                    for (int i=0;i<items.size();i++)
                    {
                        TextBlock myitem=items.valueAt(i);
                        stringBuilder.append(myitem.getValue());
                        stringBuilder.append("\n");
                    }
                    Intent intent=new Intent(searchocr.this,searchocrimage.class);
                    intent.putExtra("search",stringBuilder.toString());
                    startActivity(intent);
                }


            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null && data.getData() != null) {
             imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView);

        }
    }
}
