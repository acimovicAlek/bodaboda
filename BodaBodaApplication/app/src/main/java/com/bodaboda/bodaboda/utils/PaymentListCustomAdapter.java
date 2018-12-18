package com.bodaboda.bodaboda.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.AccountSettingsItem;
import com.bodaboda.bodaboda.classes.AccountSettingsChild;

import java.util.HashMap;
import java.util.List;

public class PaymentListCustomAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<AccountSettingsItem> listDataHeader;
    private HashMap<AccountSettingsItem, List<AccountSettingsChild>> listHashMap;

    public PaymentListCustomAdapter(Context context, List<AccountSettingsItem> listDataHeader, HashMap<AccountSettingsItem, List<AccountSettingsChild>> listHashMap) {
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
        AccountSettingsChild paymentChild = (AccountSettingsChild) getGroup(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cacc_list_item_expanded_payment_options, null);
        }
        TextView paymentText = (TextView)view.findViewById(R.id.cacc_payment_verified_textView);
        TextView paymentStatus = (TextView)view.findViewById(R.id.cacc_payment_verified_status_textView);
        Spinner spinner = (Spinner)view.findViewById(R.id.cacc_payment_method_spinner);
        Button saveButton = (Button)view.findViewById(R.id.cacc_save_changes_edit_button);
        paymentChild.setVerification(paymentText);
        paymentChild.setPaymentMethod(paymentStatus);
        paymentChild.setPaymentOptions(spinner);
        paymentChild.setSaveChanges(saveButton);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
