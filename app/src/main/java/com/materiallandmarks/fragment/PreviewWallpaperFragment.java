package com.materiallandmarks.fragment;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.materiallandmarks.R;
import com.materiallandmarks.activity.MainActivity;

import java.io.InputStream;

public class PreviewWallpaperFragment extends Fragment {
    public static String DAY_TIME = "dayTime";
    public static String LANDMARK = "landmark";

    private String mDayTime = null;
    private String mLandmark = null;

    public PreviewWallpaperFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            View rootView = inflater.inflate(R.layout.fragment_preview_wallpaper, container, false);
            ImageView wallpaperPreview = (ImageView) rootView.findViewById(R.id.wallpaper_preview);
            Button setWallpaperButton = (Button) rootView.findViewById(R.id.set_static_wallpaper);

            final MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeStatusBarState(false);

            Bundle args = getArguments();
            if(args != null) {
                mDayTime = args.getString(DAY_TIME);
                mLandmark = args.getString(LANDMARK);
            }
            mainActivity.setAdViewVisibility(false);
            if(wallpaperPreview != null) {
                wallpaperPreview.setImageBitmap(getSelectedWallpaper(createFileName()));
//                wallpaperPreview.setBackground(drawable);
//                wallpaperPreview.setImageDrawable(drawable);
            }

            if(setWallpaperButton != null) {
                setWallpaperButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            mainActivity.removePreviewFragment();
                            WallpaperManager myWallpaperManager = WallpaperManager.getInstance(view.getContext());

//                            String uri = "@drawable/badshahi_mosque_evening";
//                            int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
//                            myWallpaperManager.setResource(imageResource);

//                            DisplayMetrics dimension = getDisplayMetrics(activity);

                            DisplayMetrics metrics = new DisplayMetrics();
                            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                            int height = metrics.heightPixels;
                            int width = metrics.widthPixels;
                            Bitmap bitmap = Bitmap.createScaledBitmap(getSelectedWallpaper(createFileName()), width, height, true);

                            myWallpaperManager.setBitmap(bitmap);
                            Toast.makeText(getContext(), getString(R.string.wallpaper_set_text), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            return rootView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createFileName() {
        if(mDayTime == null || mLandmark == null) {
            return null;
        }
        try {
            return String.format("%s_%s.jpg", mLandmark, mDayTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap getSelectedWallpaper(String wallpaperName) {
        if(wallpaperName == null) {
            return null;
        }
        try {
//            Display d = w.getDefaultDisplay();
//            Measuredwidth = d.getWidth();
//            Measuredheight = d.getHeight();
//            WallpaperManager.getDesiredMinimumWidth();
//            WallpaperManager.getDesiredMinimumHeight();

            InputStream inputStream = getActivity().getAssets().open(wallpaperName);
            if (inputStream == null) {
                return null;
            }
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
