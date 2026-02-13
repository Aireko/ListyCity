package com.example.listycity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityListTest {
    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());
        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);
        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    @Test
    void testGetCities() {
        CityList cityList = mockCityList();
        // This line checks if the first city in the cityList (retrieved by cityList.getCities().get(0))
        // is the same as the city returned by mockCity()
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));
        // This pushes down the original city
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);
        // Now the original city should be at position 1
        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    // hasCity
    @Test
    void testHasCity_WhenCityExists() {
        CityList cityList = mockCityList();
        City edmonton = mockCity();

        assertTrue(cityList.hasCity(edmonton), "Should return true when city exists in list");
    }

    @Test
    void testHasCity_WhenCityDoesNotExist() {
        CityList cityList = mockCityList();
        City vancouver = new City("Vancouver", "British Columbia");

        assertFalse(cityList.hasCity(vancouver), "Should return false when city does not exist in list");
    }

    @Test
    void testHasCity_EmptyList() {
        CityList cityList = new CityList();
        City edmonton = mockCity();

        assertFalse(cityList.hasCity(edmonton), "Should return false when list is empty");
    }

    @Test
    void testHasCity_WithEqualCityObject() {
        CityList cityList = mockCityList();
        // Create a new City object with same values but different instance
        City edmontonCopy = new City("Edmonton", "Alberta");

        assertTrue(cityList.hasCity(edmontonCopy), "Should return true for city with same name and province");
    }

    // delete function

    @Test
    void testDelete_WhenCityExists() {
        CityList cityList = new CityList();
        City edmonton = new City("Edmonton", "Alberta");
        City calgary = new City("Calgary", "Alberta");
        City vancouver = new City("Vancouver", "British Columbia");

        cityList.add(edmonton);
        cityList.add(calgary);
        cityList.add(vancouver);

        assertEquals(3, cityList.getCities().size(), "List should have 3 cities before deletion");

        cityList.delete(calgary);

        assertEquals(2, cityList.getCities().size(), "List should have 2 cities after deletion");
        assertFalse(cityList.hasCity(calgary), "Deleted city should no longer be in the list");
        assertTrue(cityList.hasCity(edmonton), "Other cities should still be in the list");
        assertTrue(cityList.hasCity(vancouver), "Other cities should still be in the list");
    }

    @Test
    void testDelete_ThrowsException_WhenCityDoesNotExist() {
        CityList cityList = mockCityList();
        City vancouver = new City("Vancouver", "British Columbia");

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(vancouver);
        }, "Should throw IllegalArgumentException when trying to delete a city that doesn't exist");
    }

    @Test
    void testDelete_ThrowsException_OnEmptyList() {
        CityList cityList = new CityList();
        City edmonton = mockCity();

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(edmonton);
        }, "Should throw IllegalArgumentException when trying to delete from empty list");
    }

    @Test
    void testDelete_WithEqualCityObject() {
        CityList cityList = mockCityList();
        // Create a new City object with same values but different instance
        City edmontonCopy = new City("Edmonton", "Alberta");

        cityList.delete(edmontonCopy);

        assertFalse(cityList.hasCity(mockCity()), "Should be able to delete using equal city object");
        assertEquals(0, cityList.getCities().size(), "List should be empty after deletion");
    }

    // countCities

    @Test
    void testCountCities_EmptyList() {
        CityList cityList = new CityList();

        assertEquals(0, cityList.countCities(), "Empty list should have count of 0");
    }

    @Test
    void testCountCities_SingleCity() {
        CityList cityList = mockCityList();

        assertEquals(1, cityList.countCities(), "List with one city should have count of 1");
    }

    @Test
    void testCountCities_MultipleCities() {
        CityList cityList = new CityList();
        cityList.add(new City("Edmonton", "Alberta"));
        cityList.add(new City("Calgary", "Alberta"));
        cityList.add(new City("Vancouver", "British Columbia"));
        cityList.add(new City("Toronto", "Ontario"));

        assertEquals(4, cityList.countCities(), "List with four cities should have count of 4");
    }

    @Test
    void testCountCities_AfterAddAndDelete() {
        CityList cityList = new CityList();
        City edmonton = new City("Edmonton", "Alberta");
        City calgary = new City("Calgary", "Alberta");
        City vancouver = new City("Vancouver", "British Columbia");

        cityList.add(edmonton);
        cityList.add(calgary);
        cityList.add(vancouver);
        assertEquals(3, cityList.countCities(), "Should have 3 cities after adding");

        cityList.delete(calgary);
        assertEquals(2, cityList.countCities(), "Should have 2 cities after deleting one");

        City toronto = new City("Toronto", "Ontario");
        cityList.add(toronto);
        assertEquals(3, cityList.countCities(), "Should have 3 cities after adding another");
    }
}