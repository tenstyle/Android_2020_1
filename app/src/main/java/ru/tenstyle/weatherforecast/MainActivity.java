package ru.tenstyle.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView cityName;
    CheckBox checkBoxShowWind, checkBoxShowPressure;
    String pressureCity, speedCity, celsiusCity;

    Pattern checkCityName = Pattern.compile("^.{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        cityName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                validate(tv, checkCityName, getString(R.string.error));
            }
        });

    }

    private void validate(TextView tv, Pattern check, String message){
        String value = tv.getText().toString();
        if (check.matcher(value).matches()){
            hideError(tv);
        }
        else{
            showError(tv, message);
        }
    }


    private void showError(TextView view, String message) {
        view.setError(message);
    }


    private void hideError(TextView view) {
        view.setError(null);
    }



    private void initViews() {
        cityName = findViewById(R.id.cityNameEditText);
//        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] cities = getResources().getStringArray(R.array.cities);
        List<String> cityList = Arrays.asList(cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, cityList);
        cityName.setAdapter(adapter);
    }

    public void onForecastBtnClick(View view) {
        Intent intent = new Intent(this, ForecastActivity.class);
        String[] cities = getResources().getStringArray(R.array.cities);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.cityNameEditText);
        String cityName = autoCompleteTextView.getText().toString();

        if (cityName.equals(cities[0])) {
            celsiusCity = "+15";
            speedCity = "4";
            pressureCity = "760";
        } else if (cityName.equals(cities[1])) {
            celsiusCity = "+20";
            speedCity = "6";
            pressureCity = "758";
        } else if (cityName.equals(cities[2])) {
            celsiusCity = "+22";
            speedCity = "1";
            pressureCity = "770";
        } else if (cityName.equals(cities[3])) {
            celsiusCity = "+10";
            speedCity = "26";
            pressureCity = "746";
        } else if (cityName.equals(cities[4])) {
            celsiusCity = "-2";
            speedCity = "18";
            pressureCity = "780";
        } else if (cityName.equals(cities[5])) {
            celsiusCity = "+18";
            speedCity = "3";
            pressureCity = "760";
        } else if (cityName.equals(cities[6])) {
            celsiusCity = "+33";
            speedCity = "2";
            pressureCity = "773";
        } else {
            celsiusCity = "+463";
            speedCity = "128";
            pressureCity = "2760";
        }

        checkBoxShowWind = findViewById(R.id.checkBoxWind);
        checkBoxShowPressure = findViewById(R.id.checkBoxPressure);

        intent.putExtra(ForecastActivity.CITYNAME, cityName);
        intent.putExtra(ForecastActivity.CELSIUSCITY, celsiusCity);
        intent.putExtra(ForecastActivity.SPEEDCITY, speedCity);
        intent.putExtra(ForecastActivity.PRESSURECITY, pressureCity);
        intent.putExtra(ForecastActivity.CHECKBOXWIND, checkBoxShowWind.isChecked());
        intent.putExtra(ForecastActivity.CHECKBOXPRESSURE, checkBoxShowPressure.isChecked());
        startActivity(intent);
    }

    public void onCloseBtnClick(View view) {
        final Snackbar snackbar = Snackbar.make(view, R.string.are_you_shure, Snackbar.LENGTH_LONG)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getParent().finish();
                    }
                });
        snackbar.show();
    }
}
