package com.materiallandmarks.landmarkservice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.materiallandmarks.R;

import java.io.InputStream;
import java.util.Map;

public class BadshahiMosqueLandmarkService extends BaseLandmarksService {
    public final static String WALLPAPER_CITY = "lhr";
    public final static String WALLPAPER_LANDMARK = "badshahi_mosque";

    @Override
    public Engine onCreateEngine() {
        return new LHRWallpaperEngine();
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

    private class LHRWallpaperEngine extends BaseWallpaperEngine {

    }
}

