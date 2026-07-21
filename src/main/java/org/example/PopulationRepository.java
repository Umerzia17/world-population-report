package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PopulationRepository
{
    private final Connection connection;
    public PopulationRepository(Connection connection)
    {
        this.connection = connection;
    }
    public long getWorldPopulation() throws SQLException
    {
        String sql = "SELECT SUM(Population) AS Population FROM country";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
            {
                return resultSet.getLong("Population");
            }
        }
        return 0L;
    }
    public long getContinentPopulation(String continent) throws SQLException
    {
        String sql = "SELECT SUM(Population) AS Population FROM country WHERE Continent = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, continent);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return resultSet.getLong("Population");
                }
            }
        }
        return 0L;
    }
    public long getRegionPopulation(String region) throws SQLException
    {
        String sql = "SELECT SUM(Population) AS Population FROM country WHERE Region = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, region);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return resultSet.getLong("Population");
                }
            }
        }

        return 0L;
    }
    public long getCountryPopulation(String country) throws SQLException
    {
        String sql = "SELECT Population FROM country WHERE Name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, country);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return resultSet.getLong("Population");
                }
            }
        }

        return 0L;
    }

    public long getDistrictPopulation(String district) throws SQLException
    {
        String sql = "SELECT SUM(Population) AS Population FROM city WHERE District = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, district);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return resultSet.getLong("Population");
                }
            }
        }

        return 0L;
    }

    public long getCityPopulation(String city) throws SQLException
    {
        String sql = "SELECT Population FROM city WHERE Name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, city);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return resultSet.getLong("Population");
                }
            }
        }

        return 0L;
    }
    public List<LanguageReport> findLanguageReports() throws SQLException
    {
        String sql = "SELECT countrylanguage.Language, " +
                "SUM(country.Population * countrylanguage.Percentage / 100) AS Speakers " +
                "FROM countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE countrylanguage.Language IN ('Chinese', 'English', 'Spanish') " +
                "GROUP BY countrylanguage.Language " +
                "ORDER BY Speakers DESC";

        List<LanguageReport> reports = new ArrayList<>();

        long worldPopulation = getWorldPopulation();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                long speakers = resultSet.getLong("Speakers");
                double worldPercentage = worldPopulation == 0 ? 0 : (speakers * 100.0) / worldPopulation;

                reports.add(new LanguageReport(
                        resultSet.getString("Language"),
                        speakers,
                        worldPercentage));
            }
        }

        return reports;
    }
}