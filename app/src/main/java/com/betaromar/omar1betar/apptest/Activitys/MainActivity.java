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
import com.betaromar.omar1betar.apptest.models.DefaultResponse;
import com.betaromar.omar1betar.apptest.R;
import com.betaromar.omar1betar.apptest.storage.SheardprefManger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword, editTextName, editTextSchool;
    private TextView login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText) findViewById(R.id.signup_email);
        editTextPassword = (EditText) findViewById(R.id.signup_password);
        editTextName = (EditText) findViewById(R.id.signup_name);
        editTextSchool = (EditText) findViewById(R.id.signup_school);
        login = (TextView) findViewById(R.id.signup_login);
        signup = (Button) findViewById(R.id.signup_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToLoginActivity();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignUp();
            }
        });
    }



    private void userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String school = editTextSchool.getText().toString().trim();

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
        if (name.isEmpty()) {
            editTextName.setError("Name Is Required");
            editTextName.requestFocus();
            return;
        }
        if (school.isEmpty()) {
            editTextSchool.setError("School Is Required");
            editTextSchool.requestFocus();
            return;
        }
        Call<DefaultResponse> call = RetrofitClient
                .getmInstance()
                .getApi()
                .createuser(email, password, name, school);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201){
                    DefaultResponse dr = response.body();
                    Toast.makeText(MainActivity.this, dr.getMsg(), Toast.LENGTH_SHORT).show();
                }else if(response.code() == 422){
                    Toast.makeText(MainActivity.this, "User Already Exist ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SheardprefManger.getInstance(this).isLoggedIn()){
            sendToLoginActivity();
        }
    }

    private void sendToProfileActivity() {
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void sendToLoginActivity() {
        Intent signupIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(signupIntent);
    }


}
