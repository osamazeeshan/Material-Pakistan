package com.materiallandmarks.activity;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.materiallandmarks.R;
import com.materiallandmarks.adapter.CustomPagerAdapter;
import com.materiallandmarks.adapter.LiveWallpaperAdapter;
import com.materiallandmarks.landmarkservice.MinarePakLandmarkService;
import com.materiallandmarks.utility.CommonUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    private LiveWallpaperAdapter mWallpaperAdapter = null;
    private AdView mAdView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            final ImageView splashImageView = (ImageView) findViewById(R.id.main_activity_splash_imageview);

            MobileAds.initialize(this, getString(R.string.admob_app_id));

            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);

            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mAdView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                    Log.i("Ads", "onAdOpened");
                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                    Log.i("Ads", "onAdLeftApplication");
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when when the user is about to return
                    // to the app after tapping on an ad.
                    Log.i("Ads", "onAdClosed");
                }
            });

            if(splashImageView != null) {
                splashImageView.setImageBitmap(CommonUtil.getPhotoFromAssets(this, "splash_image.jpg"));

                splashImageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
                splashImageView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        postOnCreate(splashImageView);
                    }
                }, 2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postOnCreate(final ImageView splashImageView) {
        try {
            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    timer.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            splashImageView.setVisibility(View.GONE);
                            splashImageView.setImageBitmap(null);

                            ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                            setSupportActionBar(toolbar);

                            viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

                            // Give the TabLayout the ViewPager
                            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
                            if(tabLayout != null) {
                                tabLayout.setupWithViewPager(viewPager);
                            }
                        }
                    });
                }
            }, 1200, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStatusBarState(boolean show) {
        try {
            if(!show) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
            else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callWallpaper() {
        try {
            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, MinarePakLandmarkService.class));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean removePreviewFragment() {
        try {
            changeStatusBarState(true);
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.PreviewWallpaperFragmentTag));
            if(fragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                return true;
            }
            return false;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setAdViewVisibility(boolean visible) {
        if(mAdView == null) {
            return;
        }
        try {
            if (visible) {
                mAdView.setVisibility(View.VISIBLE);
            } else {
                mAdView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if(!removePreviewFragment()) {
                finish();
            }
            setAdViewVisibility(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}
