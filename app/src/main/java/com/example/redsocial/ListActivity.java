package com.example.redsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.redsocial.Model.Anonymous;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_anonymous);
        new FirebaseUDC().readAnonymous(new FirebaseUDC.DataStatus() {
            @Override
            public void DataIsLoaded(List<Anonymous> anonymous, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,ListActivity.this,anonymous,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.anonymous_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_anonymous:
            startActivity(new Intent(this,NewAnonymousActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

