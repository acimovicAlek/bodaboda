package com.bodaboda.bodaboda;

public class Location {
    private float longitude;
    private float latitude;
    private String address;

    public void Location(){

    }

    public float getLongitude(){
        return longitude;
    }

    public void setLongitude(float longitude){
        this.longitude = longitude;
    }

    public float getLatitude(){
        return latitude;
    }

    public void setLatitude(float latitude){
        this.latitude = latitude;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

}
