package com.example.cardlistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class CardActivity extends Activity {
	private static final String TAG = "CardListActivity";
	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_main);
		listView = (ListView) findViewById(R.id.card_listView);

		listView.addHeaderView(new View(this));
		listView.addFooterView(new View(this));

		cardArrayAdapter = new CardArrayAdapter(getApplicationContext(),
				R.layout.list_item);

		for (int i = 0; i < 2; i++) {
			Card card = new Card("name", "person", "time");
			cardArrayAdapter.add(card);
		}

		listView.setAdapter(cardArrayAdapter);
	
		setListViewHeightBasedOnChildren(listView);

		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(CardActivity.this, "大item", Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	// 建立函數將dp轉換為像素
	public int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	// 建立函數動態設定ListView的高度
	public void setListViewHeightBasedOnChildren(ListView listView) {
		 ListAdapter listAdapter = listView.getAdapter();  
		    if (listAdapter == null) { 
		        return; 
		    } 

		    int totalHeight = 0; 
		    for (int i = 0; i < listAdapter.getCount(); i++) { 
		        View listItem = listAdapter.getView(i, null, listView); 
		        listItem.measure(0, 0); 
		        totalHeight += listItem.getMeasuredHeight(); 
		    } 

		    ViewGroup.LayoutParams params = listView.getLayoutParams(); 
		    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()- 1)); 
		    ((MarginLayoutParams)params).setMargins(10, 10, 10, 10);
		    listView.setLayoutParams(params); 
	}
}
