package com.materiallandmarks.utility;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.View;

import com.materiallandmarks.R;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CommonUtil {

    public static boolean isNullOrEmpty(String string) {
        return (string == null || string.length() == 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getRippledDrawable(String drawableImage, Context context) {
        if(isNullOrEmpty(drawableImage) || context == null) {
            return null;
        }
        try {
            int imageResource = context.getResources().getIdentifier(drawableImage, null, context.getPackageName());
            Drawable drawable = context.getResources().getDrawable(imageResource, context.getTheme());
            return new RippleDrawable(ColorStateList.valueOf(Color.argb(200, 176, 190, 197)), drawable, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String setMarginAccordingToScreen(Activity activity) {
        if (activity == null) {
            return "-1";
        }
        DisplayMetrics dimension = getDisplayMetrics(activity);
        if (dimension.widthPixels <= 700) {
            return "3";
        } else if (dimension.widthPixels < 1000) {
            return "5";
        } else if (dimension.widthPixels < 1400) {
            return "50";
        } else if (dimension.widthPixels > 1400) {
            return "10";
        }
        return "1";
    }

    private static DisplayMetrics getDisplayMetrics(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            return displaymetrics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setRoundedBackground(Context context, View view, String backgroundColor, int radius, String borderColor, int borderWidth) {
        if(view == null || CommonUtil.isNullOrEmpty(backgroundColor)) {
            return;
        }
        try {
            GradientDrawable gdDefault = new GradientDrawable();
            gdDefault.setColor(Color.parseColor(backgroundColor));
            gdDefault.setCornerRadius(radius);
            if(borderWidth > 0 && !CommonUtil.isNullOrEmpty(borderColor)) {
                borderWidth = (int) CommonUtil.convertDpToPixel(borderWidth, context);
                gdDefault.setShape(GradientDrawable.RECTANGLE);
                gdDefault.setStroke(borderWidth, Color.parseColor(borderColor));
            }
            setViewBackground(view, gdDefault);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static float convertDpToPixel(float dp, Context context) {
        if(context == null) {
            return 0;
        }
        try {
            Resources resources = context.getResources();
            if(resources == null) {
                return 0;
            }
            DisplayMetrics metrics = resources.getDisplayMetrics();
            if(metrics == null) {
                return 0;
            }
            return dp * (metrics.densityDpi / 160f);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setViewBackground(View view, Drawable bitmapDrawable) {
        if(view == null) {
            return;
        }
        try {
            int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackgroundDrawable(bitmapDrawable);

            } else {
                view.setBackground(bitmapDrawable);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Drawable getDrawable(String drawableImage, Context context) {
        if(isNullOrEmpty(drawableImage) || context == null) {
            return null;
        }
        try {
            int imageResource = context.getResources().getIdentifier(drawableImage, null, context.getPackageName());
            return context.getResources().getDrawable(imageResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentDayTime() {
        try {
            String formattedDate = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
            String[] split = formattedDate.split(":");
            int convertTimeHours = Integer.parseInt(split[0]);
            String dayTime = CommonConstants.WALLPAPER_MORNING;
            if (convertTimeHours >= CommonConstants.DEFAULT_TIME_MORNING_START && convertTimeHours < CommonConstants.DEFAULT_TIME_MORNING_END) {
                dayTime = CommonConstants.WALLPAPER_MORNING;
            } else if (convertTimeHours >= CommonConstants.DEFAULT_TIME_AFTERNOON_START && convertTimeHours < CommonConstants.DEFAULT_TIME_AFTERNOON_END) {
                dayTime = CommonConstants.WALLPAPER_AFTERNOON;
            } else if (convertTimeHours >= CommonConstants.DEFAULT_TIME_EVENING_START && convertTimeHours < CommonConstants.DEFAULT_TIME_EVENING_END) {
                dayTime = CommonConstants.WALLPAPER_EVENING;
            } else if (convertTimeHours < CommonConstants.DEFAULT_TIME_NIGHT_END || (convertTimeHours == CommonConstants.TIME_21_HOUR || convertTimeHours == CommonConstants.TIME_22_HOUR || convertTimeHours == CommonConstants.TIME_23_HOUR)) {
                dayTime = CommonConstants.WALLPAPER_NIGHT;
            }
            return dayTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDay() {
        try {
            Calendar calendar = Calendar.getInstance();
            return calendar.get(Calendar.DAY_OF_WEEK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Bitmap getPhotoFromAssets(FragmentActivity activity, String photoName) {
        if(activity == null || CommonUtil.isNullOrEmpty(photoName)) {
            return null;
        }
        try {
            AssetManager assetManager = activity.getAssets();
            if(assetManager == null) {
                return null;
            }
            InputStream inputStream = assetManager.open(photoName);
            return BitmapFactory.decodeStream(inputStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createImageUri(String landmarkName, String dayTime, boolean isSelect) {
        if(landmarkName == null || dayTime == null) {
            return null;
        }
        try {
            if(isSelect) {
                return String.format("@drawable/static_%s_%s_select_icon", landmarkName, dayTime);
            } else {
                return String.format("@drawable/static_%s_%s_icon", landmarkName, dayTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWallpaperTitle(Context context, String name) {
        if(context == null || isNullOrEmpty(name)) {
            return null;
        }
        try {
            switch (name) {
                case CommonConstants.BADSHAHI_MOSQUE :
                    return context.getString(R.string.badshahi_mosque_text);
                case CommonConstants.MONUMENT :
                    return context.getString(R.string.monument_text);
                case CommonConstants.FAISAL_MOSQUE :
                    return context.getString(R.string.faisal_mosque_text);
                case CommonConstants.MINARE_PAK :
                    return context.getString(R.string.minare_pak_text);
                case CommonConstants.KHYBER_PASS :
                    return context.getString(R.string.khyberpass_text);
                case CommonConstants.MAZARE_QUAID :
                    return context.getString(R.string.quaid_text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
