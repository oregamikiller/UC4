package com.semidream.UC4;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItemMultiLineAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<ListItemMultiLine> list = new ArrayList<ListItemMultiLine>();
	
	public ListItemMultiLineAdapter(Context context,ArrayList<ListItemMultiLine> list){
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
		ListItemMultiLine hh = list.get(position);
		H h = null;
		if(view==null){
			h = new H();
			view = LayoutInflater.from(context).inflate(R.layout.multi, parent, false);
			h.pic = (ImageView)view.findViewById(R.id.l1);
			h.title = (TextView)view.findViewById(R.id.title);
			h.addon = (TextView)view.findViewById(R.id.addon);
			h.sub = (TextView)view.findViewById(R.id.sub);
			
			view.setTag(h);
		}else{
			h = (H)view.getTag();
		}
		
		h.pic.setImageResource(Integer.parseInt(hh.getTxPath()));
		h.title.setText(hh.getTitle());
		h.addon.setText(hh.getAddon());
		h.sub.setText(hh.getSub());
		h.url = hh.getUrl();
		
		return view;
	}

	class H{
		ImageView pic;
		TextView title;
		TextView addon;
		TextView sub;
		String url;
	}
}
