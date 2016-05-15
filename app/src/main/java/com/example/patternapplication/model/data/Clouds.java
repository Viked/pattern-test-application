package com.example.patternapplication.model.data;

public class Clouds {

    private Long all = 0L;

    public Long getAll() {
        return all;
    }

    public void setAll(Long all) {
        this.all = all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds clouds = (Clouds) o;

        return all != null ? all.equals(clouds.all) : clouds.all == null;

    }

    @Override
    public int hashCode() {
        return all != null ? all.hashCode() : 0;
    }
}
