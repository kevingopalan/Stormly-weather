package com.nano.snowcast;

import static android.widget.Toast.makeText;

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
import com.nano.snowcast.databinding.ActivityMainBinding;

import java.util.Objects;



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
        CharSequence toasttext = "I am rewriting the forecast code, just wait a bit lol";
        int duration = Toast.LENGTH_SHORT;
        Toast wiptoast = Toast.makeText(this, toasttext, duration);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling api
                wiptoast.show();
            }
        });
    }
}