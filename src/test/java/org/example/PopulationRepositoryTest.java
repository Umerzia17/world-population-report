package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PopulationRepositoryTest
{
    @Test
    public void testPopulationCalculation()
    {
        long totalPopulation = 1000000;
        long cityPopulation = 700000;
        long nonCityPopulation = totalPopulation - cityPopulation;
        assertEquals(300000, nonCityPopulation);
    }
}