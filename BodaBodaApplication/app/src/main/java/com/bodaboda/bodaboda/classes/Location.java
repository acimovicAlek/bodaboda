package com.bodaboda.bodaboda.classes;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("locationId")
    private long LocationId;

    @SerializedName("longitude")
    private double Longitude;

    @SerializedName("latitude")
    private double Latitude;

    @SerializedName("locationType")
    private String LocationType;

    @SerializedName("userId")
    private long UserId;

    @SerializedName("ttl")
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
