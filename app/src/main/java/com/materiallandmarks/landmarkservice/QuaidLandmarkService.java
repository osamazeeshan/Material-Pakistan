package com.materiallandmarks.landmarkservice;

import android.service.wallpaper.WallpaperService;

public class QuaidLandmarkService extends BaseLandmarksService {
    public final static String WALLPAPER_CITY = "khi";
    public final static String WALLPAPER_LANDMARK = "quaid";

    @Override
    public WallpaperService.Engine onCreateEngine() {
        return new QuaidWallpaperEngine();
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

    private class QuaidWallpaperEngine extends BaseWallpaperEngine {
    }
}
