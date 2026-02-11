package com.example.listycity;

/**
 * This is a class that defines a city
 */
public class City implements Comparable<City> {
    private String city;
    private String province;

    /**
     *
     *
     * @param city city name
     * @param province province/state where city is
     */
    City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    /**
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(City o) {
        return this.city.compareTo(((City) o).getCity()); // this.city refers to the city name
    }
}
