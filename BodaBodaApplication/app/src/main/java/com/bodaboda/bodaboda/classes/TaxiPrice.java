package com.bodaboda.bodaboda.classes;

public class TaxiPrice {
    private long TaxiPriceId;
    private long UserId;
    private double StartingPrice;
    private double PricePerUnit;
    private double PricePerHour;
    private double SpecialPrice;

    public long getTaxiPriceId() {
        return TaxiPriceId;
    }

    public void setTaxiPriceId(long taxiPriceId) {
        TaxiPriceId = taxiPriceId;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userID) {
        UserId = userID;
    }

    public double getStartingPrice() {
        return StartingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        StartingPrice = startingPrice;
    }

    public double getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        PricePerUnit = pricePerUnit;
    }

    public double getPricePerHour() {
        return PricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        PricePerHour = pricePerHour;
    }

    public double getSpecialPrice() {
        return SpecialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        SpecialPrice = specialPrice;
    }
}
