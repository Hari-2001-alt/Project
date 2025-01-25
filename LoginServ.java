package com.tap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServ extends HttpServlet {
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapp";
	    private static final String DB_USERNAME = "root";
	    private static final String DB_PASSWORD = "root";
	    private static final String CHECK_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
		private String address;

		@Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String email = req.getParameter("email");
	        String password = req.getParameter("password");

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet res = null;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            pstmt = con.prepareStatement(CHECK_EMAIL_QUERY);
	            pstmt.setString(1, email);

	            res = pstmt.executeQuery();

	            if (res.next()) {
	                String dbPassword = res.getString("password");
	                String username = res.getString("username");
	                
	                if (password.equals(dbPassword)) {
	                    int userId = res.getInt("userid");
	                    String address = res.getString("address");

	                    User user = new User(userId, username, password, email, address);
	                    
	                    HttpSession session = req.getSession(true); // Create a new session or get existing one
	                    session.setAttribute("user", user);
	                    session.setAttribute("userId", userId);
	                    session.setAttribute("username", username);
	                    
	                    System.out.println("Login successful. Welcome, " + username);
	                    System.out.println("Session created with ID: " + session.getId());
	                    System.out.println("User ID in session: " + userId);
	                    
	                    resp.sendRedirect("home.jsp");
	                }
	                else {
	                    System.out.println("Password mismatch for user: " + email);
	                    req.setAttribute("errorMessage", "Invalid password. Please try again.");
	                    req.getRequestDispatcher("login.jsp").forward(req, resp);
	                }
	            } 
	            else {
	                System.out.println("Invalid user: " + email);
	                req.setAttribute("errorMessage", "User not found. Please register or try again.");
	                req.getRequestDispatcher("login.jsp").forward(req, resp);
	            }
	        } 
	        catch (ClassNotFoundException | SQLException e) {
	            System.err.println("Error during login: " + e.getMessage());
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "An error occurred. Please try again later.");
	            req.getRequestDispatcher("login.jsp").forward(req, resp);
	        }
	        finally {
	            try {
	                if (res != null) res.close();
	                if (pstmt != null) pstmt.close();
	                if (con != null) con.close();
	            }
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}
