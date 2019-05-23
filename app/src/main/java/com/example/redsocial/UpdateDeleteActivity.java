package com.example.redsocial;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redsocial.Model.Anonymous;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteActivity extends AppCompatActivity {
    EditText pin,level,description;
    TextView key1;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        pin = (EditText) findViewById(R.id.Pin);
        level= (EditText) findViewById(R.id.level);
        description= (EditText) findViewById(R.id.description);
        String key = getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("Anonymous").child(key);

        key1 = (TextView) findViewById(R.id.key); key1.setText(key);
        pin.setText(getIntent().getStringExtra("pin"));
        level.setText(getIntent().getStringExtra("level"));
        description.setText(getIntent().getStringExtra("description"));
    }

    public void btnUpdate_Click(View view) {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dataSnapshot.getRef().child("pin").setValue(pin.getText().toString());
                    dataSnapshot.getRef().child("level").setValue(level.getText().toString());
                    dataSnapshot.getRef().child("description").setValue(description.getText().toString());
                    Toast.makeText(UpdateDeleteActivity.this,"Data Updated Succesfully",Toast.LENGTH_LONG).show();
                    UpdateDeleteActivity.this.finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }

    public void btnDelete_Click(View view) {
            ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(UpdateDeleteActivity.this,"Challenge Deleted",Toast.LENGTH_LONG).show();
                        UpdateDeleteActivity.this.finish();
                    }else {
                        Toast.makeText(UpdateDeleteActivity.this,"Challenge Not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}
