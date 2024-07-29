package com.example.snapchatclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class listoffriends extends AppCompatActivity {

    ListView name;
    ArrayList<String> arrayList;
    ArrayList<String>uid;
    ArrayAdapter arrayAdapter;
    DatabaseReference mref;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listoffriends);
        img=findViewById(R.id.imageView4);
        name=findViewById(R.id.list);
        arrayList=new ArrayList<>();
        uid=new ArrayList<>();
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        name.setAdapter(arrayAdapter);
        Intent intent=getIntent();
        byte[]byteArray=intent.getByteArrayExtra("image");
        if (byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img.setImageBitmap(bitmap);
            Toast.makeText(this, "avataar changed", Toast.LENGTH_SHORT).show();
        }

        mref= FirebaseDatabase.getInstance().getReference().child("users");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("snapshot",snapshot.toString());
                arrayList.clear();
                uid.clear();
                for(DataSnapshot i:snapshot.getChildren()){  //snapshot=users->child->uid->child->name
                    String name=i.child("name").getValue().toString();;
                    uid.add(i.getKey());
                    arrayList.add(name);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=getIntent();
                String url=intent.getStringExtra("url");
                String text=intent.getStringExtra("text");
                String imagename=intent.getStringExtra("imagename");
                 DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name");
                 databaseReference.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         String from = snapshot.getValue(String.class);

                         HashMap<String,String> hashMap=new HashMap<>();
                         hashMap.put("url",url);
                         hashMap.put("text",text);
                         hashMap.put("imagename",imagename);
                         hashMap.put("from",from);

                         FirebaseDatabase.getInstance().getReference().child("users").child(uid.get(position)) //push add a random key
                                 .child("snaps").push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if(task.isSuccessful()){
                                             Toast.makeText(listoffriends.this, "snap sent", Toast.LENGTH_SHORT).show();
                                         }
                                         else {
                                             Toast.makeText(listoffriends.this, "Failed to sent snap", Toast.LENGTH_SHORT).show();
                                         }
                                     }
                                 });

                         hashMap.clear();
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });


            }
        });
    }
    public  void seesnap(View view){
        Intent intent=new Intent(listoffriends.this,main.class);
        startActivity(intent);
    }



}