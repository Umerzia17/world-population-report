package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
                List<CapitalCity> capitalCities = cityRepository.findAllCapitalCitiesByPopulationDesc();
                System.out.println("Requirement 3  Capital Cities Sorted by Population");
                printCapitalCityReport(capitalCities);
                System.out.println();
                // Requirement 4: Top N populated cities in the world
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the value of N: ");
                int limit = scanner.nextInt();
                scanner.nextLine();
                List<City> topCities = cityRepository.findTopNCitiesByPopulation(limit);
                System.out.println();
                System.out.println("Requirement 4  Top " + limit + " Populated Cities in the World");
                printCityReport(topCities);
                System.out.println();

                // Requirement 5: Population report for each country
                List<CountryPopulationReport> reports = countryRepository.findCountryPopulationReport();
                System.out.println("Requirement 5  Population Report for Each Country");
                printCountryPopulationReport(reports);
                System.out.println();

                // Requirement 6: Population Reports
                PopulationRepository populationRepository = new PopulationRepository(connection);
                long worldPopulation = populationRepository.getWorldPopulation();
                System.out.println("Requirement 6");
                System.out.println("--------------------------------------------");
                System.out.println("World Population : " + worldPopulation);
                System.out.println();
                System.out.print("Enter Continent: ");
                String continent = scanner.nextLine();
                System.out.print("Enter Region: ");
                String region = scanner.nextLine();
                System.out.print("Enter Country: ");
                String country = scanner.nextLine();
                System.out.print("Enter District: ");
                String district = scanner.nextLine();
                System.out.print("Enter City: ");
                String city = scanner.nextLine();
                System.out.println();
                System.out.println("Population of Continent (" + continent + ") : " + populationRepository.getContinentPopulation(continent));
                System.out.println("Population of Region (" + region + ") : " + populationRepository.getRegionPopulation(region));
                System.out.println("Population of Country (" + country + ") : " + populationRepository.getCountryPopulation(country));
                System.out.println("Population of District (" + district + ") : " + populationRepository.getDistrictPopulation(district));
                System.out.println("Population of City (" + city + ") : " + populationRepository.getCityPopulation(city));

                // Requirement 7: Language Report
                List<LanguageReport> languageReports = populationRepository.findLanguageReports();
                System.out.println();
                System.out.println("Requirement 7  Language Report");
                printLanguageReport(languageReports);
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
            System.out.printf("%-6s %-45s %-20s %-25s %-15d %-30s%n", country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());
        }
    }

    private static void printCityReport(List<City> cities)
    {
        System.out.printf("%-35s %-35s %-30s %-15s%n", "Name", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (City city : cities)
        {
            System.out.printf("%-35s %-35s %-30s %-15d%n", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation());
        }
    }

    private static void printCapitalCityReport(List<CapitalCity> capitalCities)
    {
        System.out.printf("%-35s %-35s %-15s%n", "Name", "Country", "Population");
        System.out.println("----------------------------------------------------------------------------------------------");
        for (CapitalCity capitalCity : capitalCities)
        {
            System.out.printf("%-35s %-35s %-15d%n", capitalCity.getName(), capitalCity.getCountry(), capitalCity.getPopulation());
        }
    }
    private static void printCountryPopulationReport(List<CountryPopulationReport> reports)
    {
        System.out.printf("%-35s %-15s %-20s %-20s%n", "Country", "Population", "City Population", "Non-City Population");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (CountryPopulationReport report : reports)
        {
            System.out.printf("%-35s %-15d %-20d %-20d%n", report.getName(), report.getPopulation(), report.getCityPopulation(), report.getNonCityPopulation());
        }
    }
    private static void printLanguageReport(List<LanguageReport> reports)
    {
        System.out.printf("%-20s %-20s %-25s%n", "Language", "Speakers", "World Percentage");
        System.out.println("--------------------------------------------------------------------------");
        for (LanguageReport report : reports)
        {
            System.out.printf("%-20s %-20d %-24.2f%%%n", report.getLanguage(), report.getSpeakers(), report.getWorldPercentage());
        }
    }
}