package com.materiallandmarks.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.materiallandmarks.R;
import com.materiallandmarks.fragment.PreviewWallpaperFragment;
import com.materiallandmarks.utility.CommonConstants;
import com.materiallandmarks.utility.CommonUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.materiallandmarks.utility.CommonUtil.createImageUri;
import static com.materiallandmarks.utility.CommonUtil.getDrawable;
import static com.materiallandmarks.utility.CommonUtil.getRippledDrawable;

public class StaticWallpaperAdapter extends CustomRecyclerAdapter {

    private ArrayList<String> mGetCityName;
    private FragmentActivity mFragmentActivity;
    private RecyclerView mRecyclerView;

    public StaticWallpaperAdapter(ArrayList<String> getName, Context context, FragmentActivity fragmentActivity) {
        super(context, getName);
        mGetCityName = getName;
        mFragmentActivity = fragmentActivity;
        //    getValue = new ArrayList<String>(Collections.nCopies(100, "empty"));
    }

    @Override
    protected ArrayList<String> getIconName (String cityName) {
        try {
            ArrayList<String> wallpaperIconList = new ArrayList<>();
            wallpaperIconList.add(String.format("@drawable/static_%s_morning_icon", cityName));
            wallpaperIconList.add(String.format("@drawable/static_%s_afternoon_icon", cityName));
            wallpaperIconList.add(String.format("@drawable/static_%s_evening_icon", cityName));
            wallpaperIconList.add(String.format("@drawable/static_%s_night_icon", cityName));
            wallpaperIconList.add(String.format("@drawable/static_%s_night_black_icon", cityName));

            String dayTime = CommonUtil.getCurrentDayTime();
            if(dayTime == null) {
                return null;
            }

            if(dayTime.equals(CommonConstants.WALLPAPER_MORNING)) {
                wallpaperIconList.add(String.format("@drawable/static_%s_morning_icon", cityName));
                wallpaperIconList.set(0, String.format("@drawable/static_%s_morning_select_icon", cityName));
            }
            if(dayTime.equals(CommonConstants.WALLPAPER_AFTERNOON)) {
                wallpaperIconList.add(String.format("@drawable/static_%s_afternoon_icon", cityName));
                wallpaperIconList.set(1, String.format("@drawable/static_%s_afternoon_select_icon", cityName));
            }
            if(dayTime.equals(CommonConstants.WALLPAPER_EVENING)) {
                wallpaperIconList.add(String.format("@drawable/static_%s_evening_icon", cityName));
                wallpaperIconList.set(2, String.format("@drawable/static_%s_evening_select_icon", cityName));
            }
            if(dayTime.equals(CommonConstants.WALLPAPER_NIGHT)) {
                wallpaperIconList.add(String.format("@drawable/static_%s_night_icon", cityName));
                wallpaperIconList.set(3, String.format("@drawable/static_%s_night_select_icon", cityName));
            }
            if(dayTime.equals(CommonConstants.WALLPAPER_NIGHT_BLACK)) {
                wallpaperIconList.add(String.format("@drawable/static_%s_night_black_icon", cityName));
                wallpaperIconList.set(4, String.format("@drawable/static_%s_night_black_select_icon", cityName));
            }
            wallpaperIconList.add(dayTime);

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
            View itemView = layoutInflater.inflate(R.layout.item_static_wallpaper, parent, false);
            StaticViewHolder staticViewHolder = new StaticViewHolder(itemView);
            return staticViewHolder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    protected void bindView(CustomRecyclerAdapter.ViewHolder holder, final int position) {
        try {
            final StaticViewHolder staticViewHolder = (StaticViewHolder) holder;
            if(staticViewHolder == null) {
                return;
            }

            String getName = mGetCityName.get(position);
            ArrayList<String> iconNameList = getIconName(mGetCityName.get(position));
            if(iconNameList == null) {
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                staticViewHolder.itemMorningImg.setImageDrawable(getRippledDrawable(iconNameList.get(0), mContext));
                staticViewHolder.itemAfternoonImg.setImageDrawable(getRippledDrawable(iconNameList.get(1), mContext));
                staticViewHolder.itemEveningImg.setImageDrawable(getRippledDrawable(iconNameList.get(2), mContext));
                staticViewHolder.itemNightImg.setImageDrawable(getRippledDrawable(iconNameList.get(3), mContext));
                staticViewHolder.itemNightBlackImg.setImageDrawable(getRippledDrawable(iconNameList.get(4), mContext));
                staticViewHolder.itemPreviewImg.setImageDrawable(getRippledDrawable(iconNameList.get(5), mContext));
            } else {
                staticViewHolder.itemMorningImg.setImageDrawable(getDrawable(iconNameList.get(0), mContext));
                staticViewHolder.itemAfternoonImg.setImageDrawable(getDrawable(iconNameList.get(1), mContext));
                staticViewHolder.itemEveningImg.setImageDrawable(getDrawable(iconNameList.get(2), mContext));
                staticViewHolder.itemNightImg.setImageDrawable(getDrawable(iconNameList.get(3), mContext));
                staticViewHolder.itemNightBlackImg.setImageDrawable(getDrawable(iconNameList.get(4), mContext));
                staticViewHolder.itemPreviewImg.setImageDrawable(getDrawable(iconNameList.get(5), mContext));
            }

            staticViewHolder.itemWallpaperTitle.setText(CommonUtil.getWallpaperTitle(mContext, mGetCityName.get(position)));
            CommonUtil.setRoundedBackground(mContext, staticViewHolder.itemSetButton, "#FF6F43", 12, null, 0);

            staticViewHolder.itemPreviewImg.setTag(position);
            staticViewHolder.itemPreviewImg.setTag(R.id.wallpaper_preview_img, iconNameList.get(6));
            staticViewHolder.itemSetButton.setTag(position);
            staticViewHolder.itemSetButton.setTag(R.id.wallpaper_preview_img, iconNameList.get(6));

            staticViewHolder.itemMorningImg.setTag(position);
            staticViewHolder.itemAfternoonImg.setTag(position);
            staticViewHolder.itemEveningImg.setTag(position);
            staticViewHolder.itemNightImg.setTag(position);
            staticViewHolder.itemNightBlackImg.setTag(position);

            staticViewHolder.itemPreviewImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int tag = (int) view.getTag();
                        String dayTime = (String) view.getTag(R.id.wallpaper_preview_img);
                        String landmark = mGetCityName.get(tag);
                        String uri = createImageUri(landmark, CommonConstants.WALLPAPER_MORNING, false);
                        addPreviewFragment(landmark, dayTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            staticViewHolder.itemSetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int tag = (int) view.getTag();
                        String dayTime = (String) view.getTag(R.id.wallpaper_preview_img);
                        String landmark = mGetCityName.get(tag);
                        String uri = createImageUri(landmark, CommonConstants.WALLPAPER_MORNING, false);
                        addPreviewFragment(landmark, dayTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            staticViewHolder.itemMorningImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                    //    int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                        int tag = (int) view.getTag();
                        String landmark = mGetCityName.get(tag);
                        String uri = createImageUri(landmark, CommonConstants.WALLPAPER_MORNING, false);
                        String selectUri = createImageUri(landmark, CommonConstants.WALLPAPER_MORNING, true);
                        staticViewHolder.itemMorningImg.setImageDrawable(getDrawable(selectUri, mContext));
                        staticViewHolder.itemPreviewImg.setTag(position);
                        staticViewHolder.itemPreviewImg.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_MORNING);
                        staticViewHolder.itemSetButton.setTag(position);
                        staticViewHolder.itemSetButton.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_MORNING);
                        setIconImage(staticViewHolder, landmark, CommonConstants.WALLPAPER_MORNING);
                        setPreviewImage(staticViewHolder, uri);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            staticViewHolder.itemAfternoonImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int tag = (int) view.getTag();
                        String landmark = mGetCityName.get(tag);
                        String uri = createImageUri(landmark, CommonConstants.WALLPAPER_AFTERNOON, false);
                        String selectedUri = createImageUri(landmark, CommonConstants.WALLPAPER_AFTERNOON, true);
                        staticViewHolder.itemAfternoonImg.setImageDrawable(getDrawable(selectedUri, mContext));
                        staticViewHolder.itemPreviewImg.setTag(position);
                        staticViewHolder.itemPreviewImg.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_AFTERNOON);
                        staticViewHolder.itemSetButton.setTag(position);
                        staticViewHolder.itemSetButton.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_AFTERNOON);
                        setIconImage(staticViewHolder, landmark, CommonConstants.WALLPAPER_AFTERNOON);
                        setPreviewImage(staticViewHolder, uri);
//                        addPreviewFragment(landmark, CommonConstants.WALLPAPER_AFTERNOON);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            staticViewHolder.itemEveningImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int tag = (int) view.getTag();
                        String landmark = mGetCityName.get(tag);
                        String uri = createImageUri(landmark, CommonConstants.WALLPAPER_EVENING, false);
                        String selectedUri = createImageUri(landmark, CommonConstants.WALLPAPER_EVENING, true);
                        staticViewHolder.itemEveningImg.setImageDrawable(getDrawable(selectedUri, mContext));
                        staticViewHolder.itemPreviewImg.setTag(position);
                        staticViewHolder.itemPreviewImg.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_EVENING);
                        staticViewHolder.itemSetButton.setTag(position);
                        staticViewHolder.itemSetButton.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_EVENING);
                        setIconImage(staticViewHolder, landmark, CommonConstants.WALLPAPER_EVENING);
                        setPreviewImage(staticViewHolder, uri);
//                        addPreviewFragment(landmark, CommonConstants.WALLPAPER_EVENING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            staticViewHolder.itemNightImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int tag = (int) view.getTag();
                        String landmark = mGetCityName.get(tag);
                        String uri = createImageUri(landmark, CommonConstants.WALLPAPER_NIGHT, false);
                        String selectedUri = createImageUri(landmark, CommonConstants.WALLPAPER_NIGHT, true);
                        staticViewHolder.itemNightImg.setImageDrawable(getDrawable(selectedUri, mContext));
                        staticViewHolder.itemPreviewImg.setTag(position);
                        staticViewHolder.itemPreviewImg.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_NIGHT);
                        staticViewHolder.itemSetButton.setTag(position);
                        staticViewHolder.itemSetButton.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_NIGHT);
                        setIconImage(staticViewHolder, landmark, CommonConstants.WALLPAPER_NIGHT);
                        setPreviewImage(staticViewHolder, uri);
//                        addPreviewFragment(landmark, CommonConstants.WALLPAPER_NIGHT);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            staticViewHolder.itemNightBlackImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int tag = (int) view.getTag();
                        String landmark = mGetCityName.get(tag);
                        String uri = createImageUri(landmark, CommonConstants.WALLPAPER_NIGHT_BLACK, false);
                        String selectedUri = createImageUri(landmark, CommonConstants.WALLPAPER_NIGHT_BLACK, true);
                        staticViewHolder.itemNightBlackImg.setImageDrawable(getDrawable(selectedUri, mContext));
                        staticViewHolder.itemPreviewImg.setTag(position);
                        staticViewHolder.itemPreviewImg.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_NIGHT_BLACK);
                        staticViewHolder.itemSetButton.setTag(position);
                        staticViewHolder.itemSetButton.setTag(R.id.wallpaper_preview_img, CommonConstants.WALLPAPER_NIGHT_BLACK);
                        setIconImage(staticViewHolder, landmark, CommonConstants.WALLPAPER_NIGHT_BLACK);
                        setPreviewImage(staticViewHolder, uri);
//                        addPreviewFragment(landmark, CommonConstants.WALLPAPER_NIGHT);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

//            staticViewHolder.itemMorningImg.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    view.playSoundEffect(android.view.SoundEffectConstants.CLICK);
//                    return false;
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setIconImage(StaticViewHolder staticViewHolder, String landmarkName, String selectImage) {
        try {
            if(!selectImage.equals(CommonConstants.WALLPAPER_MORNING)) {
                String uri = createImageUri(landmarkName, CommonConstants.WALLPAPER_MORNING, false);
                staticViewHolder.itemMorningImg.setImageDrawable(getDrawable(uri, mContext));
            }
            if(!selectImage.equals(CommonConstants.WALLPAPER_AFTERNOON)) {
                String uri = createImageUri(landmarkName, CommonConstants.WALLPAPER_AFTERNOON, false);
                staticViewHolder.itemAfternoonImg.setImageDrawable(getDrawable(uri, mContext));
            }
            if(!selectImage.equals(CommonConstants.WALLPAPER_EVENING)) {
                String uri = createImageUri(landmarkName, CommonConstants.WALLPAPER_EVENING, false);
                staticViewHolder.itemEveningImg.setImageDrawable(getDrawable(uri, mContext));
            }
            if(!selectImage.equals(CommonConstants.WALLPAPER_NIGHT)) {
                String uri = createImageUri(landmarkName, CommonConstants.WALLPAPER_NIGHT, false);
                staticViewHolder.itemNightImg.setImageDrawable(getDrawable(uri, mContext));
            }
            if(!selectImage.equals(CommonConstants.WALLPAPER_NIGHT_BLACK)) {
                String uri = createImageUri(landmarkName, CommonConstants.WALLPAPER_NIGHT_BLACK, false);
                staticViewHolder.itemNightBlackImg.setImageDrawable(getDrawable(uri, mContext));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPreviewImage(StaticViewHolder staticViewHolder, String uri) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                staticViewHolder.itemPreviewImg.setImageDrawable(getRippledDrawable(uri, mContext));
            } else {
                staticViewHolder.itemPreviewImg.setImageDrawable(getDrawable(uri, mContext));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPreviewFragment(String landmark, String dayTime) {
        if(dayTime == null || landmark == null) {
            return;
        }
        try {
            FragmentManager fragmentManager = mFragmentActivity.getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            PreviewWallpaperFragment fragment = new PreviewWallpaperFragment();

            Bundle args = new Bundle();
            args.putString(PreviewWallpaperFragment.DAY_TIME, dayTime);
            args.putString(PreviewWallpaperFragment.LANDMARK, landmark);
            fragment.setArguments(args);

            fragmentTransaction.replace(R.id.main_activity_container, fragment, mContext.getString(R.string.PreviewWallpaperFragmentTag));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
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

//    @Override
//    public long getItemId(int position) {
//        if(mGetName == null || position < 0 || position >= mGetName.size()) {
//            return -1;
//        }
//        try {
//            Long listIndex = mGetName.get(position);
//            if(listIndex == null) {
//                return -1;
//            }
//            return listIndex;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }


    public class StaticViewHolder extends CustomRecyclerAdapter.ViewHolder {
        public ImageView itemPreviewImg;
        public ImageView itemMorningImg;
        public ImageView itemAfternoonImg;
        public ImageView itemEveningImg;
        public ImageView itemNightImg;
        public ImageView itemNightBlackImg;
        public Button itemSetButton;
        public TextView itemWallpaperTitle;

        public StaticViewHolder(View itemView) {
            super(itemView);
            itemPreviewImg = (ImageView) itemView.findViewById(R.id.wallpaper_preview_img);
            itemMorningImg = (ImageView) itemView.findViewById(R.id.wallpaper_morning_img);
            itemAfternoonImg = (ImageView) itemView.findViewById(R.id.wallpaper_afternoon_img);
            itemEveningImg = (ImageView) itemView.findViewById(R.id.wallpaper_evening_img);
            itemNightImg = (ImageView) itemView.findViewById(R.id.wallpaper_night_img);
            itemNightBlackImg = (ImageView) itemView.findViewById(R.id.wallpaper_night_black_img);
            itemSetButton = (Button) itemView.findViewById(R.id.set_button);
            itemWallpaperTitle = (TextView) itemView.findViewById(R.id.wallpaper_title);
        }
    }
}
