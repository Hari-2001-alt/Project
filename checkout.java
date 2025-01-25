package com.tap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.controller.DaoImpl.OrderItemDaoImpl;
import com.controller.DaoImpl.OrdersDaoImpl;
import com.controller.model.Orderss;
import com.controller.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class checkout extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrdersDaoImpl ordersDao;
    private OrderItemDaoImpl orderItemDao;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    public void init() {
        ordersDao = new OrdersDaoImpl();
        orderItemDao = new OrderItemDaoImpl();
        System.out.println("Checkout servlet initialized.");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("checkout.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Checkout process started.");
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            System.out.println("Session is null or user not found - redirecting to login");
            resp.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("Cart");
        
        if (cart == null || cart.getItem().isEmpty()) {
            System.out.println("Cart is null or empty - redirecting to menu");
            resp.sendRedirect("menu.jsp");
            return;
        }

        System.out.println("Cart validated. Number of items: " + cart.getItem().size());

        Object ridObj = session.getAttribute("rid");
        if (ridObj == null || !(ridObj instanceof Integer)) {
            System.out.println("Restaurant ID (rid) is missing or invalid - redirecting to menu");
            resp.sendRedirect("menu.jsp");
            return;
        }
        int rId = (Integer) ridObj;

        float totalAmt = calculateTotalAmount(cart);
        String paymentMode = req.getParameter("paymentMode");
        if (paymentMode == null || paymentMode.isEmpty()) {
            System.out.println("Payment mode is missing - redirecting to checkout page");
            resp.sendRedirect("checkout.jsp");
            return;
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            conn.setAutoCommit(false);
            System.out.println("Database connection established.");

            Orderss order = new Orderss(user.getUserId(), rId, totalAmt, "Pending", paymentMode);
            int orderId = ordersDao.addOrder(order);
            
            if (orderId <= 0) {
                throw new SQLException("Order Insertion Failed");
            }
            
            System.out.println("Order created successfully with ID: " + orderId);

            insertOrderItems(conn, orderId, cart);
            System.out.println("Order items inserted successfully.");

            insertOrderHistory(conn, orderId, user.getUserId(), totalAmt);
            System.out.println("Order history inserted successfully.");

            conn.commit();
            System.out.println("Transaction committed successfully.");

            cart.clearItem();
            session.removeAttribute("Cart");
            session.setAttribute("orderId", orderId);
            session.setAttribute("totalAmount", totalAmt);
            
            System.out.println("Redirecting to ord_conf.jsp");
            resp.sendRedirect("ord_conf.jsp");
        } 
        catch (SQLException e) {
            System.err.println("Database error during checkout: " + e.getMessage());
            e.printStackTrace();
           
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
           
            req.setAttribute("errorMessage", "An error occurred during checkout. Please try again.");
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private float calculateTotalAmount(Cart cart) {
        float totalAmt = 0;
        for (CartItem cartItem : cart.getItem().values()) {
            totalAmt += cartItem.getQuantity() * cartItem.getPrice();
        }
        return totalAmt;
    }

    private void insertOrderItems(Connection conn, int orderId, Cart cart) throws SQLException {
        String sql = "INSERT INTO orderitem (orderId, mid, quantity, itemTotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (CartItem cartItem : cart.getItem().values()) {
                pstmt.setInt(1, orderId);
                pstmt.setInt(2, cartItem.getItemId());
                pstmt.setInt(3, cartItem.getQuantity());
                pstmt.setFloat(4, cartItem.getQuantity() * cartItem.getPrice());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    private void insertOrderHistory(Connection conn, int orderId, int userId, float totalAmt) throws SQLException {
        String sql = "INSERT INTO orderhistory (orderId, userId, totalAmt, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, userId);
            pstmt.setFloat(3, totalAmt);
            pstmt.setString(4, "Pending");
            pstmt.executeUpdate();
        }
    }
}

