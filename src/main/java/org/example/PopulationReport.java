package org.example;

public class PopulationReport
{
    private String name;
    private long totalPopulation;
    private long populationInCities;
    private double populationInCitiesPercentage;
    private long populationNotInCities;
    private double populationNotInCitiesPercentage;
    public PopulationReport()
    {
    }
    public PopulationReport(String name, long totalPopulation, long populationInCities, double populationInCitiesPercentage, long populationNotInCities, double populationNotInCitiesPercentage)
    {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.populationInCitiesPercentage = populationInCitiesPercentage;
        this.populationNotInCities = populationNotInCities;
        this.populationNotInCitiesPercentage = populationNotInCitiesPercentage;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public long getTotalPopulation()
    {
        return totalPopulation;
    }
    public void setTotalPopulation(long totalPopulation)
    {
        this.totalPopulation = totalPopulation;
    }
    public long getPopulationInCities()
    {
        return populationInCities;
    }
    public void setPopulationInCities(long populationInCities)
    {
        this.populationInCities = populationInCities;
    }
    public double getPopulationInCitiesPercentage()
    {
        return populationInCitiesPercentage;
    }
    public void setPopulationInCitiesPercentage(double populationInCitiesPercentage)
    {
        this.populationInCitiesPercentage = populationInCitiesPercentage;
    }
    public long getPopulationNotInCities()
    {
        return populationNotInCities;
    }
    public void setPopulationNotInCities(long populationNotInCities)
    {
        this.populationNotInCities = populationNotInCities;
    }
    public double getPopulationNotInCitiesPercentage()
    {
        return populationNotInCitiesPercentage;
    }
    public void setPopulationNotInCitiesPercentage(double populationNotInCitiesPercentage)
    {
        this.populationNotInCitiesPercentage = populationNotInCitiesPercentage;
    }
}