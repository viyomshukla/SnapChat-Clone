package com.example.snapchatclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class main extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayList<DataSnapshot> dataSnapshot;
    ArrayAdapter<String> arrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        dataSnapshot = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);




        FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getUid()).child("snaps")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arrayList.clear();
                        dataSnapshot.clear();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            String name = data.child("from").getValue(String.class);
                            dataSnapshot.add(data);
                            arrayList.add(name);
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle possible errors
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = dataSnapshot.get(position);
                Intent intent = new Intent(main.this, findingsnaps.class);
                intent.putExtra("text", snapshot.child("text").getValue(String.class));
                intent.putExtra("image", snapshot.child("url").getValue(String.class));
               intent.putExtra("key",snapshot.getKey());

                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.takesnap) {
            Intent intent = new Intent(main.this, snap.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(main.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish current activity to prevent going back to it
        } else if (item.getItemId()==R.id.avataar) {
            Intent intent = new Intent(main.this, Avatar.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
