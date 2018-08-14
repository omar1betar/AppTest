package com.betaromar.omar1betar.apptest.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.betaromar.omar1betar.apptest.Api.RetrofitClient;
import com.betaromar.omar1betar.apptest.R;
import com.betaromar.omar1betar.apptest.models.LoginResponse;
import com.betaromar.omar1betar.apptest.models.User;
import com.betaromar.omar1betar.apptest.storage.SheardprefManger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettingsFragment extends Fragment {
    private EditText editTextEmail,editTextName,editTextSchool;
    private EditText editTextCurrentPassword,editTextNewPassword;
    private Button saveButton,updatePasswordButton,logoutButtton,deleteProfileButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextEmail = view.findViewById(R.id.setting_edit_text_email);
        editTextName = view.findViewById(R.id.setting_edit_text_name);
        editTextSchool = view.findViewById(R.id.setting_edit_text_school);
        editTextCurrentPassword = view.findViewById(R.id.setting_edit_text_current_password);
        editTextNewPassword = view.findViewById(R.id.setting_edit_text_new_password);
        saveButton = view.findViewById(R.id.save_change_profile);
        updatePasswordButton = view.findViewById(R.id.update_passwprd);
        logoutButtton = view.findViewById(R.id.logout);
        deleteProfileButton = view.findViewById(R.id.delete_profile);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();

            }
        });
        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        logoutButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        deleteProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void updateProfile() {
        String email = editTextEmail.getText().toString().trim();
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
        User user = SheardprefManger.getInstance(getActivity()).getUser();

        Call<LoginResponse> call = RetrofitClient.getmInstance()
                .getApi().updateUser(
                        user.getId(),
                        email,
                        name,
                        school
                );
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

//                assert response.body() != null;
//                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                 //when success we save new data to sheared pref
//                if (!response.body().getError() ){
//                    SheardprefManger.getInstance(getActivity()).saveUser(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
