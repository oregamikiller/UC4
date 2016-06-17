package com.semidream.UC4;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


public class MyListViewActivity extends Activity {
	
	ListItemSingleAdapter hc;
	private ListView listview;
	int index;
 	int index1;
	int index2;
	ArrayList<ListItemSingle> list;
	ListItemSingle c;
	HashMap<Integer, ArrayList<ListItemSingle>> map = new HashMap<Integer, ArrayList<ListItemSingle>>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		Intent intent = getIntent();
		index = intent.getIntExtra("index", 0);
		index1 = intent.getIntExtra("index1", 0);
		index2 = intent.getIntExtra("index2", 0);
	

 String source = intent.getStringExtra("source");

	

 	}

	private void init(int i)


{
		
	


}
	
	private void setMap(int i) {
		System.out.println("todo");
	}

}
