package com.example.ankhleu.trackingyourspendingv3.tripdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ankhleu.trackingyourspendingv3.R;

import java.util.ArrayList;

/**
 * Created by Student on 2018/2/5.
 */

public class Adapter_tripshow extends BaseAdapter {

    private ArrayList<String> _listtitle;
    private Context _context;
    public Adapter_tripshow(ArrayList<String> title,Context context)
    {
        _listtitle = title;
        _context = context;
    }

    @Override
    public int getCount() {
        return _listtitle.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater;
        inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.fragment_bill,null,false);

        return view;
    }
}
