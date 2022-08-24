package com.materiallandmarks.landmarkservice;

import android.service.wallpaper.WallpaperService;

import com.materiallandmarks.utility.CommonUtil;

public class RandomLandmarkService extends BaseLandmarksService {

    @Override
    public WallpaperService.Engine onCreateEngine() {
        return new RandomWallpaperEngine();
    }

    @Override
    protected String getWallpaperCity() {
        try {
            int day = CommonUtil.getDay();
            switch (day) {
                case 1 :
                    return MonumentLandmarkService.WALLPAPER_CITY;
                case 2 :
                    return BadshahiMosqueLandmarkService.WALLPAPER_CITY;
                case 3 :
                    return FaisalMosqueLandmarkService.WALLPAPER_CITY;
                case 4 :
                    return QuaidLandmarkService.WALLPAPER_CITY;
                case 5 :
                    return MinarePakLandmarkService.WALLPAPER_CITY;
                case 6 :
                    return KhyberPassLandmarkService.WALLPAPER_CITY;
                case 7 :
                    return QuaidLandmarkService.WALLPAPER_CITY;
                default:
                    return MonumentLandmarkService.WALLPAPER_CITY;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getWallpaperLandmark() {
        try {
            int day = CommonUtil.getDay();
            switch (day) {
                case 1 :
                    return MonumentLandmarkService.WALLPAPER_LANDMARK;
                case 2 :
                    return BadshahiMosqueLandmarkService.WALLPAPER_LANDMARK;
                case 3 :
                    return FaisalMosqueLandmarkService.WALLPAPER_LANDMARK;
                case 4 :
                    return QuaidLandmarkService.WALLPAPER_LANDMARK;
                case 5 :
                    return MinarePakLandmarkService.WALLPAPER_LANDMARK;
                case 6 :
                    return KhyberPassLandmarkService.WALLPAPER_LANDMARK;
                case 7 :
                    return QuaidLandmarkService.WALLPAPER_LANDMARK;
                default:
                    return MonumentLandmarkService.WALLPAPER_LANDMARK;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RandomWallpaperEngine extends BaseWallpaperEngine {
    }
}
