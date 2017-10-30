package com.example.android.karta.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.karta.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapView;
    private GoogleMap gMap;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
         /*Tool bar config*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mapView = (MapView) findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
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

        // Add a marker in Sydney and move the camera
        LatLng cancun = new LatLng(21.161691377430728, -86.83427453041077);

        LatLng comm1 = new LatLng(21.160400682805484, -86.82804107666016);

        LatLng comm2 = new LatLng(21.16217163302285, -86.82805180549622);

        LatLng comm3 = new LatLng(21.132733089139244,-86.85189664363861);


        //Crea marcadores para comercios
        //
        mMap.addMarker(new MarkerOptions().position(comm1).title("Comercio 1"));//.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red)));
        mMap.addMarker(new MarkerOptions().position(comm2).title("Comercio 2"));//.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red)));
        mMap.addMarker(new MarkerOptions().position(comm3).title("Comercio 3"));//.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(cancun));


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(cancun)      // Sets the center of the map to Mountain View
                .zoom(12)                   // Sets the zoom
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
