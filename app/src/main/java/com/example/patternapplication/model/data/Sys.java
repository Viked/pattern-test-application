
package com.example.patternapplication.model.data;

public class Sys {

    private String country = "";

    private Long sunrise = 0L;

    private Long sunset = 0L;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (country != null ? !country.equals(sys.country) : sys.country != null) return false;
        if (sunrise != null ? !sunrise.equals(sys.sunrise) : sys.sunrise != null) return false;
        return sunset != null ? sunset.equals(sys.sunset) : sys.sunset == null;

    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (sunrise != null ? sunrise.hashCode() : 0);
        result = 31 * result + (sunset != null ? sunset.hashCode() : 0);
        return result;
    }
}
