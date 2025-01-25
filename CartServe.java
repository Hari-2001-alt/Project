package com.tap;

import com.controller.Dao.MenuDao;
import com.controller.DaoImpl.MenuDaoImpl;
import com.controller.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Cart")
public class CartServe extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("Cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("Cart", cart);
        }

        String action = req.getParameter("action");
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        int quantity = 1;  // Default quantity
        
        try {
            if ("add".equals(action)) {
                MenuDao menuDao = new MenuDaoImpl();
                Menu menuItem = menuDao.getMenuById(itemId);
                if (menuItem != null) {
                    quantity = Integer.parseInt(req.getParameter("quantity"));
                    CartItem cartItem = new CartItem(menuItem.getMid(), menuItem.getRid(), menuItem.getMname(), menuItem.getPrice(), quantity);
                    cart.addItem(cartItem);
                    session.setAttribute("rid", menuItem.getRid());
                }
            } 
            else if ("remove".equals(action)) {
                cart.removeItem(itemId);
            } 
            else if ("update".equals(action)) {
                
                quantity = Integer.parseInt(req.getParameter("quantity"));
                cart.updateItem(itemId, quantity); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        session.setAttribute("Cart", cart);
        resp.sendRedirect("cart.jsp");
    }
    
   
}



