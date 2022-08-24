package com.materiallandmarks.landmarkservice;

import android.service.wallpaper.WallpaperService;

public class KhyberPassLandmarkService extends BaseLandmarksService {
    public final static String WALLPAPER_CITY = "kpk";
    public final static String WALLPAPER_LANDMARK = "khyberpass";

    @Override
    public WallpaperService.Engine onCreateEngine() {
        return new KhyberWallpaperEngine();
    }

    @Override
    protected String getWallpaperCity() {
        return WALLPAPER_CITY;
    }

    @Override
    protected String getWallpaperLandmark() {
        return WALLPAPER_LANDMARK;
    }

    @Override
    public void onCreate() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class KhyberWallpaperEngine extends BaseWallpaperEngine {
    }
}


