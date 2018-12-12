package com.bodaboda.bodaboda;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api {

    @POST("values")
    Call<ResponseBody> loginRequest(
            @Field("username") String username,
            @Field("password") String password

            );

}
