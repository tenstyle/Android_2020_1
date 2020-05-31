package ru.tenstyle.weatherforecast;

public class WeatherData {
    private String cityName;
    private String celsiusData;
    private String windData;
    private String pressureData;

    public static final WeatherData[] weatherDatas = {
            new WeatherData("Moscow", "+15", "4", "760"),
            new WeatherData("Saint Petersburg", "+20", "6", "758"),
            new WeatherData("Kazan", "+22", "1", "770"),
            new WeatherData("Ekaterinburg", "+10", "26", "746"),
            new WeatherData("Irkutsk", "-2", "18", "780"),
            new WeatherData("Vladivostok", "+18", "3", "760"),
            new WeatherData("Sochi", "+33", "2", "773")
    };

    public WeatherData(String cityName, String celsiusData, String windData, String pressureData) {
        this.cityName = cityName;
        this.celsiusData = celsiusData;
        this.windData = windData;
        this.pressureData = pressureData;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCelsiusData() {
        return celsiusData;
    }

    public String getWindData() {
        return windData;
    }

    public String getPressureData() {
        return pressureData;
    }

    public String toString() {
        return this.cityName;
    }
}
