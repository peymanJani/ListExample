package com.example.listexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by S_peyman on 3/12/2018.
 */

public class ListAdapter extends BaseAdapter {


    Context context;
    String names[];
    List<ListObject> list;
    private ArrayList<ListObject> arraylist;

    public ListAdapter(Context context,List mylist) {
        this.context = context;
        this.list=mylist;
        this.arraylist = new ArrayList<ListObject>();
        this.arraylist.addAll(mylist);
    }

    @Override
    public int getCount() {
        return list.size();
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

        View rowView = LayoutInflater.from(context).inflate(R.layout.layout_adapter, parent, false);
        TextView txt = (TextView)rowView.findViewById(R.id.name);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.image);
        TextView City=(TextView)rowView.findViewById(R.id.city);
        City.setText(list.get(position).getCity());
        txt.setText(list.get(position).getNames());
        Picasso.get().load(list.get(position).getImage_url()).into(imageView);
        return rowView;
    }


    public void filter(String charText,String RadioText) {

        list.clear();
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        else
        {
            for (ListObject wp : arraylist)
            {
                if (RadioText.equals("نام")) {
                    if (wp.getNames().toLowerCase().contains(charText)) {
                        list.add(wp);
                    }
                }
                else {
                    if (wp.getCity().toLowerCase().contains(charText)) {
                        list.add(wp);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}
