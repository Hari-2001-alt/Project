package com.controller.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.controller.Dao.OrderItemDao;
import com.controller.model.OrderItem;



public class OrderItemDaoImpl  implements OrderItemDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public boolean insertOrderItem(OrderItem orderItem) {
        String query = "INSERT INTO orderitem (orderItemId, orderId, mid, quantity, itemTotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderItem.getOrderItemId());
            stmt.setInt(2, orderItem.getOrderId());
            stmt.setInt(3, orderItem.getMid());
            stmt.setInt(4, orderItem.getQuantity());
            stmt.setInt(5, orderItem.getItemTotal());

            return stmt.executeUpdate() > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO orderitem (orderId, mid, quantity, itemTotal) VALUES (?, ?, ?, ?)";
        try (Connection conn =DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMid());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setInt(4, orderItem.getItemTotal());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                return false;
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderItem.setOrderItemId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order item failed, no ID obtained.");
                }
            }
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        String query = "SELECT * FROM orderitem WHERE orderItemId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderItemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("mid"),
                        rs.getInt("quantity"),
                        rs.getInt("itemTotal")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        String query = "SELECT * FROM orderitem";
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("mid"),
                        rs.getInt("quantity"),
                        rs.getInt("itemTotal")
                );
                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
    
    private static final String INSERT_ORDER_ITEM_SQL = "INSERT INTO orderitem (orderId, mid, quantity, itemTotal) VALUES (?, ?, ?, ?)";

    @Override
    public boolean insertOrderItem1(OrderItem orderItem) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM_SQL)) {

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getMid());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setFloat(4, orderItem.getItemTotal());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting order item: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        String query = "UPDATE orderitem SET orderId = ?, mid = ?, quantity = ?, itemTotal = ? WHERE orderItemId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getMid());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getItemTotal());
            stmt.setInt(5, orderItem.getOrderItemId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {
        String query = "DELETE FROM orderitem WHERE orderItemId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderItemId);

            return stmt.executeUpdate() > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


