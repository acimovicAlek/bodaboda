package com.bodaboda.bodaboda.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.AccountSettingsItem;
import com.bodaboda.bodaboda.classes.CustomerTripItemChild;

import java.util.HashMap;
import java.util.List;

public class CustomerASExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<AccountSettingsItem> listDataHeader;
    private HashMap<AccountSettingsItem, List<CustomerTripItemChild>> listHashMap;

    public CustomerASExpandableListAdapter(Context context, List<AccountSettingsItem> listDataHeader, HashMap<AccountSettingsItem, List<CustomerTripItemChild>> listHashMap) {
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
        return listHashMap.get(this.listDataHeader.get(i)).get(i1);
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
        AccountSettingsItem accountSettingsItem = (AccountSettingsItem) getGroup(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cacc_list_item, null);
        }
        ImageView iconImage = (ImageView) view.findViewById(R.id.cacc_icon_itemView);
        TextView settingsTitle = (TextView)view.findViewById(R.id.cacc_settings_item_textView);
        settingsTitle.setTypeface(null, Typeface.BOLD);
        settingsTitle.setText(accountSettingsItem.getSettingsTitle());
        iconImage.setImageDrawable(accountSettingsItem.getIconImage());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        AccountSettingsItem accountSettingsItem = (AccountSettingsItem) getGroup(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cacc_list_item_expanded, null);
        }
        /*ImageView iconImage = (ImageView) view.findViewById(R.id.cacc_icon_itemView);
        TextView settingsTitle = (TextView)view.findViewById(R.id.cacc_settings_item_textView);
        settingsTitle.setTypeface(null, Typeface.BOLD);
        settingsTitle.setText(accountSettingsItem.getSettingsTitle());
        iconImage.setImageDrawable(accountSettingsItem.getIconImage());*/

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
