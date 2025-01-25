package com.controller.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.controller.Dao.OrderHistoryDao;
import com.controller.model.OrderHistory;


public class OrderHistoryDaoImpl implements OrderHistoryDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String INSERT_ORDER_HISTORY = "INSERT INTO order_history (orderid, userid, totalAmt, status) VALUES (?, ?, ?, ?)";
    @Override
    public boolean addOrderHistory(OrderHistory orderHistory) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_HISTORY)) {

            preparedStatement.setInt(1, orderHistory.getOrderId());
            preparedStatement.setInt(2, orderHistory.getUserId());
            preparedStatement.setFloat(3, orderHistory.getTotalAmt());
            preparedStatement.setString(4, orderHistory.getStatus());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public OrderHistory getOrderHistoryById(int id) {
        String query = "SELECT * FROM orderhistory WHERE orderHistoryId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new OrderHistory(
                    rs.getInt("orderHistoryId"),
                    rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getFloat("totalAmt"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderHistory> getAllOrderHistories() {
        List<OrderHistory> list = new ArrayList<>();
        String query = "SELECT * FROM orderhistory";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new OrderHistory(
                    rs.getInt("orderHistoryId"),
                    rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getFloat("totalAmt"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        String query = "UPDATE orderhistory SET orderId = ?, userId = ?, totalAmt = ?, status = ? WHERE orderHistoryId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderHistory.getOrderId());
            stmt.setInt(2, orderHistory.getUserId());
            stmt.setFloat(3, orderHistory.getTotalAmt());
            stmt.setString(4, orderHistory.getStatus());
            stmt.setInt(5, orderHistory.getOrderHistoryId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderHistory(int id) {
        String query = "DELETE FROM orderhistory WHERE orderHistoryId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

