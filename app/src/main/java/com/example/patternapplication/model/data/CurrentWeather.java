package com.example.patternapplication.model.data;

import com.example.patternapplication.model.db.DBConstants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 1 on 15.05.2016.
 */
@DatabaseTable(tableName = DBConstants.DB_TABLE)
public class CurrentWeather {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private double longitude = 0.0;

    @DatabaseField
    private double latitude = 0.0;

    @DatabaseField
    private String cloudsType = "";

    @DatabaseField
    private long cloudsValue = 0L;

    @DatabaseField
    private double temperature = 0.0;

    @DatabaseField
    private double temperatureMin = 0.0;

    @DatabaseField
    private double temperatureMax = 0.0;

    @DatabaseField
    private double humidity = 0.0;

    @DatabaseField
    private double pressure = 0.0;

    @DatabaseField
    private long rainTime = 0L;

    @DatabaseField
    private long dt = 0L;

    @DatabaseField
    private long requestID = 0L;

    @DatabaseField
    private String name = "";

    @DatabaseField
    private long cod = 0L;

    @DatabaseField
    private int weatherID = 0;

    @DatabaseField
    private String weatherMain = "";

    @DatabaseField
    private String weatherDescription = "";

    @DatabaseField
    private String weatherIcon = "";

    @DatabaseField
    private String country = "";

    @DatabaseField
    private long sunrise = 0L;

    @DatabaseField
    private long sunset = 0L;

    @DatabaseField
    private double windSpeed = 0.0;

    @DatabaseField
    private double windDeg = 0.0;

    public String getCloudsType() {
        return cloudsType;
    }

    public void setCloudsType(String cloudsType) {
        this.cloudsType = cloudsType;
    }

    public long getCloudsValue() {
        return cloudsValue;
    }

    public void setCloudsValue(long cloudsValue) {
        this.cloudsValue = cloudsValue;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public long getRainTime() {
        return rainTime;
    }

    public void setRainTime(long rainTime) {
        this.rainTime = rainTime;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public int getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(int weatherID) {
        this.weatherID = weatherID;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
