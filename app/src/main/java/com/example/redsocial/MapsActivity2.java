package com.example.redsocial;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private ArrayList<Marker> tmpRealTimeMarker = new ArrayList<>();
    private ArrayList<Marker> realTimeMarker = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        countDownTimer();

    }


    private void countDownTimer(){

        new CountDownTimer(10000,1000){

            public void onTick(long millisUntilFinished) {
                Log.e("seconds remaining",""+millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                //.show() show us the toast with info
                Toast.makeText(MapsActivity2.this,"Updated Points",Toast.LENGTH_SHORT);
                onMapReady(mMap);
            }
        }.start();


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // new code
        LatLng coruna = new LatLng(43.3618688,-8.4476176);
        mMap.addMarker(new MarkerOptions().position(coruna).title("Coruña"));

        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(10).target(coruna).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mDatabase.child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (Marker marker:realTimeMarker){
                    marker.remove();
                }

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    MapsPojo mp = snapshot.getValue(MapsPojo.class);
                    Double latitud = mp.getLatitud();
                    Double longitud = mp.getLongitud();
                    //New Code
                    String title = mp.getTitle();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud,longitud));
                    tmpRealTimeMarker.add(mMap.addMarker(markerOptions.title(title)));

                }

                realTimeMarker.clear();
                realTimeMarker.addAll(tmpRealTimeMarker);
                countDownTimer();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
