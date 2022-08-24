package com.materiallandmarks.landmarkservice;

public class FaisalMosqueLandmarkService extends BaseLandmarksService {

    public final static String WALLPAPER_CITY = "isl";
    public final static String WALLPAPER_LANDMARK = "faisal_mosque";

    @Override
    public Engine onCreateEngine() {
        return new FaisalMosqueLandmarkService.ISBWallpaperEngine();
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

    private class ISBWallpaperEngine extends BaseWallpaperEngine {


    }
}

