package com.example.redsocial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redsocial.Model.Anonymous;

import java.util.List;

public class NewAnonymousActivity extends AppCompatActivity {

    private EditText mAnonymousID_editTxt;
    private EditText mPIN_editTxt;
    private EditText mLevel_editTxt;
    private EditText mDescription_editTxt;
    private Button mButton;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_anonymous);
        mAnonymousID_editTxt = (EditText) findViewById(R.id.anonymousid_edittext);
        mPIN_editTxt = (EditText) findViewById(R.id.pin_editText);
        mLevel_editTxt = (EditText) findViewById(R.id.level_editText);
        mDescription_editTxt = (EditText) findViewById(R.id.description_editText);

        mButton = (Button) findViewById(R.id.buttonupdate);
        mButton2 = (Button) findViewById(R.id.button2);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anonymous anonymous = new Anonymous();
                anonymous.setAnonymousID(mAnonymousID_editTxt.getText().toString());
                anonymous.setPin(mPIN_editTxt.getText().toString());
                anonymous.setLevel(mLevel_editTxt.getText().toString());
                anonymous.setDescription(mDescription_editTxt.getText().toString());
                new FirebaseUDC().addAnonymous(anonymous, new FirebaseUDC.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Anonymous> anonymous, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewAnonymousActivity.this,"The Game record has"+
                                "been inserted succesfully", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();return;
            }
        });
    }



}
