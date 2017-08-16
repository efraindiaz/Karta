package com.example.android.karta.Activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Adapters.AdapterCart;
import com.example.android.karta.Models.LocationU;
import com.example.android.karta.Models.ProdTest;
import com.example.android.karta.Models.Product;
import com.example.android.karta.Models.Response.LocationResponse;
import com.example.android.karta.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShoppingCartActivity extends AppCompatActivity {


    RecyclerView rv;
    AdapterCart adapter;

    Button btnContinue;


    private int id_user;

    ArrayList<LocationU> currentLoc = new ArrayList<>();

    /**Info cart detail**/
    public Double total;
    Double subTota = 0.0;
    Double delivery;
    int totalQuantity;

    public static TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        /*Initialize the id_user var*/

        id_user = 1;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnContinue = (Button) findViewById(R.id.btnContinue);

        //Bundle extras = getIntent().getExtras();
        //ArrayList<Product> items = extras.getParcelableArrayList("items");

        //Toast.makeText(this, ""+items.get(0).getName(), Toast.LENGTH_SHORT).show();

        rv = (RecyclerView) findViewById(R.id.recyclerCart);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterCart(CommerceActivity.cart);
        rv.setAdapter(adapter);

        /*Set the total*/
        txtTotal = (TextView) findViewById(R.id.txtTotal);

        txtTotal.setText("$"+adapter.calculateTotal()+" MXN");
        /*Clic listenner fot btn to continue, showing the dialog location*/

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserLocations(id_user);
            }
        });

    }


    /*

    we need to create an ArrayList of strings to pass the name of location
    the we need to get the index selected
    search the id_location according to the index
    and save the id in a variable


        ArrayList<String> comboLocations = new ArrayList<Strings>();
        ArrayAdapter<
     */

    public void dialogLocations(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_location_dialog);

        Spinner spinnerLocations = (Spinner) dialog.findViewById(R.id.spinnerLocations);

        List<String> comboLocations = new ArrayList<String>();

        for(int i = 0; i < currentLoc.size(); i++){

            comboLocations.add(currentLoc.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, comboLocations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocations.setAdapter(adapter);



        dialog.show();

    }

    private void getUserLocations(int id_user) {

        currentLoc.clear();

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<LocationResponse> locationCall = service.getLocations(id_user);

        locationCall.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {

                if(response.isSuccessful()){

                    LocationResponse locations = response.body();

                    currentLoc.addAll(locations.getDataLocationUs());
                    dialogLocations();

                }

            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {

            }
        });

    }

}
