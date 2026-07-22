package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CityTest
{
    @Test
    public void testCityConstructor()
    {
        City city = new City("Karachi",
                "Pakistan",
                "Sindh",
                14910352L);

        assertEquals("Karachi", city.getName());
        assertEquals("Pakistan", city.getCountry());
        assertEquals("Sindh", city.getDistrict());
        assertEquals(14910352L, city.getPopulation());
    }

    @Test
    public void testCitySetters()
    {
        City city = new City();
        city.setName("Lahore");
        city.setCountry("Pakistan");
        city.setDistrict("Punjab");
        city.setPopulation(11126285L);
        assertEquals("Lahore", city.getName());
        assertEquals("Pakistan", city.getCountry());
        assertEquals("Punjab", city.getDistrict());
        assertEquals(11126285L, city.getPopulation());
    }
}