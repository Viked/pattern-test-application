package com.example.patternapplication.model.data;

public class Main {

    private Double temp = 0.0;

    private Integer humidity= 0;

    private Double pressure= 0.0;

    private Double temp_min = 0.0;

    private Double temp_max= 0.0;


    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTempMax() {
        return temp_max;
    }

    public void setTempMax(Double tempMax) {
        this.temp_max = tempMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        if (temp != null ? !temp.equals(main.temp) : main.temp != null) return false;
        if (humidity != null ? !humidity.equals(main.humidity) : main.humidity != null)
            return false;
        if (pressure != null ? !pressure.equals(main.pressure) : main.pressure != null)
            return false;
        if (temp_min != null ? !temp_min.equals(main.temp_min) : main.temp_min != null) return false;
        return temp_max != null ? temp_max.equals(main.temp_max) : main.temp_max == null;

    }

    @Override
    public int hashCode() {
        int result = temp != null ? temp.hashCode() : 0;
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (temp_min != null ? temp_min.hashCode() : 0);
        result = 31 * result + (temp_max != null ? temp_max.hashCode() : 0);
        return result;
    }
}
