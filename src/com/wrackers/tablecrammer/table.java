package com.wrackers.tablecrammer;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class table extends Fragment {

	int ct;

	public int currentTable() {
		return ct;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.table, container, false);

	}

	// private int position = 0;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public void setPosition(int position) {
		ct = position;
		// this.position =position;
		// Log.d("position", String.valueOf(this.position) );
		for (int i = 1; i <= 10; i++) {

			TextView view = (TextView) getActivity().findViewById(
					R.id.row1 + i - 1);
			WindowManager wm = (WindowManager) getView().getContext()
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;
			view.setTextSize( height/20);
			int result = position * i;
			String row = "      " + position + " X " + i + " = " + result;
			view.setText(row);
		}
	}

}
