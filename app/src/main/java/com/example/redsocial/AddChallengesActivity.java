package com.example.redsocial;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redsocial.Model.Anonymous;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddChallengesActivity extends AppCompatActivity {

    EditText anonymousID,Pin,level,description;
    Button insert;
    FirebaseDatabase database;
    DatabaseReference ref;
    Anonymous anonymous;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenges);

        anonymousID = (EditText) findViewById(R.id.anonymousID);
        Pin = (EditText) findViewById(R.id.Pin);
        level = (EditText) findViewById(R.id.level);
        description = (EditText) findViewById(R.id.description);
        insert = (Button) findViewById(R.id.btnInsert);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Anonymous");
        anonymous = new Anonymous();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                ref.child(String.valueOf(maxid+1)).setValue(anonymous);
                Toast.makeText(AddChallengesActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();

            }
        });
    }
    private  void getValues(){
        anonymous.setAnonymousID(anonymousID.getText().toString());
        anonymous.setPin(Pin.getText().toString());
        anonymous.setLevel(level.getText().toString());
        anonymous.setDescription(description.getText().toString());

    }


}
