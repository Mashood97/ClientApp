package com.example.okcomputers.clientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    String value;
    double dLong;
    double dLat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                TextView textView = (TextView) findViewById(R.id.locText);
                textView.setText(value);
                String[] separated = value.split(",");
                String latiPos = separated[0].trim();
                String longiPos = separated[1].trim();
                dLat  = Double.parseDouble(latiPos);
                dLong  = Double.parseDouble(longiPos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void showLocation()
    {
        Intent i = new Intent(MainActivity.this,MapsActivity.class);
        i.putExtra("LOCVAL",value);
        startActivity(i);
    }
}