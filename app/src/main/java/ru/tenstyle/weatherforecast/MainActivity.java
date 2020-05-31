package ru.tenstyle.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements CityListFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            ForecastFragment forecastDetail = new ForecastFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            forecastDetail.setForecast(id);
            fragmentTransaction.replace(R.id.fragment_container, forecastDetail);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            Intent intent = new Intent(this, ForecastActivity.class);
            intent.putExtra(ForecastActivity.EXTRA_FORECAST_ID, (int) id);
            startActivity(intent);
        }
    }
}