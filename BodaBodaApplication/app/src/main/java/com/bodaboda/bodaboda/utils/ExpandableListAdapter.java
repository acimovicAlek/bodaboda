package com.bodaboda.bodaboda.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import android.widget.TextView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.CustomerTripItem;
import com.bodaboda.bodaboda.classes.CustomerTripItemChild;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CustomerTripItem> listDataHeader;
    private HashMap<CustomerTripItem, List<CustomerTripItemChild>> listHashMap;

    public ExpandableListAdapter(Context context, List<CustomerTripItem> listDataHeader, HashMap<CustomerTripItem, List<CustomerTripItemChild>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(this.listDataHeader.get(i)).get(i1); // i = listItem, i1 = Expanded item
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        CustomerTripItem customerItem = (CustomerTripItem) getGroup(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.driver_trip_item, null);
        }
        TextView customerName = (TextView)view.findViewById(R.id.driver_request_item_customername_textView);
        TextView tripPrice = (TextView)view.findViewById(R.id.driver_item_estimatedprice_textView);
        TextView distanceToCustomer = (TextView)view.findViewById(R.id.driver_request_item_distancetocustomer_textView);
        customerName.setTypeface(null, Typeface.BOLD);
        customerName.setText(customerItem.getCustomerName());
        distanceToCustomer.setText(customerItem.getDistanceToCustomer());
        tripPrice.setText(customerItem.getEstPrice());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final CustomerTripItemChild customerChildItem = (CustomerTripItemChild) getChild(i, i1);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.driver_trip_item_expanded, null);
        }
        TextView childTripStart = (TextView)view.findViewById(R.id.driver_trip_start_textView);
        TextView childTripDest = (TextView)view.findViewById(R.id.driver_trip_destination_textView);
        TextView childTripLength = (TextView)view.findViewById(R.id.driver_trip_length);
        childTripStart.setText(customerChildItem.getFromLocation());
        childTripDest.setText(customerChildItem.getDestination());
        childTripLength.setText(customerChildItem.getTripLength());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {

        return true;
    }
}
