package com.example.redsocial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redsocial.Model.Anonymous;

import java.util.List;

public class AnonymousDetailsActivity extends AppCompatActivity {

    private EditText mAnonymousID_editTxt;
    private EditText mPIN_editTxt;
    private EditText mLevel_editTxt;
    private EditText mDescription_editTxt;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String anonymousID;
    private String pin;
    private String level;
    private String description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous_details);

        key = getIntent().getStringExtra("key");
        anonymousID = getIntent().getStringExtra("anonymousID");
        pin = getIntent().getStringExtra("pin");
        level = getIntent().getStringExtra("level");
        description = getIntent().getStringExtra("description");

        mAnonymousID_editTxt = (EditText) findViewById(R.id.anonymousid_edittext);
        mAnonymousID_editTxt.setText(anonymousID);
        mPIN_editTxt = (EditText) findViewById(R.id.pin_editText);
        mPIN_editTxt.setText(pin);
        mLevel_editTxt = (EditText) findViewById(R.id.level_editText);
        mLevel_editTxt.setText(level);
        mDescription_editTxt = (EditText) findViewById(R.id.description_editText);
        mDescription_editTxt.setText(description);

        mUpdate_btn = (Button) findViewById(R.id.buttonupdate);
        mDelete_btn = (Button) findViewById(R.id.buttondelete);
        mBack_btn = (Button) findViewById(R.id.button2);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anonymous anonymous = new Anonymous();
                anonymous.setAnonymousID(mAnonymousID_editTxt.getText().toString());
                anonymous.setPin(mPIN_editTxt.getText().toString());
                anonymous.setLevel(mLevel_editTxt.getText().toString());
                anonymous.setDescription(mDescription_editTxt.getText().toString());

                new FirebaseUDC().updateAnonymous(key, anonymous, new FirebaseUDC.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Anonymous> anonymous, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(AnonymousDetailsActivity.this,"Anonymous record has been"+
                                "updated successfully",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new FirebaseUDC().deleteAnonymous(key, new FirebaseUDC.DataStatus() {
                   @Override
                   public void DataIsLoaded(List<Anonymous> anonymous, List<String> keys) {

                   }

                   @Override
                   public void DataIsInserted() {

                   }

                   @Override
                   public void DataIsUpdated() {

                   }

                   @Override
                   public void DataIsDeleted() {
                        Toast.makeText(AnonymousDetailsActivity.this,"Anonymous has been" +
                                "deleted successfully!",Toast.LENGTH_LONG).show();
                        finish();return;
                   }
               });
            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();return;
            }
        });
    }


}
