package com.example.redsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.varunest.sparkbutton.SparkButton;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private SparkButton msparkbutton;
    private SparkButton msparkbutton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        msparkbutton = findViewById(R.id.spark_button);
        msparkbutton.setOnClickListener(this);

        msparkbutton2 = findViewById(R.id.spark_button2);
        msparkbutton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.spark_button : Intent intent = new Intent(GameActivity.this,GameActivity2.class);
                startActivity(intent);
                break;

            case R.id.spark_button2 : Intent intent1 = new Intent(GameActivity.this,ListActivity.class);
            startActivity(intent1);
            break;
        }

    }
}
