<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.controller.model.Orderss" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap');

        body {
            font-family: 'Poppins', Arial, sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .confirmation-container {
            background-color: #ffffff;
            width: 90%;
            max-width: 500px;
            aspect-ratio: 1 / 1;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            transform: translateY(20px);
            opacity: 0;
            animation: fadeIn 0.8s ease-out forwards;
        }

        @keyframes fadeIn {
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }

        .gif-container {
            margin-bottom: 15px;
            overflow: hidden;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            flex-grow: 1;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .gif-container img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain;
            transition: transform 0.3s ease;
        }

        .gif-container:hover img {
            transform: scale(1.05);
        }

        h1 {
            color: #4CAF50;
            font-size: 2.5em;
            margin-bottom: 10px;
            font-weight: 700;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
            letter-spacing: 1px;
        }

        p {
            font-size: 1.2em;
            color: #555;
            line-height: 1.6;
            margin-bottom: 15px;
            font-weight: 300;
        }

        .order-details {
            background-color: #f9f9f9;
            border-radius: 10px;
            padding: 15px;
            text-align: center;
            transition: all 0.3s ease;
            margin-top: 15px;
        }

        .order-details:hover {
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .order-details p {
            margin: 5px 0;
            font-size: 1.1em;
        }

        strong {
            color: #333;
            font-weight: 600;
        }

        .success-icon {
            font-size: 3.5em;
            color: #4CAF50;
            margin-bottom: 10px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
        }

        @media (max-width: 600px) {
            .confirmation-container {
                width: 95%;
                padding: 20px;
                aspect-ratio: auto;
            }

            h1 {
                font-size: 2em;
            }

            p {
                font-size: 1em;
            }
        }
    </style>
</head>
<body>
    <div class="confirmation-container">
        <div class="success-icon">✅</div>
        <h1>Order Confirmation</h1>
        <div class="gif-container">
            <img id="deliveryGif" src="image/ord.gif" alt="Motorcycle Delivery Animation">
        </div>
        <p>Thank you for your order! Your order has been placed successfully.</p>
        <div class="order-details">
            <p><strong>Order ID:</strong> 
                <%= session.getAttribute("orderId") != null ? session.getAttribute("orderId") : "N/A" %>
            </p>
            <%--<p><strong>Total Amount:</strong> ₹ 
                <%= session.getAttribute("totalAmount") != null ? String.format("%.2f", (Float) session.getAttribute("totalAmount")) : "0.00" %>
            </p> --%>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var img = document.getElementById('deliveryGif');
            img.addEventListener('load', function() {
                if (img.complete && img.naturalWidth !== 0) {
                    console.log('GIF loaded successfully');
                } else {
                    console.log('GIF failed to load');
                }
            });
        });
    </script>
</body>
</html>