package com.pro.ahmed.jamiya.data.api;

import com.pro.ahmed.jamiya.data.models.NewGroup;
import com.pro.ahmed.jamiya.data.models.NewUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    // TODO: here create requests
    @GET("/API/Users/Login")
    Call<String> getLoginUserId(@Query("userName") String userName,
                                @Query("password") String password,
                                @Query("deviceToken") String deviceToken,
                                @Query("key") String apiKey);

    @POST("/API/Users/Register")
    Call<Integer> addUser(@Body NewUser userInfo, @Query("key") String apiKey);

    @POST("/API/Groups/AddGroup")
    Call<Integer> addGroup(@Body NewGroup newGroup, @Query("key") String apiKey);
}
