
package com.example.patternapplication.model.data;

public class Rain {

    private Long time = 0L;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rain rain = (Rain) o;

        return time != null ? time.equals(rain.time) : rain.time == null;

    }

    @Override
    public int hashCode() {
        return time != null ? time.hashCode() : 0;
    }
}
