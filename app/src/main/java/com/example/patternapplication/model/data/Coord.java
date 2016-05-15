
package com.example.patternapplication.model.data;

public class Coord {

    private Double lon = 0.0;

    private Double lat = 0.0;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (lon != null ? !lon.equals(coord.lon) : coord.lon != null) return false;
        return lat != null ? lat.equals(coord.lat) : coord.lat == null;

    }

    @Override
    public int hashCode() {
        int result = lon != null ? lon.hashCode() : 0;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        return result;
    }
}
