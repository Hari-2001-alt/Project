<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.controller.model.Restaurant, java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>Restaurant Listings</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #FF6347, #FFFFFF);
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            margin: 20px 0;
            color: #333;
        }
        .container {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            padding: 20px;
        }
        .card {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 15px;
            text-align: center;
            overflow: hidden;
            position: relative;
        }
        .card img {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 8px 8px 0 0;
        }
        .card h3 {
            color: #333;
            margin: 10px 0;
        }
        .chip {
            display: inline-block;
            background-color: #4CAF50;
            color: #fff;
            padding: 5px 10px;
            border-radius: 16px;
            font-size: 0.9em;
        }
        .card p {
            margin: 5px 0;
            font-size: 0.95em;
            color: #555;
        }
        @media (max-width: 1200px) {
            .container {
                grid-template-columns: repeat(3, 1fr);
            }
        }
        @media (max-width: 768px) {
            .container {
                grid-template-columns: repeat(2, 1fr);
            }
        }
        @media (max-width: 480px) {
            .container {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <%
        HttpSession sessionObj = request.getSession(false);
        String username = (sessionObj != null) ? (String) sessionObj.getAttribute("username") : "Guest";
    %>
    <h1>Welcome, <%= username %>! Explore Our Restaurants</h1>

    <div class="container">
        <%
            List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
            if (restaurantList != null && !restaurantList.isEmpty()) {
                for (Restaurant r : restaurantList) {
                    String imagePath = "images/" + r.getRName().replaceAll("\\s", "_").toLowerCase() + ".jpg"; // Dynamically generated image path
        %>
        <div class="card">
            <img src="<%= imagePath %>" alt="<%= r.getRName() %>">
            <h3><%= r.getRName() %></h3>
            <p>Cuisine: <%= r.getCuisineType() %></p>
            <p>Address: <%= r.getAddress() %></p>
            <span class="chip">Rating: <%= r.getRattings() %></span>
        </div>
        <%
                }
            } else {
        %>
        <p>No restaurants available at the moment.</p>
        <%
            }
        %>
    </div>
</body>
</html>
