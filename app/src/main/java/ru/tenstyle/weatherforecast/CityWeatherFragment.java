package ru.tenstyle.weatherforecast;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;
import java.util.Objects;

import ru.tenstyle.weatherforecast.data.Characters;

import static ru.tenstyle.weatherforecast.MainFragment.CITY_INFO;

public class CityWeatherFragment extends Fragment {
    private boolean wind, humidity;
    private TextView city;
    private ProgressBar humidityScale;
    private TextView humidityPercentOnLayout;
    private TextView windSpeedLayout;
    private TextView windDirectionLayout;
    private TextView weatherLayout;
    private TextView tempLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        city = view.findViewById(R.id.cityNameAfter);
        humidityScale = view.findViewById(R.id.humidityProgress);
        humidityPercentOnLayout = view.findViewById(R.id.humidityPercent);
        windSpeedLayout = view.findViewById(R.id.speed);
        windDirectionLayout = view.findViewById(R.id.direction);
        weatherLayout = view.findViewById(R.id.weather);
        tempLayout = view.findViewById(R.id.tempOn);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Bundle bundle = getArguments();
            assert bundle != null;
            Characters characters = bundle.getParcelable(CITY_INFO);
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

            String tempValue = String.format(Locale.getDefault(), "%d", tempCelsius)
                    + "\u2103";
            tempLayout.setText(tempValue);
            humidityScale.setProgress(humidityPercent);
            String humidityValue = String.format(Locale.getDefault(), "%d", humidityPercent)
                    + "%";
            humidityPercentOnLayout.setText(humidityValue);
            String windSpeedValue = String.format(Locale.getDefault(), "%d", windSpeed) + " "
                    + getResources().getString(R.string.speedEd);
            windSpeedLayout.setText(windSpeedValue);
            windDirectionLayout.setText(windDirFromDeg(windDirection));
            weatherLayout.setText(weatherToResources(weather));

            visibleHumidity();
            visibleWind();
        }
    }

    private void visibleHumidity(){
        TextView hum = Objects.requireNonNull(getActivity()).findViewById(R.id.humidityIn);
        TextView humPercent = getActivity().findViewById(R.id.humidityPercent);
        ProgressBar humProgress = getActivity().findViewById(R.id.humidityProgress);

        if(!humidity){
            hum.setVisibility(View.GONE);
            humPercent.setVisibility(View.GONE);
            humProgress.setVisibility(View.GONE);
        }
    }

    private void visibleWind(){
        TextView w = Objects.requireNonNull(getActivity()).findViewById(R.id.windIn);
        TextView wSpeed = getActivity().findViewById(R.id.speed);
        TextView wDirect = getActivity().findViewById(R.id.direction);

        if(!wind){
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

}