<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
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

    h3, h4 {
        color: #4CAF50;
        text-align: center;
    }

    .payment-container {
        background-color: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        padding: 20px;
        border-radius: 10px;
        width: 50%;
        text-align: center;
    }

    form {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }

    label {
        font-weight: bold;
        text-align: left;
    }

    input[type="text"], input[type="number"] {
        padding: 10px;
        font-size: 16px;
        width: 100%;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .payment-options button {
        padding: 15px;
        font-size: 16px;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    .pay-now {
        background-color: #4CAF50;
    }

    .pay-now:hover {
        background-color: #45a049;
    }

    .cancel {
        background-color: #f44336;
    }

    .cancel:hover {
        background-color: #d32f2f;
    }

    footer {
        margin-top: auto;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        text-align: center;
        width: 100%;
    }
</style>
</head>
<body>
<%
    HttpSession ses = request.getSession();
    Double totalAmount = (Double) session.getAttribute("totalAmount");
    if (totalAmount == null) {
        totalAmount = 0.0;
    }
    DecimalFormat df = new DecimalFormat("#.00");
%>

<div class="payment-container">
    <h3>Payment Page</h3>
    <h4>Total Amount: Rs <%= df.format(totalAmount) %></h4>

    <form action="PaymentServ" method="post">
        <!-- Address Fields -->
        <label for="place">Place:</label>
        <input type="text" id="place" name="place" required placeholder="Enter your place">

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required placeholder="Enter your address">
        
        <label for="pincode">Pincode:</label>
        <input type="number" id="pincode" name="pincode" required placeholder="Enter your pincode">

        <!-- Hidden Field for Total Amount -->
        <input type="hidden" name="totalAmount" value="<%= totalAmount %>">

        <!-- Payment Buttons -->
        <button type="submit" class="pay-now">Pay Now</button>
        <a href="Cart.jsp" class="cancel">Cancel</a>
    </form>
</div>

<footer>
    <p>&copy; 2024 Brundavihari. All rights reserved.</p>
</footer>
</body>
</html>
