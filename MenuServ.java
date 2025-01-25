package com.tap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.controller.Dao.MenuDao;
import com.controller.DaoImpl.MenuDaoImpl;
import com.controller.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServ extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
	
	private int rid;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getParameter("rid");
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	         int rid = Integer.parseInt(req.getParameter("rid")); 
	         System.out.println(rid);
	         MenuDao menuDao = new MenuDaoImpl();
	         
	         List<Menu> mList = menuDao.getAllMenusByRestaurant(rid);
	         req.setAttribute("mList", mList); 
	         
	        
	         
	         RequestDispatcher dispatcher = req.getRequestDispatcher("Menu.jsp");
	         dispatcher.forward(req, resp);
	         
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("List of the restaurant "+rid);
	}

}
