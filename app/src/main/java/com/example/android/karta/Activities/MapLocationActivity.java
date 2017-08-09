package com.example.android.karta.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Models.LocationU;
import com.example.android.karta.Models.Response.GenericResponse;
import com.example.android.karta.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapLocationActivity extends AppCompatActivity implements OnMapReadyCallback,
        LocationListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener{

    private MapView mapView;
    private GoogleMap gMap;
    private Marker marker;
    private CameraPosition camera;
    private LocationManager locationManager;
    private Location currentLocation;
    private Geocoder geocoder;

    int id_location, id_client;
    Boolean option;
    String name, references, address;
    Double lat, lng;

    //LatLong
    LatLng latlngLocation;


    /*Layout elements*/
    TextView txtLocationName, txtLocationReference, txtLocationAddress;
    Button btnSaveLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);

        /*Cast elements*/

        txtLocationName = (TextView) findViewById(R.id.txtLocationName);
        txtLocationReference = (TextView) findViewById(R.id.txtLocationReference);
        txtLocationAddress = (TextView) findViewById(R.id.txtLocationAddress);

        /*Cast btns */
        btnSaveLocation = (Button) findViewById(R.id.btnSaveLocation);


        /*Bundle extras according to option*/
        //-> #1:true to create a new LocationU
        //->#2:false to see detail location

        Bundle extras = getIntent().getExtras();

        option = extras.getBoolean("option");
        id_client = extras.getInt("id_client");

        if(!option){
            id_location = extras.getInt("id_location");
            name = extras.getString("name");
            lng = extras.getDouble("lng");
            lat = extras.getDouble("lat");
            references = extras.getString("ref");
            address = extras.getString("address");

            /*Initialize elements with intent information*/

            txtLocationName.setText(name);
            txtLocationReference.setText(references);
            txtLocationAddress.setText(address);

            latlngLocation = new LatLng(lat, lng);
        }
        //latlngLocation = new LatLng(21.15824135109709, -86.82823435810246);


        /*Tool bar config*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/

        mapView = (MapView) findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        /*Save location onclic*/

        btnSaveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**Create a new object with location ifo**/

                String newName = txtLocationName.getText().toString();
                String newRef = txtLocationReference.getText().toString();
                String newAddress = txtLocationAddress.getText().toString();



                //if option is to create a new location
                if(option) {
                    LocationU locationU = new LocationU(0,id_client, newName, lat, lng, newRef, newAddress);
                    newLocation(locationU);
                }
                //else option is to update a location
                else{
                    LocationU locationU = new LocationU(id_location,id_client, newName, lat, lng, newRef, newAddress);
                    updateLocation(locationU);
                }



            }
        });




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

        gMap = googleMap;

        if(option) {
            locationManager = (LocationManager) MapLocationActivity.this.getSystemService(LOCATION_SERVICE);
            // Add a marker in Sydney and move the camera
            //LatLng sydney = new LatLng(-34, 151);
            //marker = gMap.addMarker(new MarkerOptions().position(latlngLocation).title("Marker in Sydney").draggable(true));

            if (ActivityCompat.checkSelfPermission(MapLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }


            /*******************************************************/

            //locationByGPS();

            /******************************************************/

        }
        else{

            marker = gMap.addMarker(new MarkerOptions().position(latlngLocation).draggable(true));

            // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latlngLocation)      // Sets the center of the map to Mountain View
                    .zoom(17)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            gMap.setOnMarkerDragListener(this);

        }

        gMap.setOnMapLongClickListener(this);
    }


    public void locationByGPS(){
        if (!this.isGPSEnabled()) {
            showInfoAlert();
        } else {
            if (ActivityCompat.checkSelfPermission(MapLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                Toast.makeText(this, "LocationU = null", Toast.LENGTH_SHORT).show();
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            currentLocation = location;

            //Toast.makeText(this, ""+ currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();

            if (currentLocation != null) {
                Toast.makeText(this, "LocationU success", Toast.LENGTH_SHORT).show();
                createOrUpdateMarkerByLocation(location);
                zoomToLocation(location);
            }

        }
    }


    /**/

    //Verify if is enabled the GPS
    private boolean isGPSEnabled() {
        try {
            int gpsSignal = Settings.Secure.getInt(MapLocationActivity.this.getContentResolver(), Settings.Secure.LOCATION_MODE);

            if (gpsSignal == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showInfoAlert() {
        new AlertDialog.Builder(MapLocationActivity.this)
                .setTitle("GPS Signal")
                .setMessage("You don't have GPS signal enabled. Would you like to enable the GPS signal now?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    /*Set new location settings*/


    /***************CREATE OR UPDATE A MARKER********************/

    private void createOrUpdateMarkerByLocation(Location location) {

        String addressFromMarker;



        if (marker == null) {
            marker = gMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).draggable(true));
            //addressFromMarker = getAddress(new LatLng(location.getAltitude(),location.getAltitude()));
            //txtLocationAddress.setText(addressFromMarker);
            //Toast.makeText(this, ""+addressFromMarker, Toast.LENGTH_SHORT).show();
        } else {
            marker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            //addressFromMarker = getAddress(new LatLng(location.getAltitude(),location.getAltitude()));
            //txtLocationAddress.setText(addressFromMarker);
            //Toast.makeText(this, ""+addressFromMarker, Toast.LENGTH_SHORT).show();
        }

        address = getAddress(new LatLng(location.getAltitude(),location.getAltitude()));
        Toast.makeText(this, "CreateMarker = "+location.getLongitude(), Toast.LENGTH_SHORT).show();
        txtLocationAddress.setText(address);
        gMap.setOnMarkerDragListener(this);

    }

    /****************Get Address from Lat and Lng **********************/

    private String getAddress(LatLng latLng){


        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        String customAddress = "";

        /**********************************/
        //Obtain the info about the Lat and Lng
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            /**Get the address form geocoder**/
            customAddress = addresses.get(0).getSubLocality() + " " + addresses.get(0).getAddressLine(0) +
                    ", "+
                    addresses.get(0).getLocality() +
                    ", "+
                    addresses.get(0).getAdminArea(); //+ addresses.get(0).getSubAdminArea();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  customAddress;

    }

    /****************************************************/

    private void zoomToLocation(Location location) {
        camera = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(16)           // limit -> 21
                .build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

    }


    /***********************LOCATION LISTENER FOR GPS LOCATION****************************/
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Changed! -> " + location.getProvider(), Toast.LENGTH_LONG).show();
        createOrUpdateMarkerByLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    /**********************MARKER DRAG LISTENER**************************/
    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }


    /*Change the Address when Marker Drag Ends*/
    @Override
    public void onMarkerDragEnd(Marker marker) {

        String addressFromMarker;

        address = getAddress(new LatLng(marker.getPosition().latitude,marker.getPosition().longitude));
        lat = marker.getPosition().latitude;
        lng = marker.getPosition().longitude;

        txtLocationAddress.setText(address);
        //zoomToLocation();
        Toast.makeText(MapLocationActivity.this, ""+marker.getPosition().latitude +
                "," +
                marker.getPosition().longitude, Toast.LENGTH_SHORT).show();

    }

    /**When user do long click on map**/

    @Override
    public void onMapLongClick(LatLng latLng) {



        if (marker == null) {

            marker = gMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        } else {
            marker.setPosition(latLng);
        }

        address = getAddress(latLng);
        txtLocationAddress.setText(address);
    }


    /****** Create a new LocationU ******/

    public void newLocation(LocationU locationU){

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<GenericResponse> createLocation = service.newLocation(locationU, id_client);

        createLocation.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {

                if(response.isSuccessful()){

                    GenericResponse resp = response.body();

                    int code = resp.getCode();

                    if(code == 200){
                        Toast.makeText(MapLocationActivity.this, "Location Created", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MapLocationActivity.this, "Error creating Location", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {

            }
        });


    }


    /****** Update a LocationU ********/

    public void updateLocation(LocationU locationU){

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<GenericResponse> updateLocation = service.updateLocation(locationU, id_client, id_location);

        updateLocation.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {

                if(response.isSuccessful()){

                    GenericResponse resp = response.body();

                    int code = resp.getCode();

                    if(code == 200){
                        Toast.makeText(MapLocationActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MapLocationActivity.this, "Error updating the Location", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {

            }
        });

    }


}
