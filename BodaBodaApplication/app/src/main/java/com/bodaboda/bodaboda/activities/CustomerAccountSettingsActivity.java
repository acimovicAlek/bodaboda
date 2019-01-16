package com.bodaboda.bodaboda.activities;

import android.accounts.Account;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.classes.AccountSettingsItem;
import com.bodaboda.bodaboda.classes.CustomerTripItemChild;
import com.bodaboda.bodaboda.classes.AccountSettingsChild;
import com.bodaboda.bodaboda.utils.CustomerASExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bodaboda.bodaboda.activities.MainActivity.token;

public class CustomerAccountSettingsActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private CustomerASExpandableListAdapter listAdapter;
    private AccountSettingsChild paymentChild;
    private List<AccountSettingsItem> listDataHeader;
    private HashMap<AccountSettingsItem, List<AccountSettingsChild>> listHash;
    private View itemView;
    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account_settings);

        listView = (ExpandableListView)findViewById(R.id.cacc_expandableListView);
        init();
        listAdapter = new CustomerASExpandableListAdapter(this, listDataHeader,listHash);
        listView.setAdapter(listAdapter);
        logOut = findViewById(R.id.customer_logout_button);


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

    private void initLogOutButton()
    {
        logOut = findViewById(R.id.customer_logout_button);
        logOut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                MainActivity.token.setToken(null);
                Intent logOutIntent = new Intent(CustomerAccountSettingsActivity.this, MainActivity.class);
                CustomerAccountSettingsActivity.this.startActivity(logOutIntent);
            }
        });
    }

    private void initGoBackButton(){
        Button goBackButton = (Button)findViewById(R.id.cacc_go_back_button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(CustomerAccountSettingsActivity.this, CustomerMainActivity.class);
                CustomerAccountSettingsActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void init() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        initGoBackButton();
        AccountSettingsItem editAccount = new AccountSettingsItem(this.getResources().getDrawable(R.drawable.baseline_account_circle_black_48), "Account options");
        AccountSettingsItem editPayment = new AccountSettingsItem(this.getResources().getDrawable(R.drawable.baseline_monetization_on_black_48dp), "Payment options");
        AccountSettingsItem help = new AccountSettingsItem(this.getResources().getDrawable(R.drawable.baseline_help_black_48dp), "Help");
        //CustomerTripItemChild ctic = new CustomerTripItemChild("Köping", "Västerås", "10km");

        listDataHeader.add(editAccount);
        listDataHeader.add(editPayment);
        listDataHeader.add(help);

        List<AccountSettingsChild> mugabe = new ArrayList<>();
        mugabe.add(paymentChild);

        List<AccountSettingsChild> jonathan = new ArrayList<>();
        jonathan.add(paymentChild);

        List<AccountSettingsChild> chingiz = new ArrayList<>();
        chingiz.add(paymentChild);

        listHash.put(listDataHeader.get(0), mugabe);
        listHash.put(listDataHeader.get(1), jonathan);
        listHash.put(listDataHeader.get(2), chingiz);

    }
}
