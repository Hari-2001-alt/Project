<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.controller.model.Restaurant,com.controller.model.User" %>
<%@ page import="com.controller.DaoImpl.RestaurantDaoImpl" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, Helvetica, sans-serif;
        }

        /* Body Styling */
        body {
            background-color: #ADD8E6; /* Light Blue */
            line-height: 1.6;
            padding: 20px;
        }

        /* Header Section */
        h1 {
            color: #4CAF50;
            text-align: center;
            margin-bottom: 10px;
            font-size: 2.5rem;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 1.8rem;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
        }

        /* Navigation Bar */
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
           // background-color: #4CAF50;
            padding: 10px 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .navbar .search-bar {
            display: flex;
            align-items: center;
            flex-grow: 1;
            margin-right: 20px;
        }

        .navbar .search-bar input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 20px;
            font-size: 1rem;
        }

        .navbar .search-bar button {
            padding: 10px 20px;
            margin-left: 10px;
            background-color: #FFD700;
            border: none;
            border-radius: 20px;
            color: #003366;
            cursor: pointer;
            font-weight: bold;
        }

        .navbar .menu {
            display: flex;
            gap: 15px;
        }

        .navbar .menu a {
            text-decoration: none;
            padding: 10px 20px;
           // background-color: #FFD700;
            border-radius: 20px;
            color: #003366;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .navbar .menu a:hover {
            background-color: #FFB700;
        }

        /* Welcome Board */
        .welcome-board {
            background: linear-gradient(to right, #ADD8E6, #62cff4, #2c67f2);
            color: blue;
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            position: relative;
            text-align: center;
        }

        .welcome-board h1 {
            font-size: 3rem;
            margin-bottom: 20px;
            text-transform: uppercase;
            letter-spacing: 2px;
            color: #003366;
        }

        .restaurant-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-around;
        }

        .restaurant-card {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 300px;
            padding: 20px;
            text-align: left;
            transition: transform 0.3s ease;
            overflow: hidden;
            position: relative;
        }

        .restaurant-card:hover {
            transform: translateY(-5px);
        }

        .restaurant-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 8px 8px 0 0;
        }

        footer {
            text-align: center;
            margin-top: 30px;
            font-size: 1rem;
            color: #777;
        }

    </style>
</head>
<body>
		
	 <!-- Navigation Bar -->
    <div class="navbar">
        <!-- Search Bar -->
        <div class="search-bar">
            <input type="text" placeholder="Search Restaurants...">
            <button type="button">Search</button>
        </div>

       
                <!-- Menu Links -->
        <div class="menu">
            <a href="#offers" title="Offers">
                <i class="fas fa-tags" style="color: #FFD700;"></i> Offers
            <a href="#cart" title="Cart">
        		<i class="fas fa-shopping-cart" style="color: #FF4500;"></i>
                <img src="image/cart.png" alt="Cart Image" style="width: 20px; height: 20px; margin-left: 8px;">
    			Cart
			</a>

            <a href="#profile" title="Profile">
                <i class="fas fa-user" style="color: #4CAF50;"></i> Profile
            </a>
            <a href="logout.jsp" title="Logout">
                <i class="fas fa-sign-out-alt" style="color: #DC143C;"></i> Logout
            </a>
        </div>

    </div>

    <div class="welcome-board">
        <h1><marquee>Welcome to Our Brundavihari</marquee></h1>
        
    </div>

    <% 
       User user = (User) session.getAttribute("user");
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <h1>Hi, <%= username != null ? username : "Guest" %></h1>
    <h2>Restaurant Listings</h2>
    
   
    

    <div class="restaurant-container">
        <% 
            RestaurantDaoImpl dao = new RestaurantDaoImpl();
            List<Restaurant> restaurants = dao.getAllRestaurants();

            if (restaurants != null && !restaurants.isEmpty()) {
                for (Restaurant restaurant : restaurants) {
        %>
       <a href="menu?rid=<%= restaurant.getRId() %>" > 
            <div class="restaurant-card">
              <div class="status-badge <%= restaurant.isActive() ? "active" : "inactive" %>">
    			<div class="status-badge">
    				<i style="<%= restaurant.isActive() ? "color: #008000;" : "color: #dc3545;" %>">
        			<%= restaurant.isActive() ? "✔" : "✖" %>
    				</i>
    				<%= restaurant.isActive() ? "Active" : "Inactive" %>
     			</div>
     		  </div>

                <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getRName() %>">
                <h3><%= restaurant.getRName() %></h3>
                <p>Cuisine Type: <%= restaurant.getCuisineType() %></p>
                <p>Address: <%= restaurant.getAddress() %></p>
                <p class="star-rating">
                    Ratings: <%= restaurant.getRattings() %>
                    <span class="star"> &#9733;</span> <!-- Yellow Star -->
                </p>
                <hr>
          </div>
         </a>
         
        <% 
                }
            } 
            else {
        %>
            <p>No restaurants available.</p>
        <% 
            }
        %>
    </div>
     <!-- Footer -->
    <footer>
        &copy; 2024 Brundavihari. All rights reserved.
    </footer>
</body>
</html>
