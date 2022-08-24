package com.materiallandmarks.landmarkservice;

import android.service.wallpaper.WallpaperService;

public class MonumentLandmarkService extends BaseLandmarksService {
    public final static String WALLPAPER_CITY = "isl";
    public final static String WALLPAPER_LANDMARK = "monument";

    @Override
    public WallpaperService.Engine onCreateEngine() {
        return new MonumentWallpaperEngine();
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

    private class MonumentWallpaperEngine extends BaseWallpaperEngine {
    }
}
