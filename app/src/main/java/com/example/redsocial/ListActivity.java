package com.example.redsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.redsocial.Model.Anonymous;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;
    private Button btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv= (ListView) findViewById(R.id.listView);
        btnnext = (Button) findViewById(R.id.next);

        Query query = FirebaseDatabase.getInstance().getReference().child("Anonymous");
        FirebaseListOptions<Anonymous> options = new FirebaseListOptions.Builder<Anonymous>()
                .setLayout(R.layout.anonymous)
                .setQuery(query,Anonymous.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView stdID = v.findViewById(R.id.anonymousID);
                TextView PIN=v.findViewById(R.id.Pin);
                TextView description=v.findViewById(R.id.description);
                TextView level=v.findViewById(R.id.level);

                Anonymous std = (Anonymous) model;
                stdID.setText("Player: "+std.getAnonymousID().toString());
                PIN.setText("PIN: "+std.getPin().toString());
                description.setText("Description: "+std.getDescription().toString());
                level.setText("Level: "+std.getLevel().toString());

            }
        };
         lv.setAdapter(adapter);
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent updataDelete = new Intent(ListActivity.this,UpdateDeleteActivity.class);
                 Anonymous a = (Anonymous) parent.getItemAtPosition(position);
                 updataDelete.putExtra("pin",a.getPin());
                 updataDelete.putExtra("level",a.getLevel());
                 updataDelete.putExtra("description",a.getDescription());
                 updataDelete.putExtra("key",a.getAnonymousID());
                 startActivity(updataDelete);
             }
         });

         btnnext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ListActivity.this,AddChallengesActivity.class);
                 startActivity(intent);
             }
         });
    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
