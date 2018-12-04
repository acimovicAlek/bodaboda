package com.bodaboda.bodaboda;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BodaBodaClient {

    //Add real url to server here!!!
    private static final String BASE_URL = "https://192.168.1.70:5001/api/";

    private static BodaBodaClient mInstance;
    private Retrofit retrofit;

    private BodaBodaClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized BodaBodaClient getInstance(){
        if(mInstance == null){
            mInstance = new BodaBodaClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
