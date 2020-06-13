package ru.tenstyle.weatherforecast;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Locale;

import ru.tenstyle.weatherforecast.data.Characters;

import static ru.tenstyle.weatherforecast.MainActivity.CITY;


public class CityWeather extends DarkClass {

    private boolean wind, humidity;
    private ImageView imageWeather;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        } else {
            Characters characters = getIntent().getParcelableExtra(CITY);
            setContentView(R.layout.activity_city_weather);

            TextView city = findViewById(R.id.cityNameAfter);
            TextView tempOnLayout = findViewById(R.id.tempOn);
            ProgressBar scaleInLayout = findViewById(R.id.humidityProgress);
            TextView humidityPercentOnLayout = findViewById(R.id.humidityPercent);
            TextView windSpeedOnLayout = findViewById(R.id.speed);
            TextView windDirectionOnLayout = findViewById(R.id.direction);
            TextView weatherLayout = findViewById(R.id.weather);
            imageWeather = findViewById(R.id.image_weather);


            assert characters != null;
            String cityName = characters.getCityName();
            city.setText(cityName);
            wind = characters.getWindOn();
            humidity = characters.getHumidityOn();
            float temp = (float) (characters.getTemperature() - 273.15);
            int tempCelsius = (int) temp;
            String weather = characters.getWeather();
            int windSpeed = (int) characters.getWindSpeed();
            float windDirection;
            int humidityPercent = characters.getHumidity();
            windDirection = characters.getWindDirection();


            String temperatureValue = String.format(Locale.getDefault(), "%d", tempCelsius)
                    + "\u2103";
            tempOnLayout.setText(temperatureValue);
            scaleInLayout.setProgress(humidityPercent);
            String humidityPercentValue = String.format(Locale.getDefault(), "%d",
                    humidityPercent) + "%";
            humidityPercentOnLayout.setText(humidityPercentValue);
            String windSpeedValue = String.format(Locale.getDefault(), "%d", windSpeed) + " "
                    + getResources().getString(R.string.speedEd);
            windSpeedOnLayout.setText(windSpeedValue);
            windDirectionOnLayout.setText(windDirFromDeg(windDirection));
            weatherLayout.setText(weatherToResources(weather));

            visibleHumidity();
            visibleWind();
            setImageWeather(weather);
        }
    }


    private void visibleHumidity() {
        TextView hum = findViewById(R.id.humidityIn);
        TextView humPercent = findViewById(R.id.humidityPercent);
        ProgressBar humProgress = findViewById(R.id.humidityProgress);

        if (!humidity) {
            hum.setVisibility(View.GONE);
            humPercent.setVisibility(View.GONE);
            humProgress.setVisibility(View.GONE);
        }
    }

    private void visibleWind() {
        TextView w = findViewById(R.id.windIn);
        TextView wSpeed = findViewById(R.id.speed);
        TextView wDirect = findViewById(R.id.direction);

        if (!wind) {
            w.setVisibility(View.GONE);
            wSpeed.setVisibility(View.GONE);
            wDirect.setVisibility(View.GONE);
        }
    }

    private String windDirFromDeg(float deg) {
        String result;
        if (deg >= 11.25 && deg < 33.75) {
            result = getResources().getString(R.string.nne);
        } else if (deg >= 33.75 && deg < 56.25) {
            result = getResources().getString(R.string.ne);
        } else if (deg >= 56.25 && deg < 78.75) {
            result = getResources().getString(R.string.ene);
        } else if (deg >= 78.75 && deg < 101.25) {
            result = getResources().getString(R.string.e);
        } else if (deg >= 101.25 && deg < 123.75) {
            result = getResources().getString(R.string.ese);
        } else if (deg >= 123.75 && deg < 146.25) {
            result = getResources().getString(R.string.se);
        } else if (deg >= 146.25 && deg < 168.75) {
            result = getResources().getString(R.string.sse);
        } else if (deg >= 168.75 && deg < 191.25) {
            result = getResources().getString(R.string.s);
        } else if (deg >= 191.25 && deg < 213.75) {
            result = getResources().getString(R.string.ssw);
        } else if (deg >= 213.75 && deg < 236.25) {
            result = getResources().getString(R.string.sw);
        } else if (deg >= 236.25 && deg < 258.75) {
            result = getResources().getString(R.string.wsw);
        } else if (deg >= 258.75 && deg < 281.25) {
            result = getResources().getString(R.string.w);
        } else if (deg >= 281.25 && deg < 303.75) {
            result = getResources().getString(R.string.wnw);
        } else if (deg >= 303.75 && deg < 326.25) {
            result = getResources().getString(R.string.nw);
        } else if (deg >= 326.25 && deg < 348.75) {
            result = getResources().getString(R.string.nnw);
        } else {
            result = getResources().getString(R.string.n);
        }
        return result;
    }

    private String weatherToResources(String weather) {
        switch (weather) {
            case "Clouds":
                weather = getResources().getString(R.string.cloudy);
                break;
            case "Rain":
                weather = getResources().getString(R.string.rain);
                break;
            case "Sun":
                weather = getResources().getString(R.string.sun);
                break;
            case "Clear":
                weather = getResources().getString(R.string.clear);
                break;
            case "Snow":
                weather = getResources().getString(R.string.snow);
                break;
            case "Fog":
                weather = getResources().getString(R.string.fog);
                break;
        }

        return weather;
    }

    private void setImageWeather(String weather) {
        switch (weather) {
            case "Clouds":
                imageWeather.setImageDrawable(getDrawable(R.drawable.cloudy));
                break;
            case "Rain":
                imageWeather.setImageDrawable(getDrawable(R.drawable.rain));
                break;
            case "Clear":
                imageWeather.setImageDrawable(getDrawable(R.drawable.sun));
                break;
        }
    }
}