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
    //KHIWallpaperEngine mKHIWallpaperEngine = null;

    public final static String WALLPAPER_CITY = "khi";
    public final static String WALLPAPER_LANDMARK = "minare_pak";

    @Override
    public Engine onCreateEngine() {
    //    mKHIWallpaperEngine = new KHIWallpaperEngine();
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

//    @Override
//    public void onDestroy() {
//        //mKHIWallpaperEngine.
//    }

    @Override
    public void onCreate() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class KHIWallpaperEngine extends BaseWallpaperEngine {

//        private final Handler mHandler = new Handler();
//        private SharedPreferences mSharedPreferences = null;
//        private String mLiveWallpaperType = null;
//        private long mTimeInMillis;
//        private boolean mVisible = true;
//        private boolean mNightMode = true;
//        private PowerManager.WakeLock mWakeLock = null;
//        private Bitmap mImage = null;
//
//        private final Runnable mDrawRunner = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    draw();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//

//        @Override
//        public void onVisibilityChanged(boolean visible) {
//
//        }
//
//        @Override
//        public void onSurfaceDestroyed(SurfaceHolder holder) {
//
//        }
//
//        @Override
//        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//            super.onSurfaceChanged(holder, format, width, height);
//        }
//
//        private boolean getPreferences(int prefType) {
//            if(mSharedPreferences == null) {
//                return false;
//            }
//            try {
//                if(prefType == PREF_TYPE_NIGHT_MODE) {
//                    return mSharedPreferences.getBoolean(getString(R.string.pref_night_mode), false);
//                }
//                return false;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
//
////        @Override
////        public Bundle onCommand(String action, int x, int y, int z, Bundle extras, boolean resultRequested) {
////            return super.onCommand(action, x, y, z, extras, resultRequested);
////        }
//
//        @Override
//        public void onVisibilityChanged(boolean visible) {
//            try {
//                mVisible = visible;
//                if (visible) {
//                    mNightMode = getPreferences(PREF_TYPE_NIGHT_MODE);
//                    mHandler.post(mDrawRunner);
//                } else {
//                //    mWakeLock.release();
//                    mHandler.removeCallbacks(mDrawRunner);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onSurfaceDestroyed(SurfaceHolder holder) {
//            super.onSurfaceDestroyed(holder);
//            try {
//                mVisible = false;
//                mWakeLock.release();
//                mHandler.removeCallbacks(mDrawRunner);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//            super.onSurfaceChanged(holder, format, width, height);
//        }
//
//        private void draw() {
//            try {
//                SurfaceHolder holder = getSurfaceHolder();
//                Canvas canvas = null;
//                try {
//                    canvas = holder.lockCanvas();
//                    if (canvas != null) {
//                        // mImage = BitmapFactory.decodeResource(getResources(), R.drawable.quaid_012);
//                        mTimeInMillis = getTimeAndCalculateRemaining();
//                        getBitmap();
//                        PointF mScale = getCanvasScale(mImage.getWidth(), mImage.getHeight());
//                        if (mScale != null) {
//                            canvas.scale(mScale.x, mScale.y);
//                            canvas.drawBitmap(mImage, 0, 0, null);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (canvas != null)
//                        holder.unlockCanvasAndPost(canvas);
//                }
//                mHandler.removeCallbacks(mDrawRunner);
//                if (mVisible) {
//                    //    mHandler.postDelayed(mDrawRunner, 10000);
//                    mHandler.postAtTime(mDrawRunner, mTimeInMillis);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        private void getBitmap() {
//            try {
//                InputStream inputStream = null;
//                switch (mLiveWallpaperType) {
//                    case CommonConstants.LIVE_WALLPAPER_MORNING:
//                        inputStream = getAssets().open("khi_quaid_morning.png");
//                        mImage = BitmapFactory.decodeStream(inputStream);
//                        //    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_MIDDAY;
//                        break;
//                    case CommonConstants.LIVE_WALLPAPER_MIDDAY:
//                        inputStream = getAssets().open("khi_quaid_afternoon.png");
//                        mImage = BitmapFactory.decodeStream(inputStream);
//                        //   mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_SUNSET;
//                        break;
//                    case CommonConstants.LIVE_WALLPAPER_SUNSET:
//                        inputStream = getAssets().open("khi_quaid_evening.png");
//                        mImage = BitmapFactory.decodeStream(inputStream);
//                        //mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_NIGHT;
//                        break;
//                    case CommonConstants.LIVE_WALLPAPER_NIGHT:
//                        if (mNightMode){
//                            inputStream = getAssets().open("khi_quaid_evening.png");
//                        } else {
//                            inputStream = getAssets().open("khi_quaid_night.png");
//                        }
//                        mImage = BitmapFactory.decodeStream(inputStream);
//                        //mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_MORNING;
//                        break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        private PointF getCanvasScale(int imageWidth, int imageHeight) {
//            try {
//                PointF canvasScale = new PointF(1f, 1f);
//                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//                Display display = wm.getDefaultDisplay();
//                canvasScale.x = display.getWidth() / (1f * imageWidth);
//                canvasScale.y = display.getHeight() / (1f * imageHeight);
//                return canvasScale;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        private long getTimeAndCalculateRemaining() {
//            try {
//                String formattedDate = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
//                String[] split = formattedDate.split(":");
//                long millis = SystemClock.uptimeMillis();//00== 903982977
//                int calculateRemainingHours = 0;
//                int convertTimeHours = Integer.parseInt(split[0]);
//                int convertTimeMinutes = Integer.parseInt(split[1]);
//
////                if (convertTimeHours >= 6 && convertTimeHours < 12 || convertTimeHours == CommonConstants.TIME_19_HOUR) {                        /* Calculate Seconds Maybe if needed */
////                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_MORNING;
////                    calculateRemainingHours = 12 - convertTimeHours;
////                } else if (convertTimeHours >= 12 && convertTimeHours < 16) {
////                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_MIDDAY;
////                    calculateRemainingHours = 16 - convertTimeHours;
////                } else if (convertTimeHours >= 16 && convertTimeHours < 18) {
////                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_SUNSET;
////                    calculateRemainingHours = 18 - convertTimeHours;
////                } else if (convertTimeHours < 6 || (convertTimeHours == CommonConstants.TIME_18_HOUR || convertTimeHours == CommonConstants.TIME_23_HOUR)) {
////                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_NIGHT;
////                    if (convertTimeHours == CommonConstants.TIME_18_HOUR) {
////                        calculateRemainingHours = 1;
////                    } else if (convertTimeHours == CommonConstants.TIME_22_HOUR) {
////                        calculateRemainingHours = CommonConstants.REMAINING_8_HOUR;
////                    } else if (convertTimeHours == CommonConstants.TIME_23_HOUR) {
////                        calculateRemainingHours = CommonConstants.REMAINING_7_HOUR;
////                    } else {
////                        calculateRemainingHours = 6 - convertTimeHours;
////                    }
////                }
//
//                if (convertTimeHours >= 6 && convertTimeHours < 12) {                        /* Calculate Seconds Maybe if needed */
//                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_MORNING;
//                    calculateRemainingHours = 12 - convertTimeHours;
//                } else if (convertTimeHours >= 12 && convertTimeHours < 16) {
//                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_MIDDAY;
//                    calculateRemainingHours = 16 - convertTimeHours;
//                } else if (convertTimeHours >= 16 && convertTimeHours < 21) {
//                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_SUNSET;
//                    calculateRemainingHours = 21 - convertTimeHours;
//                } else if (convertTimeHours < 6 || (convertTimeHours == CommonConstants.TIME_21_HOUR || convertTimeHours == CommonConstants.TIME_22_HOUR || convertTimeHours == CommonConstants.TIME_23_HOUR)) {
//                    mLiveWallpaperType = CommonConstants.LIVE_WALLPAPER_NIGHT;
//                    if (convertTimeHours == CommonConstants.TIME_21_HOUR) {
//                        calculateRemainingHours = CommonConstants.REMAINING_9_HOUR;
//                    } else if (convertTimeHours == CommonConstants.TIME_22_HOUR) {
//                        calculateRemainingHours = CommonConstants.REMAINING_8_HOUR;
//                    } else if (convertTimeHours == CommonConstants.TIME_23_HOUR) {
//                        calculateRemainingHours = CommonConstants.REMAINING_7_HOUR;
//                    } else {
//                        calculateRemainingHours = 6 - convertTimeHours;
//                    }
//                }
//
//                long calculateRemainingMinutes = convertTimeMinutes * CommonConstants.ONE_MINUTE_IN_MILLIS;
//                long timeInMillis = (calculateRemainingHours * CommonConstants.ONE_HOUR_IN_MILLIS) - calculateRemainingMinutes;
//                return millis + timeInMillis;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return CommonConstants.ONE_HOUR_IN_MILLIS;
//        }
    }
}