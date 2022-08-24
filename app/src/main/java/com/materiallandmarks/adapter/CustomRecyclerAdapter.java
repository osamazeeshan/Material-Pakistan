package com.materiallandmarks.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
	protected Context mContext = null;
	private ArrayList<String> mGetCityName;
	private OnItemClickListener mOnItemClickListener;
	public long mLastClickTime = 0;
	protected abstract CustomRecyclerAdapter.ViewHolder getNewView(ViewGroup parent, int viewType);
	protected abstract void bindView(CustomRecyclerAdapter.ViewHolder holder, int position);
	protected abstract ArrayList<String> getIconName(String cityName);

	public CustomRecyclerAdapter(Context context, ArrayList<String> getName) {
		mContext = context;
		mGetCityName = getName;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
	}

	@Override
	public CustomRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		try {
			return getNewView(parent, viewType);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onBindViewHolder(CustomRecyclerAdapter.ViewHolder holder, int position) {
		try {
			bindView(holder, position);
		} catch(Exception e) {
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

	public class ViewHolder extends RecyclerView.ViewHolder {
		private long mId;
		public ViewHolder(View itemView) {
			super(itemView);
		}

	}

	public static interface OnItemClickListener {
		public void onItemClick(long id);
	}

}
