<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
    /* General body styling */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background: linear-gradient(to bottom, #ff7e5f, #feb47b);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    /* Styling the form container */
    form {
        background: #fff;
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        width: 300px;
        text-align: center;
    }

    /* Styling inputs */
    input[type="email"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 14px;
    }

    input[type="email"]:focus,
    input[type="password"]:focus {
        outline: none;
        border-color: #ff7e5f;
    }

    /* Styling submit button */
    input[type="submit"] {
        background: #ff7e5f;
        color: #fff;
        border: none;
        padding: 10px;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background 0.3s ease;
    }

    input[type="submit"]:hover {
        background: #feb47b;
    }

    h2 {
        font-family: 'Georgia', serif; 
        background: linear-gradient(to right, #ff7e5f, #feb47b); /* Gradient background */
        color: #fff;
        padding: 10px 20px;
        border-radius: 8px;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* Subtle text shadow */
        display: inline-block; /* To fit around the text */
    }
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background: url('image/login1.jpeg') no-repeat center center fixed; /* Replace with your image path */
        background-size: cover; /* Makes the image cover the entire viewport */
        height: 100vh; /* Full viewport height */
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    h3 {
            color: #2c3e50;
            font-size: 28px;
            background-color: #ecf0f1;
            padding: 20px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h3 span {
            display: block;
            font-size: 20px;
            color: #3498db;
            margin-top: 10px;
        }
</style>
</head>
<body>
		 <h3>
         Successfully Registered.
        <span>Please Login</span>
        </h3>
	
    <form action="LoginServ" method="post">
      <h2>Brundavihari Food Application Login</h2>
        <label for="email">Email</label><br>
        <input type="email" id="email" name="email" placeholder="Enter your email" required><br>
        <label for="password">Password</label><br>
        <input type="password" id="password" name="password" placeholder="Enter your password" required><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
