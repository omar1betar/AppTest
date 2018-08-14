package com.betaromar.omar1betar.apptest.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.betaromar.omar1betar.apptest.R;
import com.betaromar.omar1betar.apptest.storage.SheardprefManger;


public class HomeFragment extends Fragment {
    private TextView emailTextView,nameTextView,schoolTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailTextView = view.findViewById(R.id.text_view_email);
        nameTextView = view.findViewById(R.id.text_view_name);
        schoolTextView = view.findViewById(R.id.text_view_school);
        emailTextView.setText(SheardprefManger.getInstance(getActivity()).getUser().getEmail());
        nameTextView.setText(SheardprefManger.getInstance(getActivity()).getUser().getName());
        schoolTextView.setText(SheardprefManger.getInstance(getActivity()).getUser().getSchool());
    }
}
