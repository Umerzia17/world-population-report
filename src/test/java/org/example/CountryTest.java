package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CountryTest
{
    @Test
    public void testCountryConstructor()
    {
        Country country = new Country("PAK", "Pakistan", "Asia",
                "Southern and Central Asia", 220892340L, "Islamabad");

        assertEquals("PAK", country.getCode());
        assertEquals("Pakistan", country.getName());
        assertEquals("Asia", country.getContinent());
        assertEquals("Southern and Central Asia", country.getRegion());
        assertEquals(220892340L, country.getPopulation());
        assertEquals("Islamabad", country.getCapital());
    }
    @Test
    public void testCountrySetters()
    {
        Country country = new Country();
        country.setCode("USA");
        country.setName("United States");
        country.setContinent("North America");
        country.setRegion("North America");
        country.setPopulation(331002651L);
        country.setCapital("Washington");
        assertEquals("USA", country.getCode());
        assertEquals("United States", country.getName());
        assertEquals("North America", country.getContinent());
        assertEquals("North America", country.getRegion());
        assertEquals(331002651L, country.getPopulation());
        assertEquals("Washington", country.getCapital());
    }
}