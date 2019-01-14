package com.bodaboda.bodaboda.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaxiPrice implements Serializable {
    private long taxiPriceId;
    private long userId;
    private double startingPrice;
    private double pricePerUnit;
    private double pricePerHour;
    private double specialPrice;


    public TaxiPrice(long taxiPriceId, long userId, double startingPrice, double pricePerUnit, double pricePerHour, double specialPrice) {
        this.taxiPriceId = taxiPriceId;
        this.userId = userId;
        this.startingPrice = startingPrice;
        this.pricePerUnit = pricePerUnit;
        this.pricePerHour = pricePerHour;
        this.specialPrice = specialPrice;
    }

    public TaxiPrice() {
    }

    public long getTaxiPriceId() {
        return taxiPriceId;
    }

    public void setTaxiPriceId(long taxiPriceId) {
        this.taxiPriceId = taxiPriceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }
}
