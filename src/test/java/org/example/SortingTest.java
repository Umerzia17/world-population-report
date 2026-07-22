package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class SortingTest
{
    @Test
    public void testCountrySorting()
    {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("A", "A", "Asia", "Asia", 500, "A"));
        countries.add(new Country("B", "B", "Asia", "Asia", 900, "B"));
        countries.add(new Country("C", "C", "Asia", "Asia", 300, "C"));
        countries.sort(Comparator.comparingLong(Country::getPopulation).reversed());
        assertEquals(900, countries.get(0).getPopulation());
        assertEquals(500, countries.get(1).getPopulation());
        assertEquals(300, countries.get(2).getPopulation());
    }
}