package com.example.redsocial;

import android.support.annotation.NonNull;

import com.example.redsocial.Model.Anonymous;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUDC {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceAnonymous;
    private List<Anonymous> anonymous = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Anonymous> anonymous,List<String>keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseUDC(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceAnonymous = mDatabase.getReference("Anonymous");

    }

    public void readAnonymous(final DataStatus dataStatus){
        mReferenceAnonymous.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                anonymous.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Anonymous anonymous1 = keyNode.getValue(Anonymous.class);
                    anonymous.add(anonymous1);
                }
                dataStatus.DataIsLoaded(anonymous,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addAnonymous(Anonymous anonymous,final DataStatus dataStatus){
        String key = mReferenceAnonymous.push().getKey();
        mReferenceAnonymous.child(key).setValue(anonymous)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void updateAnonymous(String key, Anonymous anonymous,final DataStatus dataStatus){
        mReferenceAnonymous.child(key).setValue(anonymous)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }
public void deleteAnonymous(String key, final DataStatus dataStatus ){
        mReferenceAnonymous.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
}
}
