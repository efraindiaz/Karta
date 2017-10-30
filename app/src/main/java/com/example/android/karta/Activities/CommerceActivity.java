package com.example.android.karta.Activities;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.bumptech.glide.Glide;
import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Adapters.AdapterProduct;
import com.example.android.karta.Adapters.Pager;
import com.example.android.karta.Models.ProdTest;
import com.example.android.karta.Models.Product;
import com.example.android.karta.Models.Response.CartResponse;
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
    public static ArrayList<Product> cart = new ArrayList<>(); //Array list to save products added in the cart

    /***/
    public static ArrayList<ProdTest> cartTest = new ArrayList<>();
    /***/
    public static int id_commerce;
    String name, img;
    ImageView logoCommerce;

    //Tablayout
    TabLayout tabCategories;
    //ViewPager
    ViewPager viewPager;

    //Counter FAB
    public static CounterFab counterFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce);

        /*Cast Elements*/
        logoCommerce = (ImageView) findViewById(R.id.imgCommerce);

        /*Get the info commerce of intent*/
        Bundle extras = getIntent().getExtras();
        id_commerce = extras.getInt("id_commerce");
        name = extras.getString("name");
        img = extras.getString("img");



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        /* Cast the counter FAB */
        counterFab = (CounterFab) findViewById(R.id.fabCart);

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

        /*COUNTER FAB CART CLICK EVENT*/

        counterFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartDetail(cart);
            }
        });

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
            Intent intent = new Intent(CommerceActivity.this, DetailCommmerceActivity.class);
            intent.putExtra("id_commerce", id_commerce);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    //How to send an ArrayList to another one Activity
    //Code from StackOverflow

    /*The first activity

        ArrayList<String> mylist = new ArrayList<String>();
        Intent intent = new Intent(ActivityName.this, Second.class);
        intent.putStringArrayListExtra("key", mylist);
        startActivity(intent);
        The second activity

        To retrieve

        ArrayList<String> list =  getIntent().getStringArrayListExtra("key");

       */
    /*add products to cart arraylist*/
    //Calculate the total
    //Calculate the items
    //Receive an array of products from Adapter Products when the user click on add product to cart
    public void cartHelper(){

    }

    public void cartDetail(ArrayList<Product> cart){

        Intent intent = new Intent(this, ShoppingCartActivity.class);
        //intent.putParcelableArrayListExtra("items", cart);
        intent.putExtra("id_commerce", id_commerce);
        startActivity(intent);


    }


}
