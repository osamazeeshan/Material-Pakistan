package com.materiallandmarks.fragment.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.materiallandmarks.R;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            View rootView = inflater.inflate(R.layout.fragment_about, container, false);

            return rootView;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
