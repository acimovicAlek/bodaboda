package com.bodaboda.bodaboda.utils;

import com.bodaboda.bodaboda.classes.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BodaBodaClientApi {

    @FormUrlEncoded
    @POST("/api/Users/authenticate")
    Call<ResponseBody> loginRequest(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("user")
    Call<User> createAccount(@Body User user);

}
