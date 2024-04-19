package com.nano.snowcast.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastExample {
    @SerializedName("list")
    private List<Weather> weatherList;


    public List<Weather> getWeatherList() { return weatherList; }

    public void setWeatherList(List<Weather> weatherList) { this.weatherList = weatherList; }
}
