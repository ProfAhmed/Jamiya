package com.pro.ahmed.jamiya.data.api;

//This class will have the base URL as a static variable and will also provide the APIService interface by with a getAPIService() static method to the rest of our application.

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
