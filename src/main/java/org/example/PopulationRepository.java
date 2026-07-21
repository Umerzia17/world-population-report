package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}