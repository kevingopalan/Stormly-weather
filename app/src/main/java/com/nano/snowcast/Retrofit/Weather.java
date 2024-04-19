package com.nano.snowcast.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    @SerializedName("dt")
    private List<Weather> weatherList;
    @SerializedName("description")
    String description;
    public List<Weather> getWeatherList() { return weatherList; }

    public void setWeatherList(List<Weather> weatherList) { this.weatherList = weatherList; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}

