package com.bodaboda.bodaboda.classes;

public class Location {
    private long LocationId;
    private double Longitude;
    private double Latitude;
    private String LocationType;
    private long UserId;
    private long TTL;

    public Location() {
    }

    public long getLocationId() {
        return LocationId;
    }

    public void setLocationId(long locationId) {
        LocationId = locationId;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getLocationType() {
        return LocationType;
    }

    public void setLocationType(String locationType) {
        LocationType = locationType;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public long getTTL() {
        return TTL;
    }

    public void setTTL(long TTL) {
        this.TTL = TTL;
    }

}
