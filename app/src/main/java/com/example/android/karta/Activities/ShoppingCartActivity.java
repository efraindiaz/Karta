package com.example.android.karta.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.android.karta.Models.ProdOrd;
import com.example.android.karta.Models.ProdTest;
import com.example.android.karta.Models.Product;
import com.example.android.karta.Models.Response.GenericResponse;
import com.example.android.karta.Models.Response.LocationResponse;
import com.example.android.karta.R;
import com.google.gson.Gson;

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
    int id_commerce;
    int id_consumer_address;
    String items;
    ArrayList<LocationU> currentLoc = new ArrayList<>();
    ArrayList<ProdOrd> listITems = new ArrayList();

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
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        id_user = preferences.getInt("id_user", 0);
        id_commerce = getIntent().getExtras().getInt("id_commerce");
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
        total = adapter.calculateTotal();
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

        final Spinner spinnerLocations = (Spinner) dialog.findViewById(R.id.spinnerLocations);

        //Botones de dialogo
        Button btnAceptLocation = (Button) dialog.findViewById(R.id.btnAceptLocation);
        Button btnCancelLocation = (Button) dialog.findViewById(R.id.btnCancelLocation);

        List<String> comboLocations = new ArrayList<String>();
        //Llena el combobox con ubicaciones del usuario
        for(int i = 0; i < currentLoc.size(); i++){

            comboLocations.add(currentLoc.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, comboLocations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocations.setAdapter(adapter);

        //Crea una nueva orden
        btnAceptLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = spinnerLocations.getSelectedItemPosition();
                //Toast.makeText(ShoppingCartActivity.this, "Crar nuevo pedido", Toast.LENGTH_SHORT).show();
                generateOrder(index);
            }
        });

        btnCancelLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();

    }

    //Genera una nueva orden
    private void generateOrder(int index){
        id_consumer_address = currentLoc.get(index).getId_consumer_address();
        Log.d("ORDER", "id_addres: " + id_consumer_address);
        listITems = convertToArrayList(CommerceActivity.cart);
        items = new Gson().toJson(listITems);
        Log.d("ORDER", items);
        Log.d("ORDER", "id_commer: " + id_commerce);
        Log.d("ORDER", "id_user: " + id_user);
        Log.d("ORDER", "Total: " + total);
        Log.d("ORDER", "T_items: " + listITems.size());

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<GenericResponse> sendOrder = service.sendOrder(id_commerce,
                                                            id_commerce,
                                                            id_user,
                                                            total,
                                                            listITems.size(),
                                                            id_consumer_address,
                                                            items);

        sendOrder.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if(response.isSuccessful()){
                    GenericResponse resp = response.body();
                    int code = resp.getCode();
                    if(code == 200){
                        Toast.makeText(ShoppingCartActivity.this, "Location Created", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ShoppingCartActivity.this, "No fue codigo 200", Toast.LENGTH_SHORT).show();
                        Log.d("ORDER", resp.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {

            }
        });


    }

    //Convertir a arraylist
    private ArrayList<ProdOrd> convertToArrayList(ArrayList<Product> oldArray){
        ArrayList<ProdOrd> filter_items = new ArrayList<>();
        for(int i = 0; i < oldArray.size(); i++){
            filter_items.add(new ProdOrd(oldArray.get(i).getId_product(), oldArray.get(i).getQuantity()));
        }
        return filter_items;
    }

    //Obtiene las ubicaciones del usuario ne session
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
