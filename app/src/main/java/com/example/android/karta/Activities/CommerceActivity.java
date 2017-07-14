package com.example.android.karta.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.android.karta.API.Service;
import com.example.android.karta.Adapters.AdapterProduct;
import com.example.android.karta.Models.Product;
import com.example.android.karta.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommerceActivity extends AppCompatActivity {

    RecyclerView rv;
    AdapterProduct adapter;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getProductList();
        /*rv = (RecyclerView) findViewById(R.id.recyclerProduct);

        rv.setLayoutManager(new LinearLayoutManager(this));

        products = new ArrayList<>();*/

        /*ArrayList<Product> test = new ArrayList<Product>();

        for (int i = 0; i < 5; i ++ ) {

            test.add(new Product(

                    1,
                    2,
                    "nombre",
                    "descripcion",
                    "precio",
                    2,
                    "https://storage.googleapis.com/gweb-uniblog-publish-prod/static/blog/images/google-200x200.7714256da16f.png"
            ));
        }*/

        //adapter = new AdapterProduct(test);

        //rv.setAdapter(adapter);



    }

    private void getProductList() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://c12753cc.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<List<Product>> produtCall = service.getProductData();

        produtCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){

                    List<Product> product = response.body();

                    rv = (RecyclerView) findViewById(R.id.recyclerProduct);

                    rv.setLayoutManager(new LinearLayoutManager(CommerceActivity.this));

                    adapter = new AdapterProduct(product);

                    rv.setAdapter(adapter);



                    for(int i = 0; i < product.size(); i++){

                        Product p = product.get(i);

                        Toast.makeText(CommerceActivity.this, "Nombre: "+p.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });





    }

}
