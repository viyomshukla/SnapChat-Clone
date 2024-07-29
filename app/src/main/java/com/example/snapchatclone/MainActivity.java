package com.example.snapchatclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

 EditText edtname;
 EditText edtpass;

 ImageView imageView,imageView3;
 TextView forget,signup;
 CountDownTimer countDownTimer;
 Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtname=findViewById(R.id.edtemail);
        edtpass=findViewById(R.id.edtpass);
        imageView=findViewById(R.id.imageView);
        forget=findViewById(R.id.lin);
        signup=findViewById(R.id.signup_link);
        login=findViewById(R.id.login);
        imageView3=findViewById(R.id.imageView3);
        mAuth = FirebaseAuth.getInstance();

        countDownTimer=new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
           imageView3.setVisibility(View.INVISIBLE);
           imageView.setVisibility(View.VISIBLE);
           edtname.setVisibility(View.VISIBLE);
           edtpass.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            signup.setVisibility(View.VISIBLE);
            forget.setVisibility(View.VISIBLE);
            }
        };
        countDownTimer.start();
    }
    public void login(View view){
        mAuth.signInWithEmailAndPassword(edtname.getText().toString(),edtpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "login succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,main.class);
                            startActivity(intent);
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Login failed";
                            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signup2(View view){
        Intent intent=new Intent(MainActivity.this,signuppage.class);
        startActivity(intent);
    }
    public void  forget(View view){
        String email = edtname.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error sending password reset email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}