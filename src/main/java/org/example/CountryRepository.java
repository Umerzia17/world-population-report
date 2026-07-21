package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CountryRepository
{
    private final Connection connection;
    public CountryRepository(Connection connection)
    {
        this.connection = connection;
    }
    public List<Country> findAllCountriesByPopulationDesc() throws SQLException
    {
        String sql = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, co.Name AS Capital " + "FROM country c LEFT JOIN city co ON c.Capital = co.ID " + "ORDER BY c.Population DESC";
        List<Country> countries = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                countries.add(new Country(resultSet.getString("Code"), resultSet.getString("Name"), resultSet.getString("Continent"), resultSet.getString("Region"), resultSet.getLong("Population"), resultSet.getString("Capital")));
            }
        }
        return countries;
    }
}