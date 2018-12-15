package com.bodaboda.bodaboda.utils;

import com.bodaboda.bodaboda.classes.Login;
import com.bodaboda.bodaboda.classes.Token;
import com.bodaboda.bodaboda.classes.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BodaBodaClientApi {


    @POST("/api/Users/authenticate")
    Call<Token> loginRequest(@Body Login login);

    @POST("/api/Users")
    Call<User> registerAccount(@Body User user);

}
