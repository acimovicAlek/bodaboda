package com.bodaboda.bodaboda.classes;

public class Trip {

    private long TripId;
    private String Status;
    private double Price;
    private Boolean Paid;
    private long StartingLocationId;
    private Location startingLocation;
    private long EndingLocationId;
    private Location endingLocation;
    private long CustomerId;
    private long TaxiId;

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
