package ru.tenstyle.weatherforecast;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class DarkClass extends AppCompatActivity {
    private static final String Settings_Preference = "LOGIN";
    private static final String IsDarkTheme = "IS_DARK_THEME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isDarkTheme()){
            setTheme(R.style.Theme_Weather);
        }else{
            setTheme(R.style.Theme_Weather_Day);
        }
    }

    protected boolean isDarkTheme(){
        SharedPreferences sharedPref = getSharedPreferences(Settings_Preference, MODE_PRIVATE);
        return  sharedPref.getBoolean(IsDarkTheme, true);
    }

    protected void setDarkTheme(boolean isDarkTheme){
        SharedPreferences sharedPreferences = getSharedPreferences(Settings_Preference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IsDarkTheme, isDarkTheme);
        editor.apply();
    }
}