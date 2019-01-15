package com.bodaboda.bodaboda.classes;

import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("tripId")
    private long TripId;

    @SerializedName("status")
    private String Status;

    @SerializedName("price")
    private double Price;

    @SerializedName("paid")
    private Boolean Paid;

    @SerializedName("startingLocationId")
    private long StartingLocationId;

    @SerializedName("startingLocation")
    private Location startingLocation;

    @SerializedName("endingLocationId")
    private long EndingLocationId;

    @SerializedName("endingLocation")
    private Location endingLocation;

    @SerializedName("customerId")
    private long CustomerId;

    @SerializedName("customer")
    private User Customer;

    @SerializedName("taxiId")
    private long TaxiId;

    @SerializedName("taxi")
    private User Taxi;

    public Trip() {
    }

    public Location getEndingLocation() {
        return endingLocation;
    }

    public void setEndingLocation(Location endingLocation) {
        this.endingLocation = endingLocation;
    }

    public Location getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(Location startingLocation) {
        this.startingLocation = startingLocation;
    }

    public long getTripId() {
        return TripId;
    }

    public void setTripId(long tripId) {
        TripId = tripId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Boolean getPaid() {
        return Paid;
    }

    public void setPaid(Boolean paid) {
        Paid = paid;
    }

    public long getStartingLocationId() {
        return StartingLocationId;
    }

    public void setStartingLocationId(long startingLocationId) {
        StartingLocationId = startingLocationId;
    }

    public long getEndingLocationId() {
        return EndingLocationId;
    }

    public void setEndingLocationId(long endingLocationId) {
        EndingLocationId = endingLocationId;
    }

    public long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(long customerId) {
        CustomerId = customerId;
    }

    public long getTaxiId() {
        return TaxiId;
    }

    public void setTaxiId(long taxiId) {
        TaxiId = taxiId;
    }

    public User getCustomer() {
        return Customer;
    }

    public void setCustomer(User customer) {
        Customer = customer;
    }

    public User getTaxi() {
        return Taxi;
    }

    public void setTaxi(User taxi) {
        Taxi = taxi;
    }

    /*
    static public class TripStatus{
        public static string REQUESTED { get { return "REQUESTED"; } }

        public static string PROPOSED { get { return "PROPOSED"; } }

        public static string ACCEPTED { get { return "ACCEPTED"; } }

        public static string IN_PROGRESS { get { return "IN_PROGRESS"; } }

        public static string COMPLETED { get { return "COMPLETED"; } }
    }
    */
}
