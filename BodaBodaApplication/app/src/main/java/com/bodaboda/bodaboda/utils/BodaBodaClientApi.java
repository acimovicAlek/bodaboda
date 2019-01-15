package com.bodaboda.bodaboda.utils;

import com.bodaboda.bodaboda.classes.Location;
import com.bodaboda.bodaboda.classes.Login;
import com.bodaboda.bodaboda.classes.Token;
import com.bodaboda.bodaboda.classes.Trip;
import com.bodaboda.bodaboda.classes.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BodaBodaClientApi {


    @POST("/api/Users/authenticate")
    Call<Token> loginRequest(@Body Login login);

    @POST("/api/Users")
    Call<User> registerAccount(
            @Header("Content-Type") String value,
            @Body User user
    );

    @GET("/api/Users/{id}" )
    Call<User> getUserById(
            @Header("Authorization") String authToken,
            @Path("id") long id
    );

    @POST("/api/Location")
    Call<Location> sendLocation(
            @Header("Authorization") String authToken,
            @Body Location location
    );

    @GET("/api/Location/{id}")
    Call<Location> getLocation(
            @Header("Authorization") String authToken,
            @Path("id") long id
    );

    @POST("/api/Trips")
    Call<Trip> requestTrip(
            @Header("Authorization") String authToken,
            @Body Trip trip
    );

    @GET("/api/Trips/requested")
    Call<List<Trip>> getRequestedTrips(
            @Header("Authorization") String authToken
    );

}
