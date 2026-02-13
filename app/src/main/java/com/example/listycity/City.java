package com.example.listycity;

import java.util.Objects;

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
     * basically a sort
     *
     * @param o city object
     * @return negative if city comes before the other city, 0 if they're equal, positive if city comes after the other city
     */
    @Override
    public int compareTo(City o) {
        return this.city.compareTo(((City) o).getCity()); // this.city refers to the city name
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city1 = (City) o;

        return Objects.equals(city, city1.city) && Objects.equals(province, city1.province);
    }


    @Override
    public int hashCode() {
        return Objects.hash(city, province);
    }
}
