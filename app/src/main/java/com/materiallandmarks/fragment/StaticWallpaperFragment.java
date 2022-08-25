package com.materiallandmarks.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.materiallandmarks.R;
import com.materiallandmarks.activity.MainActivity;
import com.materiallandmarks.adapter.LiveWallpaperAdapter;
import com.materiallandmarks.adapter.StaticWallpaperAdapter;
import com.materiallandmarks.utility.CommonConstants;

import java.util.ArrayList;

public class StaticWallpaperFragment extends Fragment {
    private StaticWallpaperAdapter mStaticWallpaperAdapter = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            View rootView = inflater.inflate(R.layout.fragment_static_wallpaper, container, false);
            RecyclerView wallpaperRecyclerView = (RecyclerView) rootView.findViewById(R.id.wallpaper_recycler_view);

            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeStatusBarState(true);

            if(wallpaperRecyclerView != null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                wallpaperRecyclerView.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                ArrayList<String> names = new ArrayList<>();
                names.add(CommonConstants.FAISAL_MOSQUE);
                names.add(CommonConstants.MAZARE_QUAID);
                names.add(CommonConstants.BADSHAHI_MOSQUE);
                names.add(CommonConstants.MINARE_PAK);
                names.add(CommonConstants.MONUMENT);
                names.add(CommonConstants.KHYBER_PASS);
                mStaticWallpaperAdapter = new StaticWallpaperAdapter(names, getActivity().getApplicationContext(), getActivity());
                wallpaperRecyclerView.setAdapter(mStaticWallpaperAdapter);
            }

            return rootView;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
