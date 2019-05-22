package com.example.redsocial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv= (ListView) findViewById(R.id.listView);

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
                ImageView image = v.findViewById(R.id.imageView);

                Anonymous std = (Anonymous) model;
                stdID.setText("Player: "+std.getAnonymousID().toString());
                PIN.setText("PIN: "+std.getPin().toString());
                description.setText("Description: "+std.getDescription().toString());
                level.setText("Level: "+std.getLevel().toString());
                Picasso.with(ListActivity.this).load(std.getImage().toString()).into(image);

            }
        };
         lv.setAdapter(adapter);
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
