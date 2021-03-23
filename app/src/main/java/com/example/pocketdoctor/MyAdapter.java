package com.example.pocketdoctor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<User> arrayList;
    public MyAdapter(Context context,ArrayList<User> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.doctorlistview_layout, null);
        TextView tFullName = (TextView)convertView.findViewById(R.id.fullName);
        TextView tAddress = (TextView)convertView.findViewById(R.id.address);
        TextView tDoctorID = (TextView)convertView.findViewById(R.id.doctorId);

        User user = arrayList.get(position);
        tDoctorID.setText(user.getUserId());
        tFullName.setText(user.getFirstName()+" "+user.getLastName());
        tAddress.setText(user.getAddress());

        return convertView;
    }
    @Override
    public int getCount(){
        return this.arrayList.size();
    }


}
