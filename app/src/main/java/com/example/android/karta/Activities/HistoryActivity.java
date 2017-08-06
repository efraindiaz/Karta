package com.example.android.karta.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Adapters.AdapterOrder;
import com.example.android.karta.Models.Response.OrderResponse;
import com.example.android.karta.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView rv;
    AdapterOrder adapterOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getOrderList(3);

    }

    private void getOrderList(int id_user) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<OrderResponse> ordersCall = service.getOrders(id_user);

        ordersCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {

                if(response.isSuccessful()){

                    OrderResponse orders = response.body();

                    rv = (RecyclerView) findViewById(R.id.recyclerHistory);

                    rv.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

                    adapterOrder = new AdapterOrder(orders.getDataOrders());

                    rv.setAdapter(adapterOrder);

                }
                else{
                    Toast.makeText(HistoryActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });
    }
}
