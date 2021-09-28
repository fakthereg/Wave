package com.k1d.wave.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.fragment.app.Fragment;

import com.k1d.wave.R;

public class FragmentRegisterOne extends Fragment {
    public static FragmentRegisterOne instance;

    public static FragmentRegisterOne getInstance() {
        if (instance == null){
            instance = new FragmentRegisterOne();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_one, container, false);
    }
    
}
