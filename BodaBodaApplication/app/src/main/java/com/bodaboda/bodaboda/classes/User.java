package com.bodaboda.bodaboda.classes;

public class User {

    private enum paymentMethod{
        Cash,
        CreditCard,
        Okapi
    }

    private enum reportReason{
        OffensiveLanguage,
        //Add reporting sort here
    }

    private long UserId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private boolean isDriver;
    private Token token;
    private Location currentLoc;
    private float mileagePrice;
    private float startingFee;

    public void User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Location getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(Location currentLoc) {
        this.currentLoc = currentLoc;
    }

    public float getMileagePrice() {
        return mileagePrice;
    }

    public void setMileagePrice(float mileagePrice) {
        this.mileagePrice = mileagePrice;
    }

    public float getStartingFee() {
        return startingFee;
    }

    public void setStartingFee(float startingFee) {
        this.startingFee = startingFee;
    }

    public void driverBrowseTrips(){

    }

    public void driverFinishTrip(Trip trip){

    }

    public void driverCheckTransactionHistory(){

    }

    public void driverInitiateTrip(Trip trip){

    }

    public void customerReportDriver(User driver, User.reportReason report){

    }

    public void customerModifyCustomerAccount(Customer customer){

    }

    public void customerRequestTrip(Location startLoc, Location destination){

    }

    public void customerFinishTrip(){

    }

    public void customerPay(User.paymentMethod payment){

    }

    public void customerRegisterAsDriver(User user){

    }
}
