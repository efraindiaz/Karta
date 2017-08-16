package com.example.android.karta.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Models.Response.CommerceResponse;
import com.example.android.karta.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailCommmerceActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private MapView mapView;
    private GoogleMap gMap;
    private Marker marker;

    int id_commerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_commmerce);

        Bundle extras = getIntent().getExtras();

        id_commerce = extras.getInt("id_commerce");

        getCommerce(id_commerce);



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

    private void getCommerce(int id_commerce) {


        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<CommerceResponse> commerceDetail = service.getCommerceDetail(id_commerce);

        commerceDetail.enqueue(new Callback<CommerceResponse>() {
            @Override
            public void onResponse(Call<CommerceResponse> call, Response<CommerceResponse> response) {


                if(response.isSuccessful()){

                    CommerceResponse commRes = response.body();

                    int code = commRes.getCode();

                    if(code == 200){


                    }

                }

            }

            @Override
            public void onFailure(Call<CommerceResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng comm1 = new LatLng(21.160400682805484, -86.82804107666016);

        mMap.addMarker(new MarkerOptions().position(comm1).title("Comercio 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red)));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(cancun));


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(comm1)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
