package com.bodaboda.bodaboda;

public class Trip {
    private Location startingLoc;
    private Location destination;
    private Customer customer;
    private Driver driver;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
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
