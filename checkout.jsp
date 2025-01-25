<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    
     <style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f1f3f5;
        margin: 0;
        padding: 0;
    }
    .checkout-container {
        width: 60%;
        margin: 50px auto;
        background: #ffffff;
        padding: 30px;
        border-radius: 15px;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s, box-shadow 0.3s ease;
    }
    .checkout-container:hover {
        transform: translateY(-5px);
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
    }
    h1 {
        text-align: center;
        color: #343a40;
        font-size: 32px;
        margin-bottom: 20px;
    }
    label {
        font-weight: 600;
        color: #495057;
        display: block;
        margin-top: 20px;
        font-size: 16px;
    }
    textarea, select, input[type="text"], input[type="hidden"] {
        width: 100%;
        padding: 12px;
        margin-top: 10px;
        margin-bottom: 20px;
        border: 1px solid #ced4da;
        border-radius: 8px;
        font-size: 16px;
        font-family: 'Segoe UI', sans-serif;
        color: #495057;
        transition: border-color 0.3s, box-shadow 0.3s ease;
        background-color: #fafafa;
    }
    textarea::placeholder, input[type="text"]::placeholder {
        font-style: italic;
        color: #868e96;
        font-size: 14px;
    }
    textarea:focus, select:focus, input[type="text"]:focus {
        border-color: #80bdff;
        box-shadow: 0 0 10px rgba(128, 189, 255, 0.6);
        outline: none;
        background-color: #ffffff;
        transition: all 0.2s ease;
    }
    textarea:focus::placeholder, input[type="text"]:focus::placeholder {
        color: #6c757d;
        font-style: normal;
    }
    button {
        background-color: #28a745;
        color: #ffffff;
        font-size: 18px;
        padding: 14px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        width: 100%;
        transition: background-color 0.3s, transform 0.2s ease;
    }
    button:hover {
        background-color: #218838;
        transform: scale(1.05);
    }
    button:active {
        background-color: #1e7e34;
        transform: scale(0.98);
    }
    .form-note {
        font-size: 14px;
        color: #6c757d;
        margin-top: -8px;
        margin-bottom: 20px;
        display: block;
    }
    /* Responsive Design */
    @media screen and (max-width: 768px) {
        .checkout-container {
            width: 75%;
            padding: 25px;
        }
        h1 {
            font-size: 28px;
        }
        button {
            font-size: 16px;
        }
    }
    @media screen and (max-width: 480px) {
        .checkout-container {
            width: 90%;
            padding: 20px;
        }
        h1 {
            font-size: 24px;
        }
        label {
            font-size: 14px;
        }
        textarea, select, input[type="text"], input[type="hidden"], button {
            font-size: 14px;
            padding: 10px;
        }
    }
</style>


</head>
<body>
    <div class="checkout-container">
        <h1>Checkout</h1>
        <form action="checkout" method="post">
            <label>Address:</label>
            <textarea name="address" required placeholder="Enter your address"></textarea>

            <label>City:</label>
            <input type="text" name="city" required placeholder="Enter your city" />

            <label>Pincode:</label>
            <input type="text" name="pincode" required pattern="[0-9]{6}" placeholder="Enter your 6-digit pincode" />
            <span class="form-note">Pincode should be a 6-digit number.</span>

            <label for="paymentMode">Payment Mode:</label>
            <select id="paymentMode" name="paymentMode" required>
                <option value="Credit Card">Credit Card</option>
                <option value="Debit Card">Debit Card</option>
                <option value="UPI">UPI Payment</option>
                <option value="Cash on Delivery">Cash on Delivery</option>
            </select>

            <button type="submit">Place Order</button>
        </form>
    </div>
</body>
</html>
