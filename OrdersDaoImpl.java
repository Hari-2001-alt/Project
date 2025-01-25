package com.controller.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.controller.Dao.OrdersDao;
import com.controller.model.Orderss;


public class OrdersDaoImpl implements OrdersDao {
    private static final String URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_ORDER_SQL = "INSERT INTO orderss (userid, rid, totalAmt, status, paymentMode) VALUES (?, ?, ?, ?, ?)";

    @Override
    public int addOrder(Orderss order) {
        String sql = "INSERT INTO orderss (userId, rId, totalAmt, status, paymentMode) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRid());
            pstmt.setFloat(3, order.getTotalAmt());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentMode());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    order.setOrderId(orderId);
                    return orderId;
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Orderss getOrderById(int orderId) {
        String query = "SELECT * FROM orderss WHERE orderId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Orderss(
                       // rs.getInt("orderId"),
                        rs.getInt("userId"),
                        rs.getInt("rid"),
                        rs.getFloat("totalAmt"),
                      //  rs.getTimestamp("ordDate"), 
                        
                        rs.getString("status"),
                        rs.getString("paymentMode")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Orderss> getOrdersByUserId(int userId) {
        List<Orderss> ordersList = new ArrayList<>();
        String query = "SELECT * FROM orderss WHERE userId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordersList.add(new Orderss(
                   // rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getInt("rId"),
                  //  rs.getTimestamp("ordDate"),
                    rs.getFloat("totalAmt"),
                    rs.getString("status"),
                    rs.getString("paymentMode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }
    
    public List<Orderss> getRestaurantById(int rId) {
        List<Orderss> ordersList = new ArrayList<>();
        String query = "SELECT * FROM restaurant WHERE rId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, rId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordersList.add(new Orderss(
                   // rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getInt("rId"),
                  //  rs.getTimestamp("ordDate"),
                    rs.getFloat("totalAmt"),
                    rs.getString("status"),
                    rs.getString("paymentMode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public List<Orderss> getAllOrders() {
        List<Orderss> ordersList = new ArrayList<>();
        String query = "SELECT * FROM orderss";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ordersList.add(new Orderss(
                       // rs.getInt("orderId"),
                        rs.getInt("userId"),
                        rs.getInt("rId"),
                      //  rs.getTimestamp("ordDate"), // Fetching ordDate
                        rs.getFloat("totalAmt"),
                        rs.getString("status"),
                        rs.getString("paymentMode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public void updateOrder(Orderss order) {
        String query = "UPDATE orderss SET userId = ?, restaurantId = ?, ordDate = ?, totalAmt = ?, status = ?, paymentMode = ? WHERE orderId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRid());
          //  ps.setTimestamp(3, order.getOrdDate()); // Updating ordDate
            ps.setFloat(4, order.getTotalAmt());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPaymentMode());
            ps.setInt(7, order.getOrderId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String query = "DELETE FROM orderss WHERE orderId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
