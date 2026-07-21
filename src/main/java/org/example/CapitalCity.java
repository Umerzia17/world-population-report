package org.example;
public class CapitalCity
{
    private String name;
    private String country;
    private long population;
    public CapitalCity()
    {
    }
    public CapitalCity(String name, String country, long population)
    {
        this.name = name;
        this.country = country;
        this.population = population;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getCountry()
    {
        return country;
    }
    public void setCountry(String country)
    {
        this.country = country;
    }
    public long getPopulation()
    {
        return population;
    }
    public void setPopulation(long population)
    {
        this.population = population;
    }
}