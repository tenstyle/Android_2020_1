package ru.tenstyle.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class ForecastActivity extends AppCompatActivity {
    public static final String EXTRA_FORECAST_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }


        ForecastFragment frag = (ForecastFragment)
                getSupportFragmentManager().findFragmentById(R.id.forecast_frag);
        int forecastId = (int) getIntent().getExtras().get(EXTRA_FORECAST_ID);
        frag.setForecast(forecastId);
    }
}
