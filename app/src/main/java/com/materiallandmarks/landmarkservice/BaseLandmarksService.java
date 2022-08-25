package com.materiallandmarks.landmarkservice;


import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.materiallandmarks.R;
import com.materiallandmarks.utility.CommonConstants;

import java.io.InputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public abstract class BaseLandmarksService extends WallpaperService {

    public final static int PREF_TYPE_NIGHT_MODE = 1;
    public static boolean mIsLandscape = false;

    @Override
    public Engine onCreateEngine() {
        return null;
    }

    protected abstract String getWallpaperCity();
    protected abstract String getWallpaperLandmark();

    @Override
    public void onCreate() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class BaseWallpaperEngine extends Engine {
        private final Handler mHandler = new Handler();
        private SharedPreferences mSharedPreferences = null;
        private String mLiveWallpaperType = null;
        private long mTimeInMillis;
        private boolean mVisible = true;
        private boolean mNightMode = true;
        private PowerManager.WakeLock mWakeLock = null;
        private Bitmap mImage = null;

        public int mLandmarkServiceType;

        public final Runnable mDrawRunner = new Runnable() {
            @Override
            public void run() {
                try {
                    draw();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        public BaseWallpaperEngine() {
            try {
                if (mWakeLock == null || !mWakeLock.isHeld()) {
                    PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                    mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
                    mWakeLock.acquire();
                }
                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseLandmarksService.this);
                mNightMode = getPreferences(PREF_TYPE_NIGHT_MODE);
                mLiveWallpaperType = CommonConstants.WALLPAPER_MORNING;
                mTimeInMillis = CommonConstants.ONE_MINUTE_IN_MILLIS;
                mHandler.post(mDrawRunner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            try {
                this.mVisible = visible;
                if (visible) {
                    mNightMode = getPreferences(PREF_TYPE_NIGHT_MODE);
                    mHandler.post(mDrawRunner);
                } else {
                    //    mWakeLock.release();
                    mHandler.removeCallbacks(mDrawRunner);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            try {
                this.mVisible = false;
                mWakeLock.release();
                mHandler.removeCallbacks(mDrawRunner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
            if(display.getRotation() == Surface.ROTATION_90 || display.getRotation() == Surface.ROTATION_270) {
                mIsLandscape = true;
            } else {
                mIsLandscape = false;
            }
            draw();
        }

        private boolean getPreferences(int prefType) {
            if(mSharedPreferences == null) {
                return false;
            }
            try {
                if(prefType == PREF_TYPE_NIGHT_MODE) {
                    return mSharedPreferences.getBoolean(getString(R.string.pref_night_mode), false);
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        public void draw() {
            try {
                SurfaceHolder holder = getSurfaceHolder();
                Canvas canvas = null;
                try {
                    canvas = holder.lockCanvas();
                    if (canvas != null) {
                        mTimeInMillis = getTimeAndCalculateRemaining();
                        getBitmap();

                        PointF mScale = getCanvasScale(mImage.getWidth(), mImage.getHeight());
                        if (mScale != null) {
                            canvas.scale(mScale.x, mScale.y);
                            canvas.drawBitmap(mImage, 0, 0, null);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null)
                        holder.unlockCanvasAndPost(canvas);
                }
                mHandler.removeCallbacks(mDrawRunner);
                if (mVisible) {
                    mHandler.postAtTime(mDrawRunner, mTimeInMillis);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void getBitmap() {
            try {
                InputStream inputStream = null;
                String imageName = createImageName(mLiveWallpaperType);
                if(imageName == null) {
                    return;
                }
                inputStream = getAssets().open(imageName);
                if(inputStream == null) {
                    return;
                }
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inDensity = DisplayMetrics.DENSITY_DEFAULT;
                mImage = BitmapFactory.decodeStream(inputStream, null, opts);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String createImageName(String wallpaperType) {
            if(wallpaperType == null) {
                return null;
            }
            try {
                if (mNightMode && wallpaperType.equals(CommonConstants.WALLPAPER_NIGHT)){
                    if(mIsLandscape) {
                        return String.format(Locale.getDefault(), "%s_%s_black_horizontal.jpg", getWallpaperLandmark(), wallpaperType);
                    }
                    return String.format(Locale.getDefault(), "%s_%s_black.jpg", getWallpaperLandmark(), wallpaperType);
                }
                if(mIsLandscape) {
                    return String.format(Locale.getDefault(), "%s_%s_horizontal.jpg", getWallpaperLandmark(), wallpaperType);
                }
                else {
                    return String.format(Locale.getDefault(), "%s_%s.jpg", getWallpaperLandmark(), wallpaperType);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private PointF getCanvasScale(int imageWidth, int imageHeight) {
            try {
                PointF canvasScale = new PointF(1f, 1f);
                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                canvasScale.x = display.getWidth() / (1f * imageWidth);
                canvasScale.y = display.getHeight() / (1f * imageHeight);
                return canvasScale;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private long getTimeAndCalculateRemaining() {
            try {
                String formattedDate = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
                String[] split = formattedDate.split(":");
                long millis = SystemClock.uptimeMillis();//00== 903982977
                int calculateRemainingHours = 0;
                int convertTimeHours = Integer.parseInt(split[0]);
                int convertTimeMinutes = Integer.parseInt(split[1]);

                if (convertTimeHours >= CommonConstants.DEFAULT_TIME_MORNING_START && convertTimeHours < CommonConstants.DEFAULT_TIME_MORNING_END) {                        /* Calculate Seconds Maybe if needed */
                    mLiveWallpaperType = CommonConstants.WALLPAPER_MORNING;
                    calculateRemainingHours = CommonConstants.DEFAULT_TIME_MORNING_END - convertTimeHours;
                } else if (convertTimeHours >= CommonConstants.DEFAULT_TIME_AFTERNOON_START && convertTimeHours < CommonConstants.DEFAULT_TIME_AFTERNOON_END) {
                    mLiveWallpaperType = CommonConstants.WALLPAPER_AFTERNOON;
                    calculateRemainingHours = CommonConstants.DEFAULT_TIME_AFTERNOON_END - convertTimeHours;
                } else if (convertTimeHours >= CommonConstants.DEFAULT_TIME_EVENING_START && convertTimeHours < CommonConstants.DEFAULT_TIME_EVENING_END) {
                    mLiveWallpaperType = CommonConstants.WALLPAPER_EVENING;
                    calculateRemainingHours = CommonConstants.DEFAULT_TIME_EVENING_END - convertTimeHours;
                } else if (convertTimeHours < CommonConstants.DEFAULT_TIME_NIGHT_END || (convertTimeHours == CommonConstants.TIME_21_HOUR || convertTimeHours == CommonConstants.TIME_22_HOUR || convertTimeHours == CommonConstants.TIME_23_HOUR)) {
                    mLiveWallpaperType = CommonConstants.WALLPAPER_NIGHT;
                    if (convertTimeHours == CommonConstants.TIME_21_HOUR) {
                        calculateRemainingHours = CommonConstants.REMAINING_9_HOUR;
                    } else if (convertTimeHours == CommonConstants.TIME_22_HOUR) {
                        calculateRemainingHours = CommonConstants.REMAINING_8_HOUR;
                    } else if (convertTimeHours == CommonConstants.TIME_23_HOUR) {
                        calculateRemainingHours = CommonConstants.REMAINING_7_HOUR;
                    } else {
                        calculateRemainingHours = CommonConstants.DEFAULT_TIME_NIGHT_END - convertTimeHours;
                    }
                }

                long calculateRemainingMinutes = convertTimeMinutes * CommonConstants.ONE_MINUTE_IN_MILLIS;
                long timeInMillis = (calculateRemainingHours * CommonConstants.ONE_HOUR_IN_MILLIS) - calculateRemainingMinutes;
                return millis + timeInMillis;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return CommonConstants.ONE_HOUR_IN_MILLIS;
        }
    }
}


