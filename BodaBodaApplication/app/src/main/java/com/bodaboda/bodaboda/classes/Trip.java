package com.bodaboda.bodaboda.classes;

public class Trip {
    private LocationClass startingLoc;
    private LocationClass destination;
    private Long customerID;
    private Long driverID;
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

    public void setEstPrice(float estPrice) {
        this.estPrice = estPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
