package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        try {
            Connection connection = databaseConnection.getConnection();
            if (connection != null) {
                CountryRepository countryRepository = new CountryRepository(connection);
                List<Country> countries = countryRepository.findAllCountriesByPopulationDesc();

                printCountryReport(countries);
                databaseConnection.closeConnection(connection);
            }
        } catch (SQLException exception) {
            System.out.println("Connection Failed: " + exception.getMessage());
        }
    }

    private static void printCountryReport(List<Country> countries) {
        System.out.printf("%-6s %-45s %-20s %-25s %-15s %-30s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        for (Country country : countries) {
            System.out.printf("%-6s %-45s %-20s %-25s %-15d %-30s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }
}