package com.betaromar.omar1betar.apptest.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.betaromar.omar1betar.apptest.Fragments.HomeFragment;
import com.betaromar.omar1betar.apptest.Fragments.SettingsFragment;
import com.betaromar.omar1betar.apptest.Fragments.UserFragment;
import com.betaromar.omar1betar.apptest.R;
import com.betaromar.omar1betar.apptest.models.User;
import com.betaromar.omar1betar.apptest.storage.SheardprefManger;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        display(new HomeFragment());

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_navegtion);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment =null;
                switch (item.getItemId()){
                    case R.id.menu_home:
                        fragment = new HomeFragment();

                        break;
                    case R.id.menu_user:
                        fragment = new UserFragment();

                        break;
                    case R.id.menu_settings:
                        fragment = new SettingsFragment();

                        break;
                }
                if (fragment != null){
                    display(fragment);
                }
                return false;

            }
        });
    }
    private void display(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!SheardprefManger.getInstance(this).isLoggedIn()){
            sendToProfileActivity();
        }
    }

    private void sendToProfileActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
