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
    public List<CountryPopulationReport> findCountryPopulationReport() throws SQLException
{
    String sql = "SELECT country.Name, country.Population, " +"IFNULL(SUM(city.Population), 0) AS CityPopulation, " +"(country.Population - IFNULL(SUM(city.Population), 0)) AS NonCityPopulation " +"FROM country LEFT JOIN city ON country.Code = city.CountryCode " +"GROUP BY country.Code, country.Name, country.Population " +"ORDER BY country.Population DESC";

    List<CountryPopulationReport> reports = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery())
    {
        while (resultSet.next())
        {
            reports.add(new CountryPopulationReport(
                    resultSet.getString("Name"),
                    resultSet.getLong("Population"),
                    resultSet.getLong("CityPopulation"),
                    resultSet.getLong("NonCityPopulation")));
        }
    }

    return reports;
}
}