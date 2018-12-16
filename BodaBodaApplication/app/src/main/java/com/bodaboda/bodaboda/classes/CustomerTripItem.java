package com.bodaboda.bodaboda.classes;

public class CustomerTripItem {
    private String customerName;
    private String distanceToCustomer;
    private String estPrice;

    public CustomerTripItem(String customerName, String distanceToCustomer, String estPrice) {
        this.customerName = customerName;
        this.distanceToCustomer = distanceToCustomer;
        this.estPrice = estPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDistanceToCustomer() {
        return distanceToCustomer;
    }

    public void setDistanceToCustomer(String distanceToCustomer) {
        this.distanceToCustomer = distanceToCustomer;
    }

    public String getEstPrice() {
        return estPrice;
    }

    public void setEstPrice(String estPrice) {
        this.estPrice = estPrice;
    }
}
