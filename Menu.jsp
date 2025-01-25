<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.controller.model.Menu" %>
<%@ page import="java.util.List" %>
<%@ page import="com.controller.Dao.MenuDao" %>
<%@ page import="com.controller.DaoImpl.MenuDaoImpl" %>

<!DOCTYPE html>
<html>
<head>
    <title>Restaurant Menu</title>
    <style>
        .container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            margin: 20px;
        }
        .card {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            width: 300px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .card h3, .card h4 {
            margin: 10px 0;
        }
        .card p {
            margin: 5px 0;
        }
        .price {
            font-weight: bold;
            color: green;
        }
        .availability {
            font-style: italic;
            color: #555;
        }
        .quantity-selector {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
            margin-top: 10px;
        }
        .quantity-selector button {
            padding: 5px 10px;
            font-size: 16px;
            cursor: pointer;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f8f8f8;
        }
        .quantity-selector input {
            width: 50px;
            text-align: center;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .add-to-cart-button {
            margin-top: 15px;
            padding: 10px 15px;
            font-size: 16px;
            color: white;
            background-color: green;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .add-to-cart-button:hover {
            background-color: darkgreen;
        }
    </style>
    <script>
        function updateQuantity(inputId, increment) {
            const inputField = document.getElementById(inputId);
            let currentValue = parseInt(inputField.value) || 0;

            currentValue += increment;
            if (currentValue < 0) {
                currentValue = 0;
            }

            inputField.value = currentValue;
        }
    </script>
</head>
<body>
    <div class="container">
        <%
            // Retrieve the list of menus from the request attribute
            List<Menu> menus = (List<Menu>) request.getAttribute("mList");

            if (menus == null || menus.isEmpty()) {
        %>
            <p>No menus available for this restaurant.</p>
        <%
            } else {
                int index = 0;
                for (Menu menu : menus) {
                    String inputId = "quantity-" + index;
        %>
                    <div class="card">
                        <img src="<%= menu.getImagePath() %>" alt="<%= menu.getImagePath() %>" style="width: 250px; height: 150px;">
                        <hr>
                        <h4><%= menu.getMname() %></h4>
                        <p><%= menu.getDescription() %></p>
                        <p class="price">Rs. <%= menu.getPrice() %></p>
                        <p class="availability"><%= menu.getIsAvailable() ? "Available" : "Unavailable" %></p>
                       <%--  <div class="quantity-selector">
                            <button onclick="updateQuantity('<%= inputId %>', -1)">-</button>
                            <input type="text" id="<%= inputId %>" value="0">
                            <button onclick="updateQuantity('<%= inputId %>', 1)">+</button>
                        </div> --%>
                        <form action="Cart" method="post">
                          <input type="hidden" name="action" value="add">
                           <input type="hidden" name="itemId" value="<%=menu.getMid()%>">
                           <input type="hidden" name="quantity" value="1">
                           
                    
                          <button class="add-to-cart-button">Add to Cart</button>
                         </form>
                        
                    </div>
                    
        <%
                    index++;
                }
            }
        %>
    </div>
</body>
</html>
