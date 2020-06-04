package ru.tenstyle.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class CityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView cityRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_city,
                container, false);
        String[] cityNames = new String[WeatherData.weatherDatas.length];
        for (int i = 0; i < cityNames.length; i++) {
            cityNames[i] = WeatherData.weatherDatas[i].getCityName();
        }

        int[] cityImages = new int[WeatherData.weatherDatas.length];
        for (int i = 0; i < cityImages.length; i++) {
            cityImages[i] = WeatherData.weatherDatas[i].getImageResourceId();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(cityNames, cityImages);
        cityRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        cityRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ForecastActivity.class);
                intent.putExtra(ForecastActivity.EXTRA_FORECAST_ID, position);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });

        return cityRecycler;
    }
}