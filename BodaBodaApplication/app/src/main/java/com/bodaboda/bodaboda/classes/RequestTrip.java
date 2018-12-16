package com.bodaboda.bodaboda.classes;

import java.sql.Date;

public class RequestTrip {

    private String Status;
    private Boolean Paid;
    private LocationClass StartingLocation;
    private LocationClass EndingLocation;
    private long CustomerId;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Boolean getPaid() {
        return Paid;
    }

    public void setPaid(Boolean paid) {
        Paid = paid;
    }

    public LocationClass getStartingLocation() {
        return StartingLocation;
    }

    public void setStartingLocation(LocationClass startingLocation) {
        StartingLocation = startingLocation;
    }

    public LocationClass getEndingLocation() {
        return EndingLocation;
    }

    public void setEndingLocation(LocationClass endingLocation) {
        EndingLocation = endingLocation;
    }

    public long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(long customerId) {
        CustomerId = customerId;
    }

    /*
    static public class TripStatus{
        public static string REQUESTED { get { return "REQUESTED"; } }

        public static string ACCEPTED { get { return "ACCEPTED"; } }

        public static string IN_PROGRESS { get { return "IN_PROGRESS"; } }

        public static string COMPLETED { get { return "COMPLETED"; } }
    }
    */
}
