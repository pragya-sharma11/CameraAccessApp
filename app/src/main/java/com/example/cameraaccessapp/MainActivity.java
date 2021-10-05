package com.example.cameraaccessapp;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        i = findViewById(R.id.imageView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
    }

    @MainThread
    public boolean dispatchResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            i.setImageBitmap(bitmap);
        }
        else{
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
        return getActivityResultRegistry().dispatchResult(requestCode, resultCode, data);
    }
}