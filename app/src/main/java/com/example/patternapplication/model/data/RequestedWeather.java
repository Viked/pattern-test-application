package com.example.patternapplication.model.data;

import java.util.ArrayList;
import java.util.List;

public class RequestedWeather {

    private Coord coord;

    private Sys sys;

    private List<Weather> weather = new ArrayList<>();

    private Main main;

    private Wind wind;

    private Rain rain;

    private Clouds clouds;

    private Long dt = 0L;

    private Long id = 0L;

    private String name = "";

    private Long cod = 0L;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestedWeather that = (RequestedWeather) o;

        if (coord != null ? !coord.equals(that.coord) : that.coord != null) return false;
        if (sys != null ? !sys.equals(that.sys) : that.sys != null) return false;
        if (weather != null ? !weather.equals(that.weather) : that.weather != null) return false;
        if (main != null ? !main.equals(that.main) : that.main != null) return false;
        if (wind != null ? !wind.equals(that.wind) : that.wind != null) return false;
        if (rain != null ? !rain.equals(that.rain) : that.rain != null) return false;
        if (clouds != null ? !clouds.equals(that.clouds) : that.clouds != null) return false;
        if (dt != null ? !dt.equals(that.dt) : that.dt != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return cod != null ? cod.equals(that.cod) : that.cod == null;

    }

    @Override
    public int hashCode() {
        int result = coord != null ? coord.hashCode() : 0;
        result = 31 * result + (sys != null ? sys.hashCode() : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + (wind != null ? wind.hashCode() : 0);
        result = 31 * result + (rain != null ? rain.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        result = 31 * result + (dt != null ? dt.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cod != null ? cod.hashCode() : 0);
        return result;
    }
}
