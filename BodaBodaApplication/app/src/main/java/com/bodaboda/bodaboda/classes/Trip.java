package com.bodaboda.bodaboda.classes;

public class Trip {
    private Location startingLoc;
    private Location destination;
    private User customer;
    private User driver;
    private float estPrice;
    private int id;

    public void Trip(){

    }

    public Location getStartingLoc() {
        return startingLoc;
    }

    public void setStartingLoc(Location startingLoc) {
        this.startingLoc = startingLoc;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
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
