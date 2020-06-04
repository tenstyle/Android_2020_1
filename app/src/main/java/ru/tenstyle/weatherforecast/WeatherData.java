package ru.tenstyle.weatherforecast;

public class WeatherData {
    private String cityName;
    private String celsiusData;
    private String windData;
    private String pressureData;
    private int imageResourceId;

    //    Данный код вызывает падение программы при запуске. Не могу побороть static

//    private static Context context;
//
//    public static final City[] cities = {
//            new City(context.getResources().getString(R.string.moscow), R.drawable.moscow),
//            new City(context.getResources().getString(R.string.saint_petersburg), R.drawable.spb),
//            new City(context.getResources().getString(R.string.kazan), R.drawable.kazan),
//            new City(context.getResources().getString(R.string.ekaterinburg), R.drawable.ekat),
//            new City(context.getResources().getString(R.string.irkutsk), R.drawable.irkutsk),
//            new City(context.getResources().getString(R.string.vladivostok), R.drawable.vladik),
//            new City(context.getResources().getString(R.string.sochi), R.drawable.sochi)
//    };

    public static final WeatherData[] weatherDatas = {
            new WeatherData("Moscow", "+15", "4", "760", R.drawable.moscow),
            new WeatherData("Saint Petersburg", "+20", "6", "758", R.drawable.spb),
            new WeatherData("Kazan", "+22", "1", "770", R.drawable.kazan),
            new WeatherData("Ekaterinburg", "+10", "26", "746", R.drawable.ekat),
            new WeatherData("Irkutsk", "-2", "18", "780", R.drawable.irkutsk),
            new WeatherData("Vladivostok", "+18", "3", "760", R.drawable.vladik),
            new WeatherData("Sochi", "+33", "2", "773", R.drawable.sochi)
    };

    public WeatherData(String cityName, String celsiusData, String windData, String pressureData, int imageResourceId) {
        this.cityName = cityName;
        this.celsiusData = celsiusData;
        this.windData = windData;
        this.pressureData = pressureData;
        this.imageResourceId = imageResourceId;
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

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.cityName;
    }
}
