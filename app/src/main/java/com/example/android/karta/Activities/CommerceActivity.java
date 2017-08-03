package com.example.android.karta.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Adapters.AdapterProduct;
import com.example.android.karta.Adapters.Pager;
import com.example.android.karta.Models.Product;
import com.example.android.karta.Models.Response.ProductResponse;
import com.example.android.karta.Models.ServiceResponse;
import com.example.android.karta.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommerceActivity extends AppCompatActivity{

    RecyclerView rv;
    AdapterProduct adapter;
    List<Product> products;
    public static int id_commerce;
    String name, img;
    ImageView logoCommerce;

    //Tablayout
    TabLayout tabCategories;
    //ViewPager
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce);

        /*Cast Elements*/
        logoCommerce = (ImageView) findViewById(R.id.imgCommerce);

        Bundle extras = getIntent().getExtras();
        id_commerce = extras.getInt("id_commerce");
        name = extras.getString("name");
        img = extras.getString("img");



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        /*Initialize Tab with products categories*/
        tabCategories = (TabLayout) findViewById(R.id.productsTabLayout);

        /*Set categories*/
        tabCategories.addTab(tabCategories.newTab().setText("Platillos"));
        tabCategories.addTab(tabCategories.newTab().setText("Complementos"));
        tabCategories.addTab(tabCategories.newTab().setText("Postres"));
        tabCategories.addTab(tabCategories.newTab().setText("Bebidas"));

        tabCategories.setTabGravity(TabLayout.GRAVITY_FILL);


        //Initializae pager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabCategories.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabCategories));

        //Adding onTabSelectedListener to swipe views
        tabCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        Glide.with(this).load(img).into(logoCommerce);
        this.setTitle(name);

    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.info_commerce, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.more_info_commerce) {
            // do something here
        }
        return super.onOptionsItemSelected(item);
    }
}
