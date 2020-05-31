package ru.tenstyle.weatherforecast;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ForecastFragment extends Fragment {
    private long forecastId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            forecastId = savedInstanceState.getLong("forecastId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView title = view.findViewById(R.id.cityNameTextView);
            WeatherData weatherData = WeatherData.weatherDatas[(int) forecastId];
            title.setText(weatherData.getCityName());
            TextView celsiusData = view.findViewById(R.id.textViewCelsiusCity);
            celsiusData.setText(weatherData.getCelsiusData());
            TextView windData = view.findViewById(R.id.textSpeedCity);
            windData.setText(weatherData.getWindData());
            TextView pressureData = view.findViewById(R.id.textPressureCity);
            pressureData.setText(weatherData.getPressureData());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putLong("forecastId", forecastId);
    }

    public void setForecast(long id) {
        this.forecastId = id;
    }
}