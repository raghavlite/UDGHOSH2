package com.sharespirit.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.udghosh2.R;
import com.sharespirit.udghosh2.BaseInflaterAdapter;
import com.sharespirit.udghosh2.CardItemData;
import com.sharespirit.udghosh2.IAdapterViewInflater;

public class carder2 implements IAdapterViewInflater<CardItemData>{

	
	@Override
	public View inflate(final BaseInflaterAdapter<CardItemData> adapter, final int pos, View convertView, ViewGroup parent)
	{
		ViewHolder holder;

		if (convertView == null)
		{
			
			
			
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.noti_card, parent, false);
			holder = new ViewHolder(convertView);
		}
		else
		{
			
			Log.d("list debug", "inside covertview != null");
			holder = (ViewHolder) convertView.getTag();
		}

		final CardItemData item = (CardItemData) adapter.getTItem(pos);
		holder.updateDisplay(item);

		return convertView;
	}

	private class ViewHolder
	{
		private View m_rootView;
//		private ImageView m_text1;
    	private TextView m_text2;
		private TextView m_text3;

		public ViewHolder(View rootView)
		{
			m_rootView = rootView;
			//m_text1 = (ImageView) m_rootView.findViewById(R.id.image);
			m_text2 = (TextView) m_rootView.findViewById(R.id.noti_date);
			m_text3 = (TextView) m_rootView.findViewById(R.id.not_card);
			rootView.setTag(this);
		}

		public void updateDisplay(CardItemData item)
		{
			
			//imageLoader.displayImage(imageUrls[position], m_text1, options, animateFirstListener);
			
			//m_text1.setText(item.getText1());
			m_text2.setText(item.getText2());
			m_text3.setText(item.getText3());
		}
	}
	
}
