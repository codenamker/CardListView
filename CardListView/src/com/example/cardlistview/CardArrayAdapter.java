package com.example.cardlistview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import android.widget.TextView;

public class CardArrayAdapter extends ArrayAdapter<Card> {
	private static final String TAG = "CardArrayAdapter";
	private List<Card> cardList = new ArrayList<Card>();
	Context context;
	static class CardViewHolder {
		TextView line1;
		TextView line2;
		TextView line3;
		Button btn1;
	}

	public CardArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		this.context=context;
	}

	@Override
	public void add(Card object) {
		cardList.add(object);
		super.add(object);
	}

	@Override
	public int getCount() {
		return this.cardList.size();
	}

	@Override
	public Card getItem(int index) {
		return this.cardList.get(index);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CardViewHolder viewHolder;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.list_item, parent, false);
			viewHolder = new CardViewHolder();
			viewHolder.line1 = (TextView) row.findViewById(R.id.line1);
			viewHolder.line2 = (TextView) row.findViewById(R.id.line2);
			viewHolder.line3 = (TextView) row.findViewById(R.id.line3);
			viewHolder.btn1= (Button) row.findViewById(R.id.button1);
			row.setTag(viewHolder);
		} else {
			viewHolder = (CardViewHolder) row.getTag();
		}
		Card card = getItem(position);
		viewHolder.line1.setText(card.getLine1());
		viewHolder.line2.setText(card.getLine2());
		viewHolder.line3.setText(card.getLine3());
		viewHolder.btn1.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

            	Toast.makeText(context, "item內的Button", Toast.LENGTH_LONG)
				.show();

            }         

        });     
		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}
