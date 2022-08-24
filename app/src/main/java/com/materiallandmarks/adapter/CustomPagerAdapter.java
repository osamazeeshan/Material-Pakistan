package com.materiallandmarks.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.materiallandmarks.fragment.LiveWallpaperFragment;
import com.materiallandmarks.fragment.StaticWallpaperFragment;
import com.materiallandmarks.fragment.settings.AboutFragment;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        try {
            switch (position) {
                case 0:
                    return new LiveWallpaperFragment();
                case 1:
                    return new StaticWallpaperFragment();
                case 2:
                    return new AboutFragment();
                default:
                    return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        try {
            switch (position ) {
                case 0:
                    return "Live";
                case 1:
                    return "Static";
                case 2:
                    return "Hey There!";
                default:
                    return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
