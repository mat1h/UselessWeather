package com.teespire.uselessweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teespire.elements.SearchElement;

import java.util.ArrayList;

/**
 * Created by Sohaib Basit on 02/07/15.
 */
public class SearchListAdapter extends BaseAdapter {

    private ArrayList<SearchElement> searchElementArrayList;

    public ArrayList<SearchElement> getSearchElementArrayList() {
        return searchElementArrayList;
    }

    public void setSearchElementArrayList(ArrayList<SearchElement> searchElementArrayList) {
        this.searchElementArrayList = searchElementArrayList;
    }

    @Override
    public int getCount() {
        return searchElementArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            view = layoutInflater.inflate(R.layout.cell_search_result, viewGroup, false);
        }

        SearchElement searchElement = searchElementArrayList.get(position);

        TextView textViewCityName = (TextView)view.findViewById(R.id.lbl_cityName);
        TextView textViewLatitude = (TextView)view.findViewById(R.id.lbl_latitude);
        TextView textViewLongitude = (TextView)view.findViewById(R.id.lbl_longitude);

        textViewCityName.setText(searchElement.getCityName());
        textViewLatitude.setText(searchElement.getLatitude());
        textViewLongitude.setText(searchElement.getLongitude());

        return view;
    }
}
