package com.jinfulin.quick_master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfulin.quick_master.R;

import java.util.List;

public class AdapterDialogSelectTxt extends BaseAdapter {

	private List<Item> mList;
	private  Context context;
	private Object mDefaultName;

	public AdapterDialogSelectTxt(Context context, List<Item> list, Object defaultName) {
	    this.context = context;
	    this.mList = list;
		this.mDefaultName = defaultName;
	}
	public AdapterDialogSelectTxt(Context context, List<Item> list) {
	    this.context = context;
	    this.mList = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_dialog_select_txt, null);
			ViewHolder holder = new ViewHolder();
			holder.txtContent = (TextView) convertView.findViewById(R.id.dialog_text_content);
			holder.imgSelect = (ImageView) convertView
					.findViewById(R.id.dialog_selected);
			holder.divide = convertView.findViewById(R.id.divide);
			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Item item = (Item) getItem(position);
		holder.txtContent.setText(item.name);
		if(position < getCount() - 1 ){
		    holder.divide.setVisibility(View.VISIBLE);
		}else{
		    holder.divide.setVisibility(View.GONE);
		}
		if (mDefaultName != null && mDefaultName.equals(item.object)) {
			holder.imgSelect.setVisibility(View.VISIBLE);
		} else {
		    holder.imgSelect.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		if (position < 0 || position >= mList.size())
			return null;
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public static class Item {
		public String name;
		public Object object;
	}

	static class ViewHolder {
		public TextView txtContent;
		public ImageView imgSelect;
		public View divide;
	}
	
}
