package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private final Connection connection;

    public CityRepository(Connection connection) {
        this.connection = connection;
    }

    public List<City> findAllCitiesByPopulationDesc() throws SQLException {
        String sql = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";
        List<City> cities = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cities.add(new City(
                        resultSet.getString("Name"),
                        resultSet.getString("Country"),
                        resultSet.getString("District"),
                        resultSet.getLong("Population")
                ));
            }
        }

        return cities;
    }

    public List<CapitalCity> findAllCapitalCitiesByPopulationDesc() throws SQLException {
        String sql = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city JOIN country ON country.Capital = city.ID " +
                "ORDER BY city.Population DESC";
        List<CapitalCity> capitalCities = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                capitalCities.add(new CapitalCity(
                        resultSet.getString("Name"),
                        resultSet.getString("Country"),
                        resultSet.getLong("Population")
                ));
            }
        }

        return capitalCities;
    }

    public List<City> findTopNCitiesByPopulation(int limit) throws SQLException {
        String sql = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC LIMIT ?";
        List<City> cities = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, limit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getString("Name"),
                            resultSet.getString("Country"),
                            resultSet.getString("District"),
                            resultSet.getLong("Population")
                    ));
                }
            }
        }

        return cities;
    }
}