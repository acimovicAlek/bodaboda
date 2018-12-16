package com.bodaboda.bodaboda.classes;

public class CustomerTripItemChild {
    private String fromLocation;
    private String destination;
    private String tripLength;

    public CustomerTripItemChild(String fromLocation, String destination, String tripLength) {
        this.fromLocation = fromLocation;
        this.destination = destination;
        this.tripLength = tripLength;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTripLength() {
        return tripLength;
    }

    public void setTripLength(String tripLength) {
        this.tripLength = tripLength;
    }

}
