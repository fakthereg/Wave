package com.k1d.wave.ui.main.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.k1d.wave.MainActivity;
import com.k1d.wave.R;
import com.k1d.wave.User;

public class FragmentLoggedIn extends Fragment {
    public static FragmentLoggedIn instance;

    public static FragmentLoggedIn getInstance() {
        if (instance == null) {
            instance = new FragmentLoggedIn();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logged_in, container, false);
        ImageView avatar = view.findViewById(R.id.logged_in_avatar);
        TextView hello_username = view.findViewById(R.id.logged_in_hello);
        String settext = "Привет, " + User.name.substring(0, 1).toUpperCase() + User.name.substring(1) + "!";
        avatar.setImageResource(User.avatar);
        hello_username.setText(settext);
        MainActivity.loadSongs();
        return view;

    }
}
