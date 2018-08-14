package com.betaromar.omar1betar.apptest.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betaromar.omar1betar.apptest.Api.RetrofitClient;
import com.betaromar.omar1betar.apptest.R;
import com.betaromar.omar1betar.apptest.models.LoginResponse;
import com.betaromar.omar1betar.apptest.storage.SheardprefManger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button login;
    private TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = (EditText) findViewById(R.id.login_email);
        editTextPassword = (EditText) findViewById(R.id.login_password);
        signup = (TextView) findViewById(R.id.login_signup);
        login = (Button) findViewById(R.id.login_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToSignupActivity();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();

            }
        });
    }

    private void userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (email.isEmpty()) {
            editTextEmail.setError("Email Is Required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email Is Required");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password Is Required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Password Should be more than 6 chars ");
            editTextPassword.requestFocus();
            return;
        }
        Call<LoginResponse> call = RetrofitClient
                .getmInstance()
                .getApi()
                .userLogin(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (!loginResponse.getError()) {
                    SheardprefManger.getInstance(LoginActivity.this).saveUser(loginResponse.getUser());

                    sendToProfileActivity();

                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SheardprefManger.getInstance(this).isLoggedIn()){
            sendToProfileActivity();
        }
    }

    private void sendToProfileActivity() {
        Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void sendToSignupActivity() {
        Intent signupIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(signupIntent);
    }
}
