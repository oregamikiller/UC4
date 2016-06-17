package com.semidream.UC4;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> list = new ArrayList<String>();
	
	public MyListAdapter(Context context,ArrayList<String> list){
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		String hh = list.get(position);
		TextView h;
		if(view==null){
			
			view = LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
			
			h= (TextView)view.findViewById(R.id.tx3);
			
			view.setTag(h);
		}else{
			h = (TextView)view.getTag();
		}
		
		h.setText(hh);
		return view;
	}

	
}
