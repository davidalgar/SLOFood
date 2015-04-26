package me.algar.slofood;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity {

    public static final String EXTRA_GPS = "gps_coords";
    public static final String EXTRA_TRUCK_NAME = "truck_name";
    public static final String EXTRA_SCHEDULE = "schedule";

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private String mGps;
    private String mTruckName;
    private String mSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mGps = getIntent().getStringExtra(EXTRA_GPS);
        mTruckName = getIntent().getStringExtra(EXTRA_TRUCK_NAME);
        mSchedule = getIntent().getStringExtra(EXTRA_SCHEDULE);

        if(mGps != null) {
            Toast.makeText(this, "GPS: " + mGps, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Truck Name: " + mTruckName, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Schedule: " + mSchedule, Toast.LENGTH_SHORT).show();
        }

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                addTruckToMap();
            }
        }
    }

    private void addTruckToMap(){
        float lat = 35.2460177f;
        float lng = -120.640035f;
        if(mGps != null) {
            String[] coords = mGps.split(",");
            lat = Float.parseFloat(coords[0]);
            lng = Float.parseFloat(coords[1]);
        }
        LatLng coords = new LatLng(lat, lng);

        if(mTruckName != null) {
            mMap.addMarker(new MarkerOptions().position(coords).title(mTruckName + " - " + mSchedule));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coords, 15.0f));
    }
}
