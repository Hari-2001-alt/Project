package com.controller.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.controller.Dao.MenuDao;
import com.controller.model.Menu;

public class MenuDaoImpl implements MenuDao {

    private static final String URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } 

    @Override
    public void addMenu(Menu menu) {
        String sql = "INSERT INTO menu (rid, mname, description, price, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, menu.getRid());
            statement.setString(2, menu.getMname());
            statement.setString(3, menu.getDescription());
            statement.setInt(4, menu.getPrice());
            statement.setBoolean(5, menu.getIsAvailable());
            statement.setString(6, menu.getImagePath());
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenuById(int mid) {
        String sql = "SELECT * FROM menu WHERE mid = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Menu(
                        resultSet.getInt("mid"),
                        resultSet.getInt("rid"),
                        resultSet.getString("mname"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("isAvailable"),
                        resultSet.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateMenu(Menu menu) {
        String sql = "UPDATE menu SET rid = ?, mname = ?, description = ?, price = ?, isAvailable = ?, imagePath = ? WHERE mid = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, menu.getRid());
            statement.setString(2, menu.getMname());
            statement.setString(3, menu.getDescription());
            statement.setInt(4, menu.getPrice());
            statement.setBoolean(5, menu.getIsAvailable());
            statement.setString(6, menu.getImagePath());
            statement.setInt(7, menu.getMid());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int mid) {
        String sql = "DELETE FROM menu WHERE mid = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getAllMenusByRestaurant(int rid) {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE rid = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                menus.add(new Menu(
                        resultSet.getInt("mid"),
                        resultSet.getInt("rid"),
                        resultSet.getString("mname"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("isAvailable"),
                        resultSet.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
}




