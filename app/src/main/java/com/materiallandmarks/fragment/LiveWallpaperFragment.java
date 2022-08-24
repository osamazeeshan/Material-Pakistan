package com.materiallandmarks.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.materiallandmarks.R;
import com.materiallandmarks.adapter.LiveWallpaperAdapter;
import com.materiallandmarks.utility.CommonConstants;
import com.materiallandmarks.utility.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class LiveWallpaperFragment extends Fragment {
    private LiveWallpaperAdapter mWallpaperAdapter = null;
    private GridLayoutManager lLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            View rootView = inflater.inflate(R.layout.fragment_live_wallpaper, container, false);
            RecyclerView wallpaperRecyclerView = (RecyclerView) rootView.findViewById(R.id.wallpaper_recycler_view);
            if(wallpaperRecyclerView != null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                lLayout = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, true);
//                lLayout.scrollToPositionWithOffset(0, 0);
//                lLayout.setReverseLayout(true);
//                lLayout.setStackFromEnd(true);
//                lLayout.layoutDecoratedWithMargins(wallpaperRecyclerView, 0, 0, 0, 0);
//                GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();

//                lLayout.setMeasuredDimension(165, 220);
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) wallpaperRecyclerView.getLayoutParams();
                String margin = CommonUtil.setMarginAccordingToScreen(getActivity());
                marginLayoutParams.setMargins(Integer.parseInt(margin), 0, 0, 0);
//                marginLayoutParams.setMarginStart(0);
//                wallpaperRecyclerView.setLayoutParams(marginLayoutParams);
                wallpaperRecyclerView.setLayoutManager(lLayout);
                ArrayList<String> names = new ArrayList<>();
//                names.add(0, CommonConstants.MAZARE_QUAID);
//                names.add(1, CommonConstants.FAISAL_MOSQUE);
//                names.add(2, CommonConstants.BADSHAHI_MOSQUE);
//                names.add(3, CommonConstants.KHYBER_PASS);
//                names.add(4, CommonConstants.MONUMENT);
//                names.add(5, CommonConstants.MINARE_PAK);
//                names.add(CommonConstants.RANDOM);
                names.add(CommonConstants.FAISAL_MOSQUE);
                names.add(CommonConstants.MAZARE_QUAID);
                names.add(CommonConstants.BADSHAHI_MOSQUE);
                names.add(CommonConstants.KHYBER_PASS);
                names.add(CommonConstants.MONUMENT);
                names.add(CommonConstants.MINARE_PAK);
                mWallpaperAdapter = new LiveWallpaperAdapter(names, getActivity().getApplicationContext());
                mWallpaperAdapter.setRecyclerView(wallpaperRecyclerView);

                wallpaperRecyclerView.setAdapter(mWallpaperAdapter);

                names.add(6, CommonConstants.RANDOM);
//                wallpaperRecyclerView.getLayoutManager().scrollToPosition(3);
                mWallpaperAdapter.notifyItemInserted(6);
                mWallpaperAdapter.notifyDataSetChanged();
            }

            return rootView;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
