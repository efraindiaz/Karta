package com.example.android.karta.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {

    Button btnNewUser, btnLogin, btnRestorePass;
    EditText etEmail, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        Boolean loggedin = preferences.getBoolean("loggedin", false);

        if(loggedin){
            goToMain();
        }

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnNewUser = (Button) findViewById(R.id.buttonNewUser);
        btnRestorePass = (Button) findViewById(R.id.btnRespass);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Espere...", Toast.LENGTH_SHORT).show();
                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                login(email,pass);
            }
        });


        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        btnRestorePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resPass = new Intent(LoginActivity.this, RestorePassActivity.class);
                startActivity(resPass);
            }
        });
    }

    private void login(String email, String pass) {

        Retrofit retrofit = API.getRetrofit();

        Service service = retrofit.create(Service.class);

        Call<UserResponse> userCall = service.getUserData(email, pass);

        userCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()) {
                    UserResponse userRes = response.body();
                    int code = userRes.getCode();


                    if (code == 200) {
                        User user = userRes.getDataUser();
                        saveInPhone(user);
                        goToMain();
                    }

                    else if(code == 401){

                        Toast.makeText(LoginActivity.this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveInPhone(User user) {

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("loggedin", true);
        editor.putInt("id_user", user.getId_info_user_consumer());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.commit();

    }

    private void goToMain(){
        Intent main = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(main);
    }
}
