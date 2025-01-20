package models;

import java.util.ArrayList;
import java.sql.*;

public class City {
    // #################### Properties ####################################
    private Integer cityId;
    private String name;
    private State state;

    // #################### Constructors ####################################
    public City() {}

    public City(Integer cityId) {
        this.cityId = cityId;
    }

    public City(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public City(Integer cityId, String name, State state) {
        this.cityId = cityId;
        this.name = name;
        this.state = state;
    }

    // #################### Methods ####################################
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return name.equals(city.name) && state.getName().equals(city.state.getName());
    }

    public static ArrayList<City> collectAllCities() {
        ArrayList<City> cities = new ArrayList<>();
        String query = "SELECT city_id, c.name, s.name FROM cities AS c INNER JOIN states AS s ON c.state_id = s.state_id";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb", "root", "1234");
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            while (rs.next()) {
                cities.add(new City(rs.getInt(1), rs.getString(2), new State(rs.getString(3))));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    // #################### Getters and Setters ####################################
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}
