package com.example.snapchatclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class signuppage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edtmail;
    EditText edtpass,first,second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        edtmail=findViewById(R.id.mail);
        edtpass=findViewById(R.id.pass);
        first=findViewById(R.id.firstname);
        second=findViewById(R.id.secondname);
        mAuth = FirebaseAuth.getInstance();
    }
    public void signup(View view){
        mAuth.createUserWithEmailAndPassword(edtmail.getText().toString(),edtpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signuppage.this, "signup succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(signuppage.this,main.class);
                            addtodatabase();
                            startActivity(intent);
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Signup failed";
                            Toast.makeText(signuppage.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void signup3(View view){
        Intent intent=new Intent(signuppage.this,MainActivity.class);
        startActivity(intent);
    }
    public void addtodatabase(){
        String uid=mAuth.getUid();
        FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("name").setValue(first.getText().toString()+" "+second.getText().toString());
    }
}