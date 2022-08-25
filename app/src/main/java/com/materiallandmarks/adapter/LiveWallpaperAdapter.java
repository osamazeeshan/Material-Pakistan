package com.materiallandmarks.adapter;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.materiallandmarks.R;
import com.materiallandmarks.landmarkservice.FaisalMosqueLandmarkService;
import com.materiallandmarks.landmarkservice.MinarePakLandmarkService;
import com.materiallandmarks.landmarkservice.KhyberPassLandmarkService;
import com.materiallandmarks.landmarkservice.BadshahiMosqueLandmarkService;
import com.materiallandmarks.landmarkservice.MonumentLandmarkService;
import com.materiallandmarks.landmarkservice.QuaidLandmarkService;
import com.materiallandmarks.landmarkservice.RandomLandmarkService;
import com.materiallandmarks.utility.CommonConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class LiveWallpaperAdapter extends CustomRecyclerAdapter {

    private ArrayList<String> mGetCityName;
    public ArrayList<String> getValue;
    public LiveViewHolder mHolder;
    private RecyclerView mRecyclerView;

    public LiveWallpaperAdapter(ArrayList<String> cityNames, Context context) {
        super(context, cityNames);
        mGetCityName = cityNames;
    }

    @Override
    protected ArrayList<String> getIconName (String cityName) {
        try {
            ArrayList<String> wallpaperIconList = new ArrayList<>();
            wallpaperIconList.add(String.format("@drawable/live_%s_icon", cityName));
            return wallpaperIconList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    protected ViewHolder getNewView(ViewGroup parent, int viewType) {
        try {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_live_wallpaper, parent, false);
            LiveViewHolder liveViewHolder = new LiveViewHolder(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                    callWallpaperService(mGetCityName.get(itemPosition), view.getContext());
                }
            });

            return liveViewHolder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void bindView(ViewHolder holder, int position) {
        try {
            final LiveViewHolder liveViewHolder = (LiveViewHolder) holder;
            if(liveViewHolder == null) {
                return;
            }
            String getName = mGetCityName.get(position);
            ArrayList<String> iconNameList = getIconName(getName);
            if(iconNameList != null) {
                String uri = String.format(iconNameList.get(0));
                int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Drawable drawable = mContext.getResources().getDrawable(imageResource, mContext.getTheme());
                    RippleDrawable rippledImage = new RippleDrawable(ColorStateList.valueOf(Color.argb(200, 176, 190, 197)), drawable, null);
                    liveViewHolder.wallpaperImage.setImageDrawable(rippledImage);
                } else {
                    Drawable drawable = mContext.getResources().getDrawable(imageResource);
                    liveViewHolder.wallpaperImage.setImageDrawable(drawable);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callWallpaperService(String wallpaperCity, Context context) {
        if(context == null || wallpaperCity == null) {
            return;
        }
        try {
            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            switch (wallpaperCity) {
                case CommonConstants.MINARE_PAK:
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, MinarePakLandmarkService.class));
                    break;
                case CommonConstants.BADSHAHI_MOSQUE:
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, BadshahiMosqueLandmarkService.class));
                    break;
                case CommonConstants.FAISAL_MOSQUE:
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, FaisalMosqueLandmarkService.class));
                    break;
                case CommonConstants.KHYBER_PASS:
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, KhyberPassLandmarkService.class));
                    break;
                case CommonConstants.MONUMENT:
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, MonumentLandmarkService.class));
                    break;
                case CommonConstants.MAZARE_QUAID:
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, QuaidLandmarkService.class));
                    break;
                case CommonConstants.RANDOM:
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, RandomLandmarkService.class));
                    break;
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return mGetCityName.size();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    class LiveViewHolder extends CustomRecyclerAdapter.ViewHolder {
        private ImageView wallpaperImage;

        public LiveViewHolder(View itemView) {
            super(itemView);
            wallpaperImage = (ImageView) itemView.findViewById(R.id.wallpaper_image_view);
        }
    }
}
