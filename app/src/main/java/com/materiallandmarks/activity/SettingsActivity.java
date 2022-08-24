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
//            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//            upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
//            getActionBar().setHomeAsUpIndicator(upArrow);

            //   actionBar.setDisplayHomeAsUpEnabled(true);
            // }
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
////                Intent intent = new Intent(this, M.class);
////                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                startActivity(intent);
////                return true;
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
