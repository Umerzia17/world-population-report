package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PopulationRepository {
    private final Connection connection;

    public PopulationRepository(Connection connection) {
        this.connection = connection;
    }

    public long getWorldPopulation() throws SQLException {
        String sql = "SELECT SUM(Population) AS Population FROM country";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong("Population");
            }
        }

        return 0L;
    }

    public List<PopulationReport> findPopulationByCountry() throws SQLException {
        String sql = "SELECT c.Name, c.Population AS TotalPopulation, " +
                "COALESCE(SUM(city.Population), 0) AS PopulationInCities " +
                "FROM country c LEFT JOIN city ON city.CountryCode = c.Code " +
                "GROUP BY c.Code, c.Name, c.Population ORDER BY c.Name";
        List<PopulationReport> reports = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long totalPopulation = resultSet.getLong("TotalPopulation");
                long populationInCities = resultSet.getLong("PopulationInCities");
                long populationNotInCities = totalPopulation - populationInCities;
                double populationInCitiesPercentage = totalPopulation == 0 ? 0 : (populationInCities * 100.0) / totalPopulation;
                double populationNotInCitiesPercentage = totalPopulation == 0 ? 0 : (populationNotInCities * 100.0) / totalPopulation;

                reports.add(new PopulationReport(
                        resultSet.getString("Name"),
                        totalPopulation,
                        populationInCities,
                        populationInCitiesPercentage,
                        populationNotInCities,
                        populationNotInCitiesPercentage
                ));
            }
        }

        return reports;
    }

    public List<PopulationReport> findPopulationByContinent() throws SQLException {
        return findGroupedPopulationByCountryField("Continent");
    }

    public List<PopulationReport> findPopulationByRegion() throws SQLException {
        return findGroupedPopulationByCountryField("Region");
    }

    public List<PopulationReport> findPopulationByCountryName() throws SQLException {
        return findGroupedPopulationByCountryField("Name");
    }

    public List<PopulationReport> findPopulationByDistrict() throws SQLException {
        String sql = "SELECT city.District AS Name, SUM(city.Population) AS TotalPopulation " +
                "FROM city GROUP BY city.District ORDER BY city.District";
        List<PopulationReport> reports = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long totalPopulation = resultSet.getLong("TotalPopulation");
                reports.add(new PopulationReport(
                        resultSet.getString("Name"),
                        totalPopulation,
                        0L,
                        0.0,
                        totalPopulation,
                        100.0
                ));
            }
        }

        return reports;
    }

    public List<PopulationReport> findPopulationByCity() throws SQLException {
        String sql = "SELECT city.Name AS Name, city.Population AS TotalPopulation FROM city ORDER BY city.Name";
        List<PopulationReport> reports = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long totalPopulation = resultSet.getLong("TotalPopulation");
                reports.add(new PopulationReport(
                        resultSet.getString("Name"),
                        totalPopulation,
                        0L,
                        0.0,
                        totalPopulation,
                        100.0
                ));
            }
        }

        return reports;
    }

    public List<LanguageReport> findLanguageReports() throws SQLException {
        String sql = "SELECT Language, SUM(country.Population * countrylanguage.Percentage / 100) AS Speakers " +
                "FROM countrylanguage JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE Language IN ('Chinese', 'English', 'Spanish') " +
                "GROUP BY Language ORDER BY Speakers DESC";
        List<LanguageReport> reports = new ArrayList<>();
        long worldPopulation = getWorldPopulation();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long speakers = resultSet.getLong("Speakers");
                double worldPercentage = worldPopulation == 0 ? 0 : (speakers * 100.0) / worldPopulation;
                reports.add(new LanguageReport(
                        resultSet.getString("Language"),
                        speakers,
                        worldPercentage
                ));
            }
        }

        return reports;
    }

    private List<PopulationReport> findGroupedPopulationByCountryField(String groupColumn) throws SQLException {
        String sql = "SELECT c." + groupColumn + " AS Name, SUM(c.Population) AS TotalPopulation, " +
                "COALESCE(SUM(city_population.PopulationInCities), 0) AS PopulationInCities " +
                "FROM country c " +
                "LEFT JOIN (SELECT CountryCode, SUM(Population) AS PopulationInCities FROM city GROUP BY CountryCode) city_population " +
                "ON city_population.CountryCode = c.Code " +
                "GROUP BY c." + groupColumn + " ORDER BY c." + groupColumn;
        List<PopulationReport> reports = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long totalPopulation = resultSet.getLong("TotalPopulation");
                long populationInCities = resultSet.getLong("PopulationInCities");
                long populationNotInCities = totalPopulation - populationInCities;
                double populationInCitiesPercentage = totalPopulation == 0 ? 0 : (populationInCities * 100.0) / totalPopulation;
                double populationNotInCitiesPercentage = totalPopulation == 0 ? 0 : (populationNotInCities * 100.0) / totalPopulation;

                reports.add(new PopulationReport(
                        resultSet.getString("Name"),
                        totalPopulation,
                        populationInCities,
                        populationInCitiesPercentage,
                        populationNotInCities,
                        populationNotInCitiesPercentage
                ));
            }
        }

        return reports;
    }
}