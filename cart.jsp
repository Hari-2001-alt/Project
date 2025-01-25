<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.*" %>
<%@ page import="com.tap.CartItem,com.tap.Cart,com.controller.DaoImpl.RestaurantDaoImpl,com.controller.model.Restaurant,com.controller.model.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
        color: #333;
    }

    h3 {
        color: #4CAF50;
        text-align: center;
    }

    table {
        width: 80%;
        border-collapse: collapse;
        margin: 20px 0;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        background-color: #fff;
    }

    th, td {
        text-align: center;
        padding: 12px;
        border: 1px solid #ddd;
    }

    th {
        background-color: #4CAF50;
        color: white;
        font-weight: bold;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #f1f1f1;
    }

    .button-container {
        margin-top: 20px;
        display: flex;
        gap: 10px;
        justify-content: center;
    }

    .button-container a {
        text-decoration: none;
        padding: 10px 20px;
        font-size: 16px;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    .green-button {
        background-color: #4CAF50;
    }

    .green-button:hover {
        background-color: #45a049;
    }

    .grey-button {
        background-color: #808080;
    }

    .grey-button:hover {
        background-color: #666666;
    }

    .action-buttons button {
        background-color: #f44336;
        color: white;
        border: none;
        border-radius: 3px;
        padding: 5px 10px;
        cursor: pointer;
        font-size: 14px;
        transition: background-color 0.3s ease;
    }

    .action-buttons button:hover {
        background-color: #d32f2f;
    }

    .quantity-control {
        display: flex;
        align-items: center;
        gap: 5px;
    }

    .quantity-control button {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 5px 10px;
        font-size: 16px;
        cursor: pointer;
        border-radius: 3px;
        transition: background-color 0.3s ease;
    }

    .quantity-control button:hover {
        background-color: #45a049;
    }

    .quantity-control input {
        width: 50px;
        text-align: center;
        border: 1px solid #ccc;
        border-radius: 3px;
        padding: 5px;
    }

    footer {
        margin-top: auto;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        text-align: center;
        width: 100%;
    }
     /* Style for the empty cart message */
    .empty-cart-message {
        font-size: 1.5rem;
        color: #4CAF50;
        text-align: center;
        margin-top: 20px;
        font-weight: bold;
    }

    /* Style for the "Go back to Menu" link */
    .empty-cart-link {
        display: block;
        margin-top: 20px;
        text-align: center;
        font-size: 1.2rem;
        color: #007BFF;
        text-decoration: none;
        font-weight: bold;
        padding: 10px 20px;
        background-color: #f1f1f1;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }

    .empty-cart-link:hover {
        background-color: #e0e0e0;
    }
     
     .quantity-controls {
    display: flex;
    align-items: center;
    justify-content: center;
    }

    .quantity-btn {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 5px 10px;
        font-size: 16px;
        cursor: pointer;
        border-radius: 3px;
        transition: background-color 0.3s ease;
    }

    .quantity-input {
        margin: 0 5px;
        font-size: 16px;
    }

    .remove-btn {
        background-color: #f44336;
        color: white;
        border: none;
        padding: 5px 10px;
        font-size: 14px;
        cursor: pointer;
        border-radius: 3px;
        transition: background-color 0.3s ease;
    }

    .remove-btn:hover {
        background-color: #d32f2f;
    }

    .minus-btn {
        background-color: #4CAF50; /* Changed to green */
    }

    .plus-btn {
        background-color: #4CAF50;
    }

    .alert {
        padding: 15px;
        margin-bottom: 20px;
        border: 1px solid transparent;
        border-radius: 4px;
    }

    .alert-danger {
        color: #721c24;
        background-color: #f8d7da;
        border-color: #f5c6cb;
    }

    .checkout-form {
        margin-top: 20px;
        padding: 20px;
        background-color: #f9f9f9;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .checkout-form label {
        display: block;
        margin-bottom: 5px;
    }

    .checkout-form input[type="text"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .checkout-form button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .checkout-form button:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>
<% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger" role="alert">
        <%= request.getAttribute("error") %>
    </div>
<% } %>

<%
    HttpSession ses = request.getSession();
    Cart cart = (Cart) ses.getAttribute("Cart");

    if (cart == null) {
        cart = new Cart(); // Initialize Cart if not in session
        ses.setAttribute("Cart", cart);
    }

    // Store cart items in session for checkout
    if (cart != null && !cart.getItem().isEmpty()) {
        List<CartItem> cartItemsList = new ArrayList<>(cart.getItem().values());
        session.setAttribute("cartItems", cartItemsList);
    }

    // Add debug logging for cart items
    System.out.println("Cart items in session: " + session.getAttribute("Cart"));

    if (cart.getItem() == null || cart.getItem().isEmpty()) {
%>
    <div class="empty-cart-message">
        <h3>Your cart is empty.</h3>
    </div>
    <a href="Menu.jsp" class="empty-cart-link">Go back to Menu</a>
<%
    } else {
        Map<Integer, CartItem> cartItems = cart.getItem();
        double totalAmount = 0;
%>
    <h3>Your Cart</h3>
    <table>
        <thead>
            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
<%
    for (Map.Entry<Integer, CartItem> entry : cartItems.entrySet()) {
        int itemId = entry.getKey();
        CartItem item = entry.getValue();
        double itemTotal = item.getQuantity() * item.getPrice();
        totalAmount += itemTotal;
%>
        <tr>
            <td><%= item.getName() %></td>
            <td>
                <div class="item-actions">
                   <form action="Cart" method="post" class="update-quantity-form">
                       <input type="hidden" name="action" value="update">
                        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                         <button type="submit" name="quantity" value="<%= item.getQuantity()-1 %>" class="btn btn-secondary">-</button>
                       <span> <%= item.getQuantity() %></span>
                       <button type="submit" name="quantity" value="<%= item.getQuantity()+1 %>" class="btn btn-secondary">+</button>
                   </form>
               </div>
            </td>
            <td>Rs <%= item.getPrice() %></td>
            <td>Rs <%= itemTotal %></td>
            <td>
                <form action="Cart" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <button type="submit" class="remove-btn">Remove</button>
                </form>
            </td>
        </tr>
<%
    }
%>
        </tbody>
    </table>
    <h4>Total Amount: Rs <%= totalAmount %></h4>
    <div class="button-container">
        <a href="menu?rid=<%= ses.getAttribute("rid") %>" class="grey-button">Get More Items</a>
    </div>

    <form action="checkout" method="post" class="checkout-form">
        <input type="hidden" name="paymentMode" value="CASH" />
         <input type="hidden" name="paymentMode" value="Credit" />
          <input type="hidden" name="paymentMode" value="Debit" />
           <input type="hidden" name="paymentMode" value="UPI" />
        <input type="hidden" name="totalAmount" value="<%= totalAmount %>" />
      
        <% System.out.println("Cart items in session: " + session.getAttribute("cartItems")); %>
        
        <button type="submit" class="green-button">Proceed to Checkout</button>
    </form>

  
    <a href="menu?rid=<%= ses.getAttribute("rid") %>">Continue Shopping</a>
<%
    }
%>
<footer>
    <p>&copy; 2024 Brundavihari. All rights reserved.</p>
</footer>
</body>
</html>

