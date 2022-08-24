package com.materiallandmarks.utility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TabHost;

public class CustomTabHost extends TabHost {

	@SuppressWarnings("UnusedDeclaration")
	public CustomTabHost(Context context) {
		super(context);
	}

	@SuppressWarnings("UnusedDeclaration")
	public CustomTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onTouchModeChanged(boolean isInTouchMode) {
		//leave it empty here. It looks that when you use hard keyboard, this method would have be called and the focus will be taken.
	}
}
