package com.example.snapchatclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Avatar extends AppCompatActivity {


    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        imageView1=findViewById(R.id.img1);
        imageView2 = findViewById(R.id.img2);
        imageView3 = findViewById(R.id.img3);
        imageView4=findViewById(R.id.img4);
        imageView5=findViewById(R.id.img5);
        imageView6 = findViewById(R.id.img6);
        imageView7 = findViewById(R.id.img7);
        imageView8=findViewById(R.id.img8);



    }

    public void avataar1(View view){
        BitmapDrawable drawable = (BitmapDrawable) imageView1.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
    public void avataar2(View view) {

        BitmapDrawable drawable = (BitmapDrawable) imageView2.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
    public void  avataar3(View view){
        BitmapDrawable drawable = (BitmapDrawable) imageView3.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
    public void avataar4(View view){
        BitmapDrawable drawable = (BitmapDrawable) imageView4.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
    public void avataar5(View view){
        BitmapDrawable drawable = (BitmapDrawable) imageView5.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
    public void avataar6(View view){
        BitmapDrawable drawable = (BitmapDrawable) imageView6.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
    public void avataar7(View view){
        BitmapDrawable drawable = (BitmapDrawable) imageView7.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
    public void avataar8(View view){
        BitmapDrawable drawable = (BitmapDrawable) imageView8.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(Avatar.this, listoffriends.class);
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
}