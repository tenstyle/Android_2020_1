package ru.tenstyle.weatherforecast;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

public class CityListFragment extends ListFragment {

    interface Listener {
        void itemClicked(long id);
    };

    private Listener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] cities = new String[WeatherData.weatherDatas.length];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = WeatherData.weatherDatas[i].getCityName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                cities);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener)context;
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }
}
