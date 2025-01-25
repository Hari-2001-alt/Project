package com.tap;



import java.io.IOException;

import com.controller.DaoImpl.UserDaoImpl;
import com.controller.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.fetchUserByEmail(email);

        if (user != null && password.equals(user.getPassword())) 
        { 
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
