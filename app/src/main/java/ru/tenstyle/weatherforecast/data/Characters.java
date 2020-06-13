package ru.tenstyle.weatherforecast.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Characters implements Parcelable {
    private String cityName;
    private Boolean isWindOn;
    private Boolean isHumidityOn;
    private float temperature;
    private String weather;
    private String weatherDescription;
    private float windSpeed;
    private float windDirection;
    private int humidity;


    public Characters(String cityName, Boolean isWindOn, Boolean isHumidityOn, float temperature,
                      String weather, String weatherDescription, float windSpeed,
                      float windDirection, int humidity) {
        this.cityName = cityName;
        this.isWindOn = isWindOn;
        this.isHumidityOn = isHumidityOn;
        this.temperature = temperature;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
    }

    private Characters(Parcel parcel){
        cityName = parcel.readString();
        isWindOn = parcel.readInt() == 1;
        isHumidityOn = parcel.readInt() == 1;
        temperature = parcel.readFloat();
        weather = parcel.readString();
        weatherDescription = parcel.readString();
        windSpeed = parcel.readFloat();
        windDirection = parcel.readFloat();
        humidity = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityName);
        dest.writeInt(isWindOn? 1 : 0);
        dest.writeInt(isHumidityOn? 1 : 0);
        dest.writeFloat(temperature);
        dest.writeString(weather);
        dest.writeString(weatherDescription);
        dest.writeFloat(windSpeed);
        dest.writeFloat(windDirection);
        dest.writeInt(humidity);
    }

    public static final Creator<Characters> CREATOR = new Creator<Characters>() {
        @Override
        public Characters createFromParcel(Parcel source) {
            return new Characters(source);
        }

        @Override
        public Characters[] newArray(int size) {
            return new Characters[size];
        }
    };

    public String getCityName() {
        return cityName;
    }

    public Boolean getWindOn() {
        return isWindOn;
    }

    public Boolean getHumidityOn() {
        return isHumidityOn;
    }

    public Float getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindDirection() {
        return windDirection;
    }

    public int getHumidity() {
        return humidity;
    }
}
