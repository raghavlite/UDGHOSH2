package com.sharespirit.udghosh2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sharespirit.adapters.CardInflater;
import com.sharespirit.adapters.carder2;

public class BaseInflaterAdapter<T> extends BaseAdapter
{
	private List<T> m_items = new ArrayList<T>();
	private IAdapterViewInflater<T> m_viewInflater;

	public BaseInflaterAdapter(CardInflater cardInflater)
	{
		m_viewInflater = (IAdapterViewInflater<T>) cardInflater;
	}

	public BaseInflaterAdapter(List<T> items, IAdapterViewInflater<T> viewInflater)
	{
		m_items.addAll(items);
		m_viewInflater = viewInflater;
	}

	public BaseInflaterAdapter(carder2 carder2) {
		// TODO Auto-generated constructor stub
		m_viewInflater = (IAdapterViewInflater<T>) carder2;
	}

	public void setViewInflater(IAdapterViewInflater<T> viewInflater, boolean notifyChange)
	{
		m_viewInflater = viewInflater;

		if (notifyChange)
			notifyDataSetChanged();
	}

	public void addItem(T item, boolean notifyChange)
	{
		//m_items.add(item);
		m_items.add(0, item);

		if (notifyChange)
			notifyDataSetChanged();
	}

	public void addItems(List<T> items, boolean notifyChange)
	{
		m_items.addAll(items);

		if (notifyChange)
			notifyDataSetChanged();
	}

	public void clear(boolean notifyChange)
	{
		m_items.clear();

		if (notifyChange)
			notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return m_items.size();
	}

	@Override
	public Object getItem(int pos)
	{
		return getTItem(pos);
	}

	public Object getTItem(int pos)
	{
		return  m_items.get(pos);
	}

	@Override
	public long getItemId(int pos)
	{
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent)
	{
		return m_viewInflater != null ? m_viewInflater.inflate(this, pos, convertView, parent) : null;
	}
}
