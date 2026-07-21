package org.example;

public class CountryPopulationReport
{
    private String name;
    private long population;
    private long cityPopulation;
    private long nonCityPopulation;
    public CountryPopulationReport(String name, long population, long cityPopulation, long nonCityPopulation)
    {
        this.name = name;
        this.population = population;
        this.cityPopulation = cityPopulation;
        this.nonCityPopulation = nonCityPopulation;
    }
    public String getName()
    {
        return name;
    }
    public long getPopulation()
    {
        return population;
    }
    public long getCityPopulation()
    {
        return cityPopulation;
    }
    public long getNonCityPopulation()
    {
        return nonCityPopulation;
    }
}