package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class TopNTest
{
    @Test
    public void testTopNCities()
    {
        List<City> cities = new ArrayList<>();
        cities.add(new City("A", "P", "X", 100));
        cities.add(new City("B", "P", "X", 500));
        cities.add(new City("C", "P", "X", 300));
        cities.add(new City("D", "P", "X", 700));
        cities.sort(Comparator.comparingLong(City::getPopulation).reversed());
        List<City> topTwo = cities.subList(0, 2);
        assertEquals(2, topTwo.size());
        assertEquals(700, topTwo.get(0).getPopulation());
        assertEquals(500, topTwo.get(1).getPopulation());
    }
}