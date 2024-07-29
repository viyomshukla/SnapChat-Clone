package com.example.snapchatclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class snap extends AppCompatActivity {
static public  int picknumber=100;
ImageView imageView;
    Uri imageUri;
    StorageReference storageRef ;
    UUID uuid=UUID.randomUUID();
    EditText textView;
Button upload ,select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap);
        imageView=findViewById(R.id.imageView2);
        upload=findViewById(R.id.btn);
        textView=findViewById(R.id.edt);
        select=findViewById(R.id.button2);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadfile();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null) {

                    upload3();
                }
                else {
                    Toast.makeText(snap.this, "please upload the image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void upload(View view){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,picknumber);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("requestcode",Integer.toString(requestCode));
        Log.i("resultcode",Integer.toString(resultCode));
        Log.i("data",data.toString());
        if(requestCode == picknumber && resultCode == RESULT_OK && data != null && data.getData() != null)
            {
                imageUri = data.getData();
            imageView.setImageURI(imageUri);
            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Image Not selected", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public  void uploadfile(){

        if (imageUri != null) {
            storageRef = FirebaseStorage.getInstance().getReference().child("images/" + uuid + ".jpg");
            storageRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(snap.this, "Upload success", Toast.LENGTH_SHORT).show();
                            textView.setVisibility(View.VISIBLE);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(snap.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(snap.this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void upload3() {
            storageRef = FirebaseStorage.getInstance().getReference().child("images/" + uuid + ".jpg");
            storageRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           storageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                               @Override
                               public void onComplete(@NonNull Task<Uri> task) {
                                   if(task.isSuccessful()) {
                                       String url = task.getResult().toString();
                                       Log.i("url", url);
                                       Intent intent = new Intent(snap.this, listoffriends.class);
                                       intent.putExtra("url", url);
                                       intent.putExtra("text", textView.getText().toString());
                                       intent.putExtra("imagename", uuid.toString());
                                       startActivity(intent);
                                   }else{
                                       Toast.makeText(snap.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

    }

}