package com.bodaboda.bodaboda;

public class Customer extends Account {

    private enum paymentMethod{
        Cash,
        CreditCard,
        Okapi
    }

    private enum reportReason{
        OffensiveLanguage,
        //Add reporting sort here
    }

    public void reportDriver(Driver driver, reportReason report){

    }

    public void modifyCustomerAccount(Customer customer){

    }

    public void requestTrip(Location startLoc, Location destination){

    }

    public void finishTrip(){

    }

    public void pay(paymentMethod payment){

    }

    public void registerAsDriver(Account account){

    }
}
