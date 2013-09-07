package com.wrackers.tablecrammer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class numbers extends ListFragment {

	OnNumberSelectedListener mListener ;
	
	
	public interface OnNumberSelectedListener{
		public int OnNumberSelected(int position);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try{
			mListener = (OnNumberSelectedListener) activity;
		}
		catch(Exception e){
			
		}
	}

	String[] arrayNumbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.numbers, container, false);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, arrayNumbers));
		//getListView().setFastScrollEnabled(true);
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
			mListener.OnNumberSelected(position+1);
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	
}
