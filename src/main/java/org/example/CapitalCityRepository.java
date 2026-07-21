package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapitalCityRepository
{
    private final Connection connection;
    public CapitalCityRepository(Connection connection)
    {
        this.connection = connection;
    }
    public List<City> findAllCapitalCitiesByPopulationDesc() throws SQLException
    {
        List<City> capitalCities = new ArrayList<>();
        String query ="SELECT city.Name, country.Name AS Country, city.Population " +"FROM city " +"JOIN country ON city.ID = country.Capital " +"ORDER BY city.Population DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            City city = new City();
            city.setName(resultSet.getString("Name"));
            city.setCountry(resultSet.getString("Country"));
            city.setPopulation(resultSet.getInt("Population"));
            capitalCities.add(city);
        }
        resultSet.close();
        preparedStatement.close();
        return capitalCities;
    }
}