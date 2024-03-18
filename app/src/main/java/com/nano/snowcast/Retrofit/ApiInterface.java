package com.nano.snowcast.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?&appid=a7b6b9afe7bd471b10175c9743ddb5b3&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
