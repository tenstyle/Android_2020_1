package ru.tenstyle.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForecastActivity extends AppCompatActivity {

    public static final String CITYNAME = "Your city name";
    public static final String CELSIUSCITY = "0";
    public static final String SPEEDCITY = "5";
    public static final String PRESSURECITY = "760";
    public static final String CHECKBOXWIND = "false";
    public static final String CHECKBOXPRESSURE = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        recievedData();
        forecastImageSet();
        setWindViewsVisibility();
        setPressureViewsVisibility();
    }

    private void recievedData() {
        Intent intent = getIntent();

        String cityName = intent.getStringExtra(CITYNAME);
        TextView cityNameTextView = findViewById(R.id.cityNameTextView);
        cityNameTextView.setText(cityName);

        String celsiusCity = intent.getStringExtra(CELSIUSCITY);
        TextView cityCelsius = findViewById(R.id.textViewCelsiusCity);
        cityCelsius.setText(celsiusCity);

        String speedCity = intent.getStringExtra(SPEEDCITY);
        TextView citySpeed = findViewById(R.id.textSpeedCity);
        citySpeed.setText(speedCity);

        String pressureCity = intent.getStringExtra(PRESSURECITY);
        TextView cityPressure = findViewById(R.id.textPressureCity);
        cityPressure.setText(pressureCity);

    }

    private void setWindViewsVisibility() {
        Intent intent = getIntent();
        if (intent.getBooleanExtra(CHECKBOXWIND, false)) {
            TextView textViewWind = findViewById(R.id.textViewWind);
            textViewWind.setVisibility(View.VISIBLE);
            TextView citySpeed = findViewById(R.id.textSpeedCity);
            citySpeed.setVisibility(View.VISIBLE);
            TextView textViewMPS = findViewById(R.id.textViewMPS);
            textViewMPS.setVisibility(View.VISIBLE);
        }
    }

    private void setPressureViewsVisibility() {
        Intent intent = getIntent();
        if (intent.getBooleanExtra(CHECKBOXPRESSURE, false)) {
            TextView textViewAir = findViewById(R.id.textViewAir);
            textViewAir.setVisibility(View.VISIBLE);
            TextView cityPressure = findViewById(R.id.textPressureCity);
            cityPressure.setVisibility(View.VISIBLE);
            TextView textViewMmHg = findViewById(R.id.textViewMmHg);
            textViewMmHg.setVisibility(View.VISIBLE);
        }
    }

    private void forecastImageSet() {
        ImageView forecastImage = findViewById(R.id.forecastImageView);
        String[] cities = getResources().getStringArray(R.array.cities);
        TextView cityNameTextView = findViewById(R.id.cityNameTextView);
        String cityName = cityNameTextView.getText().toString();

        if (cityName.equals(cities[0])) {
            forecastImage.setImageResource(R.drawable.heavy_rain);
        } else if (cityName.equals(cities[1])) {
            forecastImage.setImageResource(R.drawable.sunny);
        } else if (cityName.equals(cities[2])) {
            forecastImage.setImageResource(R.drawable.rainbow);
        } else if (cityName.equals(cities[3])) {
            forecastImage.setImageResource(R.drawable.windy);
        } else if (cityName.equals(cities[4])) {
            forecastImage.setImageResource(R.drawable.snow);
        } else if (cityName.equals(cities[5])) {
            forecastImage.setImageResource(R.drawable.cloudy);
        } else if (cityName.equals(cities[6])) {
            forecastImage.setImageResource(R.drawable.heatwave);
        } else {
            forecastImage.setImageResource(R.drawable.tornado);
        }
    }
}
