package com.bodaboda.bodaboda.Interfaces;

import com.bodaboda.bodaboda.Models.Account;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BodaBodaClientApi {

    @FormUrlEncoded
    @POST("authenticate")
    Call<ResponseBody> loginRequest(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("account")
    Call<Account> createAccount(@Body Account account);

}
