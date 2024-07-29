package com.example.snapchatclone;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class findingsnaps extends AppCompatActivity {
 TextView textView;
    String key;
 ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findingsnaps);
        textView=findViewById(R.id.txt);
        imageView=findViewById(R.id.img);
        Intent intent=getIntent();
        String name=intent.getStringExtra("text");
        String image=intent.getStringExtra("image");
         key=intent.getStringExtra("key");
        textView.setText(name);
        Picasso.get().load(image).into(imageView);
    }
    public void back(View view){
        FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("snaps").child(key).removeValue();
    Intent intent =new Intent(findingsnaps.this,main.class);
    startActivity(intent);
    }



}