package com.example.android.karta.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Activities.LocationsActivity;
import com.example.android.karta.Activities.MapLocationActivity;
import com.example.android.karta.Models.LocationU;
import com.example.android.karta.Models.Response.GenericResponse;
import com.example.android.karta.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Root on 07/08/2017.
 */

public class AdapterLocation extends RecyclerView.Adapter<AdapterLocation.LocationsViewHolder>{

    List<LocationU> locationUs;
    Context context;

    public AdapterLocation(List<LocationU> locationUs) {
        this.locationUs = locationUs;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_recycler, parent, false);

        LocationsViewHolder holder = new LocationsViewHolder(v);

        context = parent.getContext();

        return holder;
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {

        final LocationU locationU = locationUs.get(position);

        holder.txtLocation.setText(locationU.getName());
        holder.txtAddress.setText(locationU.getManual_address());

        /*Initialize variables to get detail locationU info*/
        final int elementPosition = position;

        final int id_location = locationU.getId_consumer_address();
        final int id_client = locationU.getId_info_user_consumer();
        final String name = locationU.getName();
        final Double lng = locationU.getLongitude();
        final Double lat = locationU.getLatitude();
        final String references = locationU.getReferences();
        final String address = locationU.getManual_address();

        //LocationU myLocation = new LocationU(locationU.);

        /*Click event for detail or update LocationU*/

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MapLocationActivity.class);
                intent.putExtra("option", false); //option to see detail locationU
                /*LocationU info to put extra*/

                intent.putExtra("id_location", id_location);
                intent.putExtra("id_client", id_client);
                intent.putExtra("name", name);
                intent.putExtra("lng", lng);
                intent.putExtra("lat", lat);
                intent.putExtra("ref", references);
                intent.putExtra("address", address);

                context.startActivity(intent);
            }
        });

        /*Clic event to delete lcoation*/

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoAlert(elementPosition, id_client, id_location);
            }
        });


    }

    @Override
    public int getItemCount() {
        return locationUs.size();
    }


    public static class LocationsViewHolder extends RecyclerView.ViewHolder{

        TextView txtLocation, txtAddress;
        Button btnDelete, btnDetail;

        public LocationsViewHolder(View itemView) {
            super(itemView);

            txtLocation = (TextView) itemView.findViewById(R.id.txtLocationName);
            txtAddress = (TextView) itemView.findViewById(R.id.txtLocationAddress);

            btnDelete = (Button) itemView.findViewById(R.id.btnDeleteLocation);
            btnDetail = (Button) itemView.findViewById(R.id.btnDetailLocation);

        }
    }

     /*Show alert to delete an Location*/

    private void showInfoAlert(final int position, final int id_user, final int id_location) {
        new AlertDialog.Builder(context)
                .setTitle("Atention")
                .setMessage("Are you sure to delete the Location?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteLocation(position, id_user, id_location);
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private void deleteLocation(final int position, int id_user, int id_location) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<GenericResponse> deleteResponse = service.deleteLocation(id_user, id_location);

        deleteResponse.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {

                if(response.isSuccessful()){

                    GenericResponse resp = response.body();

                    int code = resp.getCode();

                    if(code == 200){

                        Toast.makeText(context, "Delete OK", Toast.LENGTH_SHORT).show();
                        locationUs.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,locationUs.size());



                    }
                    else{
                        Toast.makeText(context, "Error deleting Location", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {

            }
        });
    }
}
