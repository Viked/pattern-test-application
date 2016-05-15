package com.example.patternapplication.model.data;

public class Wind {

    private int id;

    private Double speed = 0.0;

    private Double deg = 0.0;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        if (id != wind.id) return false;
        if (speed != null ? !speed.equals(wind.speed) : wind.speed != null) return false;
        return deg != null ? deg.equals(wind.deg) : wind.deg == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (deg != null ? deg.hashCode() : 0);
        return result;
    }
}
