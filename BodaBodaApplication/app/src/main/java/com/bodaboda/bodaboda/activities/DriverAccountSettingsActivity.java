package com.bodaboda.bodaboda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.AccountSettingsItem;
import com.bodaboda.bodaboda.classes.CustomerTripItemChild;
import com.bodaboda.bodaboda.utils.CustomerASExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriverAccountSettingsActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private CustomerASExpandableListAdapter listAdapter;
    private List<AccountSettingsItem> listDataHeader;
    private HashMap<AccountSettingsItem, List<CustomerTripItemChild>> listHash;
    private View itemView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account_settings);

        listView = (ExpandableListView)findViewById(R.id.dacc_expandableListView);
        init();
        listAdapter = new CustomerASExpandableListAdapter(this, listDataHeader,listHash);
        listView.setAdapter(listAdapter);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if(expandableListView.isGroupExpanded(i))
                {
                    expandableListView.collapseGroup(i);
                    view.findViewById(R.id.cacc_expand_item_button).setVisibility(view.VISIBLE);
                    view.findViewById(R.id.cacc_collapse_item_button).setVisibility(view.INVISIBLE);
                }
                else
                {
                    expandableListView.expandGroup(i);
                    view.findViewById(R.id.cacc_expand_item_button).setVisibility(view.INVISIBLE);
                    view.findViewById(R.id.cacc_collapse_item_button).setVisibility(view.VISIBLE);
                }
                return true;
            }
        });
    }

    private void initGoBackButton(){
        Button goBackButton = (Button)findViewById(R.id.driversettings_back_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DriverAccountSettingsActivity.this, DriverMainActivity.class);
                DriverAccountSettingsActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void init() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        initGoBackButton();
        AccountSettingsItem editAccount = new AccountSettingsItem(this.getResources().getDrawable(R.drawable.baseline_account_circle_black_48), "Account options");
        AccountSettingsItem editPayment = new AccountSettingsItem(this.getResources().getDrawable(R.drawable.baseline_monetization_on_black_48dp), "Payment options");
        AccountSettingsItem editVehicle = new AccountSettingsItem(this.getResources().getDrawable(R.drawable.baseline_motorcycle_black_48), "Vehicle settings");
        AccountSettingsItem help = new AccountSettingsItem(this.getResources().getDrawable(R.drawable.baseline_help_black_48dp), "Help");
        CustomerTripItemChild ctic = new CustomerTripItemChild("Köping", "Västerås", "10km");

        listDataHeader.add(editAccount);
        listDataHeader.add(editPayment);
        listDataHeader.add(editVehicle);
        listDataHeader.add(help);

        List<CustomerTripItemChild> mugabe = new ArrayList<>();
        mugabe.add(ctic);

        List<CustomerTripItemChild> jonathan = new ArrayList<>();
        jonathan.add(ctic);

        List<CustomerTripItemChild> chingiz = new ArrayList<>();
        chingiz.add(ctic);

        listHash.put(listDataHeader.get(0), mugabe);
        listHash.put(listDataHeader.get(1), jonathan);
        listHash.put(listDataHeader.get(2), chingiz);

    }
}
