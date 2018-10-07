package com.pro.ahmed.jamiya.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//This class will create a singleton of Retrofit in the method getClient(String baseUrl) and return it to the caller
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            /// TODO: UnComment this cause out Memory Error
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
