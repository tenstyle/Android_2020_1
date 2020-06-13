package ru.tenstyle.weatherforecast;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.tenstyle.weatherforecast.data.Characters;
import ru.tenstyle.weatherforecast.data.WeatherRequest;

import static ru.tenstyle.weatherforecast.MainActivity.CITY;

public class MainFragment extends Fragment {

    private static final String TAG = "410";
    public static final String CITY_INFO = "cityInfo" ;
    private static final int SETTING_CODE = 88;
    private static final String preCity = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String aftCity = ",RU&appid=";
    private static final String API_KEY = "5d9d88c5e2d7bb3ae36457d11b354928";

    private String city;
    private FragmentTransaction fragmentTransaction;
    private CheckBox wind;
    private  CheckBox humidity;
    RecyclerView recyclerView;
    private float temp;
    private String weather;
    private String weatherDescription;
    private float windSpeed;
    private float windDirection;
    private int humidityProc;
    private Characters charact;
    private EditText cityChose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        String[] data = getResources().getStringArray(R.array.cities);
        wind = view.findViewById(R.id.wind);
        humidity = view.findViewById(R.id.humidity);
        recyclerView = view.findViewById(R.id.popular_city);
        cityChose = view.findViewById(R.id.cityNameOn);
        initRecycleView(data);
        Button show = view.findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = cityChose.getText().toString().trim();
                try {
                    final URL uri = new URL(preCity + city + aftCity + API_KEY);
                    final Handler handler = new Handler();
                    new Thread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            HttpsURLConnection urlConnection = null;
                            try{
                                urlConnection = (HttpsURLConnection) uri.openConnection();
                                urlConnection.setRequestMethod("GET");
                                urlConnection.setReadTimeout(10000);
                                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                                String result = in.lines().collect(Collectors.joining());
                                Gson gson = new Gson();
                                final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        temp = weatherRequest.getMain().getTemp();
                                        weather = weatherRequest.getWeather()[0].getMain();
                                        weatherDescription = weatherRequest.getWeather()[0].getDescription();
                                        windSpeed = weatherRequest.getWind().getSpeed();
                                        windDirection = weatherRequest.getWind().getDeg();
                                        humidityProc = (int)weatherRequest.getMain().getHumidity();

                                        charact = new Characters(city, isWindOn(), isHumidityOn(),
                                                temp, weather, weatherDescription, windSpeed,
                                                windDirection, humidityProc);

                                        if(getResources().getConfiguration().orientation
                                                == Configuration.ORIENTATION_PORTRAIT)
                                        {
                                            Intent intent = new Intent(Objects.requireNonNull(
                                                    getActivity()).getApplicationContext(),
                                                    CityWeather.class);
                                            intent.putExtra(CITY, charact);
                                            startActivity(intent);
                                        } else {
                                            fragmentTransaction = Objects.requireNonNull(
                                                    getActivity()).getSupportFragmentManager().
                                                    beginTransaction();
                                            fragmentTransaction.setTransition(
                                                    FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                            Bundle bun = new Bundle();
                                            bun.putParcelable(CITY_INFO, charact);
                                            CityWeatherFragment fr = new CityWeatherFragment();
                                            fr.setArguments(bun);
                                            fragmentTransaction.replace(R.id.weather_fragment, fr);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                        }
                                    }
                                });
                            }catch(Exception e){
                                Log.e(TAG, "Fail connection", e);
                                e.printStackTrace();
                            }finally {
                                if(null != urlConnection){
                                    urlConnection.disconnect();
                                }
                            }
                        }
                    }).start();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTING_CODE){
            Objects.requireNonNull(getActivity()).recreate();
        }
    }

    private boolean isWindOn(){
        return wind.isChecked();
    }

    private boolean isHumidityOn(){
        return humidity.isChecked();
    }

    private WeatherAdapterForCity.OnItemClickListener clickListener = new WeatherAdapterForCity.OnItemClickListener() {
        @Override
        public void onItemClick(View v) {
            TextView b = (TextView) v;
            city = b.getText().toString();
            try {
                final URL uri = new URL(preCity + city + aftCity + API_KEY);
                final Handler handler = new Handler();
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try{
                            urlConnection = (HttpsURLConnection) uri.openConnection();
                            urlConnection.setRequestMethod("GET");
                            urlConnection.setReadTimeout(10000);
                            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    urlConnection.getInputStream()));
                            String result = in.lines().collect(Collectors.joining());
                            Gson gson = new Gson();
                            final WeatherRequest weatherRequest = gson.fromJson(result,
                                    WeatherRequest.class);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    temp = weatherRequest.getMain().getTemp();
                                    weather = weatherRequest.getWeather()[0].getMain();
                                    weatherDescription = weatherRequest.getWeather()[0]
                                            .getDescription();
                                    windSpeed = weatherRequest.getWind().getSpeed();
                                    windDirection = weatherRequest.getWind().getDeg();
                                    humidityProc = (int)weatherRequest.getMain().getHumidity();

                                    charact = new Characters(city, isWindOn(), isHumidityOn(), temp,
                                            weather, weatherDescription, windSpeed, windDirection,
                                            humidityProc);

                                    if(getResources().getConfiguration().orientation ==
                                            Configuration.ORIENTATION_PORTRAIT)
                                    {
                                        Intent intent = new Intent(Objects.requireNonNull(
                                                getActivity()).getApplicationContext(),
                                                CityWeather.class);
                                        intent.putExtra(CITY, charact);
                                        startActivity(intent);
                                    } else {
                                        fragmentTransaction = Objects.requireNonNull(getActivity())
                                                .getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.setTransition(
                                                FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                        Bundle bun = new Bundle();
                                        bun.putParcelable(CITY_INFO, charact);
                                        CityWeatherFragment fr = new CityWeatherFragment();
                                        fr.setArguments(bun);
                                        fragmentTransaction.replace(R.id.weather_fragment, fr);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();
                                    }
                                }
                            });
                        }catch(Exception e){
                            Log.e(TAG, "Fail connection", e);
                            e.printStackTrace();
                        }finally {
                            if(null != urlConnection){
                                urlConnection.disconnect();
                            }
                        }
                    }
                }).start();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    };

    private void initRecycleView(String[] data){

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);

        WeatherAdapterForCity adapter = new WeatherAdapterForCity(data);
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(clickListener);

    }
}