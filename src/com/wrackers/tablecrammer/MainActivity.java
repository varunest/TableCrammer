package com.wrackers.tablecrammer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;

import com.wrackers.tablecrammer.numbers.OnNumberSelectedListener;

public class MainActivity extends Activity implements OnNumberSelectedListener,
		GestureDetector.OnGestureListener {

	private GestureDetectorCompat gestureDetector;
	private final static String DEBUG_TAG = "Gestures";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gestureDetector = new GestureDetectorCompat(this, this);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		View v = (View) menu.findItem(R.id.findTable).getActionView();

		final EditText tableSearch = (EditText) v
				.findViewById(R.id.table_search);

		tableSearch.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(tableSearch.getWindowToken(), 0);
					String tableNumber = tableSearch.getText().toString();
					int tn = Integer.parseInt(tableNumber);
					table Table = (table) getFragmentManager()
							.findFragmentById(R.id.table);

					int ct = Table.currentTable();
					ct--;
					Table.setPosition(tn);
					return true;
				}
				return false;
			}
		});
		return true;
		
	}
	
	

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public int OnNumberSelected(int position) {
		// TODO Auto-generated method stub
		Log.d("->", String.valueOf(position));

		

		table Table = (table) getFragmentManager().findFragmentById(R.id.table);

		Table.setPosition(position);

		/*
		 * <fragment android:name="com.wrackers.tablecrammer.table"
		 * android:id="@+id/table" android:layout_weight="9"
		 * android:layout_width="0px" android:layout_height="match_parent" />
		 */

		return 0;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		this.gestureDetector.onTouchEvent(event);
		// Be sure to call the Super class implementation

		return super.onTouchEvent(event);

	}

	public boolean onDown(MotionEvent e) {

		// Log.d(DEBUG_TAG,"onDown : " + e.toString() );
		return true;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public boolean onFling(MotionEvent start, MotionEvent end, float velocityX,
			float velocityY) {

		if ((end.getRawX() - start.getRawX() > 100) && (velocityX > 100)) { // swipe
																			// right
			table Table = (table) getFragmentManager().findFragmentById(
					R.id.table);

			int ct = Table.currentTable();
			ct--;
			Table.setPosition(ct);

		} else { // swipe left
			table Table = (table) getFragmentManager().findFragmentById(
					R.id.table);
			int ct = Table.currentTable();
			ct++;
			Table.setPosition(ct);

		}

		Log.d(DEBUG_TAG, "onFling: " + start.toString() + end.toString());

		return true;

	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
