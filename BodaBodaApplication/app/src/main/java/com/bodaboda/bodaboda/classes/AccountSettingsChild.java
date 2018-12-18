package com.bodaboda.bodaboda.classes;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bodaboda.bodaboda.R;
import com.bodaboda.bodaboda.utils.CustomerASExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AccountSettingsChild {

    private Button saveChanges;
    private TextView paymentMethod;
    private TextView verification;
    private Spinner paymentOptions;
    private List<String> spinnerArray = new ArrayList<String>();


    public Button getSaveChanges() {
        return saveChanges;
    }

    public void setSaveChanges(Button saveChanges) {
        this.saveChanges = saveChanges;
    }

    public TextView getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TextView paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TextView getVerification() {
        return verification;
    }

    public void setVerification(TextView verification) {
        this.verification = verification;
    }

    public Spinner getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(Spinner paymentOptions) {
        spinnerArray.add("Visa");
        spinnerArray.add("Cash");


    }
}



