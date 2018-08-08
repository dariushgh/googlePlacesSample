package com.example.dariush.testplaceapi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class PlacePikerActivity extends AppCompatActivity {
    private int PLACE_PIKER_REQUEST = 1;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_piker);
        textView = findViewById(R.id.textView);



        ActivityCompat.requestPermissions(PlacePikerActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(PlacePikerActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(PlacePikerActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1);

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void goShowPlace(View view) {

        PlacePicker.IntentBuilder placePicker = new PlacePicker.IntentBuilder();

        try {

            startActivityForResult(placePicker.build(PlacePikerActivity.this), PLACE_PIKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {

            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PIKER_REQUEST) {

            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(PlacePikerActivity.this, data);
                textView.setText(place.getAddress());
            }

        }

    }
}
