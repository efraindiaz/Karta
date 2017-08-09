package com.example.android.karta.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Adapters.AdapterLocation;
import com.example.android.karta.Models.Response.LocationResponse;
import com.example.android.karta.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationsActivity extends AppCompatActivity {

    RecyclerView rv;
    AdapterLocation adapter;

    FloatingActionButton fabAddLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        /*Tool bar config*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        fabAddLocation = (FloatingActionButton) findViewById(R.id.fabAddLocation);


        fabAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationsActivity.this, MapLocationActivity.class);
                intent.putExtra("id_client", 1);
                intent.putExtra("option", true); //option for create a new LocationU
                startActivity(intent);
            }
        });


        getUserLocations(1);


    }

    private void getUserLocations(int id_user) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<LocationResponse> locationCall = service.getLocations(id_user);

        locationCall.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {

                if(response.isSuccessful()){

                    LocationResponse locations = response.body();

                    rv = (RecyclerView) findViewById(R.id.recyclerLocations);

                    rv.setLayoutManager(new LinearLayoutManager(LocationsActivity.this));

                    adapter = new AdapterLocation(locations.getDataLocationUs());

                    rv.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {

            }
        });

    }

}
