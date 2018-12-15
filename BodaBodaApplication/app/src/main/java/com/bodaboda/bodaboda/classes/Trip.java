package com.bodaboda.bodaboda.classes;

public class Trip {
    private LocationClass startingLoc;
    private LocationClass destination;
    private Long customerID;
    private String customerName;
    private Long driverID;
    private String driverName;
    private float distanceToCustomer;
    private float tripLength;
    private float estPrice;
    private int id;

    public void Trip(){

    }

    public LocationClass getStartingLoc() {
        return startingLoc;
    }

    public void setStartingLoc(LocationClass startingLoc) {
        this.startingLoc = startingLoc;
    }

    public LocationClass getDestination() {
        return destination;
    }

    public void setDestination(LocationClass destination) {
        this.destination = destination;
    }

    public Long getCustomerID() { return customerID; }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getDriverID() {
        return driverID;
    }

    public void setDriver(Long driver) {
        this.driverID = driverID;
    }

    public float getEstPrice() {
        return estPrice;
    }

    public String getEstPriceString() {
        return String.valueOf(estPrice);
    }

    public void setEstPrice(float estPrice) {
        this.estPrice = estPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public float getDistanceToCustomer() {
        return distanceToCustomer;
    }

    public String getDistanceToCustomerString() {
        return String.valueOf(distanceToCustomer);
    }

    public void setDistanceToCustomer(float distanceToCustomer) {
        this.distanceToCustomer = distanceToCustomer;
    }

    public float getTripLength() {
        return tripLength;
    }

    public String getTripLengthString() {
        return String.valueOf(tripLength);
    }

    public void setTripLength(float tripLength) {
        this.tripLength = tripLength;
    }
}
