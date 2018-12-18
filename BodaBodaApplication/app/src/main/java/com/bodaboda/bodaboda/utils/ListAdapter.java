package com.bodaboda.bodaboda.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.Trip;

import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {

    List<Trip> trips;
    Context mContext;

    public ListAdapter(@NonNull Context context, List<Trip> tripItems) {
        super(context, R.layout.driver_trip_item);
        this.trips = tripItems;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return trips.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert mInflater != null;

            convertView = mInflater.inflate(R.layout.driver_trip_item, parent, false);

            mViewHolder.mCustomerName = (TextView) convertView.findViewById(R.id.driver_request_item_customername_textView);
            mViewHolder.mDistanceToCustomer = (TextView) convertView.findViewById(R.id.driver_request_item_distancetocustomer_textView);
            mViewHolder.mPrice = (TextView) convertView.findViewById(R.id.driver_item_estimatedprice_textView);

            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        mViewHolder.mCustomerName.setText("CustomerName");
        mViewHolder.mDistanceToCustomer.setText("2 km");
        mViewHolder.mPrice.setText("250 rwf");


        return convertView;
    }

    static class ViewHolder{
        TextView mCustomerName;
        TextView mDistanceToCustomer;
        TextView mPrice;
    }
}
