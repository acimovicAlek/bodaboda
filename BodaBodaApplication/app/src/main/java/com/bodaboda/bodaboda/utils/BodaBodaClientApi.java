package com.bodaboda.bodaboda.utils;

import com.bodaboda.bodaboda.classes.Login;
import com.bodaboda.bodaboda.classes.RequestTripArguments;
import com.bodaboda.bodaboda.classes.Token;
import com.bodaboda.bodaboda.classes.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BodaBodaClientApi {


    @POST("/api/Users/authenticate")
    Call<Token> loginRequest(@Body Login login);

    @POST("/api/Users")
    Call<User> registerAccount(@Body User user);

    @POST("/api/Trips")
    Call<User> sendRequestedTrip(
            @Header("Authorization") String authToken,
            @Body RequestTripArguments requestTripArguments
    );

    @GET("/api/Users/11")
    Call<User> getUserById(
            @Header("Authorization") String authToken
            //@Path("id") long id
    );

}
