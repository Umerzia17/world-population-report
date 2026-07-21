package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try
        {
            Connection connection = databaseConnection.getConnection();
            if (connection != null)
            {
                System.out.println("Database is Connected Successfully!\n");
                // Requirement 1: Countries sorted by population
                CountryRepository countryRepository = new CountryRepository(connection);
                List<Country> countries = countryRepository.findAllCountriesByPopulationDesc();
                System.out.println("Requirement 1  Countries Sorted by Population");
                printCountryReport(countries);
                System.out.println();
                // Requirement 2: Cities sorted by population
                CityRepository cityRepository = new CityRepository(connection);
                List<City> cities = cityRepository.findAllCitiesByPopulationDesc();
                System.out.println("Requirement 2  Cities Sorted by Population");
                printCityReport(cities);
                System.out.println();

                // Requirement 3: Capital cities sorted by population
                CapitalCityRepository capitalCityRepository = new CapitalCityRepository(connection);
                List<City> capitalCities = capitalCityRepository.findAllCapitalCitiesByPopulationDesc();
                System.out.println("Requirement 3  Capital Cities Sorted by Population");
                printCapitalCityReport(capitalCities);

                databaseConnection.closeConnection(connection);
            }
        }
        catch (SQLException exception)
        {
            System.out.println("The Connection Failed: " + exception.getMessage());
        }
    }

    private static void printCountryReport(List<Country> countries)
    {
        System.out.printf("%-6s %-45s %-20s %-25s %-15s %-30s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        for (Country country : countries)
        {
            System.out.printf("%-6s %-45s %-20s %-25s %-15d %-30s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

    private static void printCityReport(List<City> cities)
    {
        System.out.printf("%-35s %-35s %-30s %-15s%n", "Name", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (City city : cities)
        {
            System.out.printf("%-35s %-35s %-30s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    private static void printCapitalCityReport(List<City> capitalCities)
    {
        System.out.printf("%-35s %-35s %-15s%n", "Name", "Country", "Population");
        System.out.println("----------------------------------------------------------------------------------------------");
        for (City city : capitalCities)
        {
            System.out.printf("%-35s %-35s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getPopulation());
        }
    }
}