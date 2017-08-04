package com.example.android.karta.Activities;

import android.support.annotation.RestrictTo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.karta.API.API;
import com.example.android.karta.API.Service;
import com.example.android.karta.Models.Response.UserResponse;
import com.example.android.karta.Models.User;
import com.example.android.karta.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    EditText txtName, txtLastName, txtEmail, txtPhone, txtPass, txtRepeatPass;
    Button btnNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        /*Cast Edit text */

        txtName = (EditText) findViewById(R.id.txtNewName);
        txtLastName = (EditText) findViewById(R.id.txtNewLastName);
        txtEmail = (EditText) findViewById(R.id.txtNewEmail);
        txtPhone = (EditText) findViewById(R.id.txtNewPhone);
        txtPass = (EditText) findViewById(R.id.txtNewPass);
        txtRepeatPass = (EditText) findViewById(R.id.txtRepeatPassword);

        btnNewAccount = (Button) findViewById(R.id.btnNewAccount);


        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = txtName.getText().toString();
                String lastName = txtLastName.getText().toString();
                int phone = Integer.parseInt(txtPhone.getText().toString());
                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();

                User newUser = new User(0,name,lastName,phone,email,pass, "");

                createAccount(newUser);


            }
        });

    }

    private void createAccount(User newUser) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<UserResponse> newUserCall = service.newAccount(newUser);

        newUserCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()){

                    UserResponse newUserRes = response.body();
                    int code = newUserRes.getCode();

                    if(code == 200){
                        Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
