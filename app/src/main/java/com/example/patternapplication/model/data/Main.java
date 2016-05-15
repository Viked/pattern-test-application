package com.example.patternapplication.model.data;

public class Main {

    private Double temp = 0.0;

    private Integer humidity= 0;

    private Double pressure= 0.0;

    private Double tempMin= 0.0;

    private Double tempMax= 0.0;


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

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
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
        if (tempMin != null ? !tempMin.equals(main.tempMin) : main.tempMin != null) return false;
        return tempMax != null ? tempMax.equals(main.tempMax) : main.tempMax == null;

    }

    @Override
    public int hashCode() {
        int result = temp != null ? temp.hashCode() : 0;
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (tempMin != null ? tempMin.hashCode() : 0);
        result = 31 * result + (tempMax != null ? tempMax.hashCode() : 0);
        return result;
    }
}
