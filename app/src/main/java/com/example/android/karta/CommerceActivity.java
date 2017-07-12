package com.example.android.karta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Adapter;

import com.example.android.karta.Adapters.AdapterProduct;
import com.example.android.karta.Models.Product;

import java.util.ArrayList;
import java.util.List;

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


        rv = (RecyclerView) findViewById(R.id.recyclerProduct);

        rv.setLayoutManager(new LinearLayoutManager(this));

        products = new ArrayList<>();

        ArrayList<Product> test = new ArrayList<Product>();

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
        }

        adapter = new AdapterProduct(test);

        rv.setAdapter(adapter);


    }
}
