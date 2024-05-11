package com.nano.snowcast;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nano.snowcast.Retrofit.ApiClient;
import com.nano.snowcast.Retrofit.ApiInterface;
import com.nano.snowcast.Retrofit.ForecastExample;
import com.nano.snowcast.Retrofit.ForecastInterface;
import com.nano.snowcast.Retrofit.Example;
import com.nano.snowcast.databinding.ActivityMainBinding;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    //initialize weather app home elements
    TextView tempText, tempText2, temp1text, descText , humidityText;
    Button search;
    EditText textField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //android's stuff blah blah blah
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //setting values for weather app
        TextView logoTextView = findViewById(R.id.text_logo);
        logoTextView.setText(R.string.stormly);
        search = findViewById(R.id.search);
        tempText = findViewById(R.id.text_home);
        tempText2 = findViewById(R.id.text_nowtemp);
        descText = findViewById(R.id.text_desc);
        humidityText = findViewById(R.id.text_humidity);
        textField = findViewById(R.id.text_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling api
                getWeatherData(textField.getText().toString().trim());
                getForecastData(textField.getText().toString().trim());
            }
        });
    }
    private void getWeatherData(String name){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.body() != null) {
                    tempText.setText(String.format("%sº", response.body().getMain().getTemp()));
                    tempText2.setText(String.format("%sº", response.body().getMain().getTemp()));
                    descText.setText(String.format("Feels like %sº", response.body().getMain().getFeels_like()));
                    humidityText.setText(String.format("Humidity: %s%%", response.body().getMain().getHumidity()));
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
    // Forecast function (having a little trouble with this because everything is in a list for forecast gson)
    private void getForecastData(String name){
        ForecastInterface apiInterface = ApiClient.getClient().create(ForecastInterface.class);
        Call<ForecastExample> call = apiInterface.getWeatherData(name);
        call.enqueue(new Callback<ForecastExample>() {
            @Override
            public void onResponse(Call<ForecastExample> call, Response<ForecastExample> response) {

                assert response.body() != null;
                temp1text.setText(String.format(response.body().getWeatherList().get(0).getDescription()));
                Log.d("DATA", response.body().getWeatherList().get(0).getDescription());

            }

            @Override
            public void onFailure(Call<ForecastExample> call, Throwable t) {

            }
        });
    }
}