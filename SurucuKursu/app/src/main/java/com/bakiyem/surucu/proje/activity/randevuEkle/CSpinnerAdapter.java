package com.bakiyem.surucu.proje.activity.randevuEkle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bakiyem.surucu.proje.R;

import java.util.List;

public class CSpinnerAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final List<String> listOfSpinnerData;
    private final Context context;

    public CSpinnerAdapter(RandevuEkleActivity con, List<String> listOfSpinnerData) {
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(con);
        this.listOfSpinnerData = listOfSpinnerData;
        this.context = con;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listOfSpinnerData.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ListContent holder;
        View v = convertView;
        if (v == null) {
            v = mInflater.inflate(R.layout.item_country_spinner, null);
            holder = new ListContent();

            holder.name = v.findViewById(R.id.spinnerItem);

            v.setTag(holder);
        } else {

            holder = (ListContent) v.getTag();
        }

        Typeface myFont = Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf");

        holder.name.setTypeface(myFont);
        holder.name.setText("" + listOfSpinnerData.get(position));

        return v;
    }

}

class ListContent {

    TextView name;

}