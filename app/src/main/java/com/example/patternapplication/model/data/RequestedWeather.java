
package com.example.patternapplication.model.data;

import com.example.patternapplication.model.db.DBConstants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DatabaseTable(tableName = DBConstants.DB_TABLE)
public class RequestedWeather {

    @DatabaseField(generatedId = true, columnName = DBConstants.COLUMN_ID)
    private int tableId;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Coord coord;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Sys sys;

    @ForeignCollectionField(eager = false)
    private List<Weather> weather = new ArrayList<Weather>();

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Main main;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Wind wind;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Rain rain;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Clouds clouds;

    @DatabaseField(columnName = DBConstants.COLUMN_DT)
    private Long dt = 0L;

    @DatabaseField(columnName = DBConstants.COLUMN_REQUEST_ID)
    private Long id = 0L;

    @DatabaseField(columnName = DBConstants.COLUMN_NAME)
    private String name = "";

    @DatabaseField(columnName = DBConstants.COLUMN_COD)
    private Long cod = 0L;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * @param coord The coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     * @return The sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * @param sys The sys
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     * @return The weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * @param weather The weather
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * @return The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * @param main The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * @return The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * @param wind The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * @return The rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * @param rain The rain
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * @return The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * @param clouds The clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * @return The dt
     */
    public Long getDt() {
        return dt;
    }

    /**
     * @param dt The dt
     */
    public void setDt(Long dt) {
        this.dt = dt;
    }

    /**
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The cod
     */
    public Long getCod() {
        return cod;
    }

    /**
     * @param cod The cod
     */
    public void setCod(Long cod) {
        this.cod = cod;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
