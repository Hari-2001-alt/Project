<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<style>
     body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background: url('image/reg.jpg') no-repeat center center fixed; /* Replace with your image path */
        background-size: cover; /* Makes the image cover the entire viewport */
        height: 100vh; /* Full viewport height */
        display: flex;
        justify-content: center;
        align-items: center;
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
    form {
        background: #fff;
        max-width: 400px;
        margin: 50px auto;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    }
    label {
        display: block;
        margin: 10px 0 5px;
    }
    input[type="text"],
    input[type="email"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin: 5px 0 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    input[type="submit"] {
        background-color: #007BFF;
        color: white;
        border: none;
        padding: 10px 15px;
        cursor: pointer;
        border-radius: 5px;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
    .register-container {
      background-color: #ffffff;
      border-radius: 10px;
      padding: 20px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
    }

    .form-group {
      margin-bottom: 15px;
    }

    .form-group label {
      font-size: 1.1em;
      color: #555;
      display: block;
      margin-bottom: 5px;
    }

    .form-group input {
      width: 100%;
      padding: 10px;
      font-size: 1em;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    .form-group input:focus {
      border-color: #4CAF50;
      outline: none;
    }

    .submit-btn {
      width: 100%;
      padding: 12px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      font-size: 1.1em;
      cursor: pointer;
    }

    .submit-btn:hover {
      background-color: #45a049;
    }
</style>
</head>
<body>
	
    <form action="Register" method="post">
    <h2>Register for  BrundaVihari Food Application</h2>
    
    <label for="username">Username</label>
    <input type="text" name="username" id="username" required>
    
    <label for="email">Email</label>
    <input type="email" name="email" id="email" required>
    
    <label for="password">Password</label>
    <input type="password" name="password" id="password" required>
    
    <label for="cpassword">Confirm Password</label>
    <input type="password" name="cpassword" id="cpassword" required>
    
    <label for="address">Address</label>
    <input type="text" name="address" id="address" required>
    
    
    
    
    <input type="submit" value="Register">
    <p> If you have already an account<a href="login.jsp">Login here</a>
</form>

</body>
</html>