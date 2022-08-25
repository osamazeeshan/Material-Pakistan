package com.materiallandmarks.landmarkservice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.materiallandmarks.R;
import com.materiallandmarks.utility.CommonConstants;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MinarePakLandmarkService extends BaseLandmarksService {
    public final static String WALLPAPER_CITY = "khi";
    public final static String WALLPAPER_LANDMARK = "minare_pak";

    @Override
    public Engine onCreateEngine() {
        return new KHIWallpaperEngine();
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

    private class KHIWallpaperEngine extends BaseWallpaperEngine {
    }
}