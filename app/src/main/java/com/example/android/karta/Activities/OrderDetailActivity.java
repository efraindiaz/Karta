package com.example.android.karta.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Adapters.AdapterDetailOrder;
import com.example.android.karta.Models.Response.OrderDetailResponse;
import com.example.android.karta.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderDetailActivity extends AppCompatActivity {

    int id_commerce;
    int id_order;
    Double total;
    String date;
    String status;

    TextView txtDate;
    TextView txtStatus;
    TextView txtTotal;

    RecyclerView rv;
    AdapterDetailOrder adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        /*Get id order and id commerce from intent*/
        Bundle extras = getIntent().getExtras();
        id_commerce = extras.getInt("id_commerce");
        id_order = extras.getInt("id_order");
        total = extras.getDouble("total");
        date = extras.getString("date");
        status = extras.getString("status");

        txtDate = (TextView) findViewById(R.id.txtOrderDate);
        txtStatus = (TextView) findViewById(R.id.txtOrderStatus);
        txtTotal = (TextView) findViewById(R.id.txtOrderTotal);

        txtDate.setText(date);
        txtStatus.setText(status);
        txtTotal.setText("$"+total+" MXN");
        getOrderDetail(id_order);

    }

    private void getOrderDetail(int id_order) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<OrderDetailResponse> orderItems = service.getOrderDetail(id_order);

        orderItems.enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {

                if(response.isSuccessful()){

                    OrderDetailResponse orderDetail = response.body();

                    int code = orderDetail.getCode();

                    if(code == 200){
                        rv = (RecyclerView) findViewById(R.id.recyclerOrderDetail);
                        rv.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
                        adapter = new AdapterDetailOrder(orderDetail.getDataProducts());
                        rv.setAdapter(adapter);
                    }

                }
                else{
                    Toast.makeText(OrderDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {

            }
        });

    }
}
