package com.jinfulin.quick_master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * 新增预约
 * Created by king on 2015/12/3.
 */
public class AddAppointAdapter extends BaseAdapter {
    private int personId;
    private  Context context;
    private  ArrayList<com.jinfulin.quick_master.beans.AppointInfo.Person> list;
    private ViewHolder holder;
    private boolean goEdit;
    private int listSize = 4;

    public AddAppointAdapter(Context context, ArrayList<com.jinfulin.quick_master.beans.AppointInfo.Person> list) {
        this.context = context;
        this.list = list;

    }


    @Override
    public int getCount() {
        return listSize;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = View.inflate(context,R.layout.item_addappoint, null);
//            holder.tv_addName = (TextView) convertView.findViewById(R.id.tv_addName);
//            holder.et_cardNum = (TextView) convertView.findViewById(R.id.et_cardNum);
//            holder.et_userName = (TextView) convertView.findViewById(R.id.et_userName);
//            holder.tv_identify = (TextView) convertView.findViewById(R.id.tv_identify);
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
////
//        ArrayList<String> list_num = new ArrayList<String>();
//        list_num.add("一");
//        list_num.add("二");
//        list_num.add("三");
//        list_num.add("四");
//        holder.tv_addName.setText("客人" +list_num.get(position));
//        if (list==null){
//            return convertView;
//        }
//        AppointInfo.Person person = list.get(position);
//        if (person!=null){
//            holder.et_cardNum.setText(person.card);
//            holder.et_userName.setText(person.name);
//            holder.tv_identify.setText(person.identityName);
//
//        }

        return convertView;
    }


    public void setData(ArrayList<com.jinfulin.quick_master.beans.AppointInfo.Person> list){
        this.list = list;
        this.notifyDataSetChanged();

    }
    public void setSize(int listSize){
        this.listSize = listSize;
        this.notifyDataSetChanged();

    }

    private class ViewHolder {
        private TextView tv_addName;
        private TextView et_cardNum;
        private TextView et_userName;
        private TextView tv_identify;
    }


}
