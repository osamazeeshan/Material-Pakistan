package com.materiallandmarks.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.MenuItem;

import com.materiallandmarks.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            addPreferencesFromResource(R.xml.settings);
            android.app.ActionBar actionBar = getActionBar();
            if(actionBar != null) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
                    actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>Settings </font>", Html.FROM_HTML_MODE_LEGACY));
                }
                else {
                    actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>Settings </font>"));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
