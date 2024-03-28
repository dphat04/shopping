/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.OrderDAO;
import model.Account;
import model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddOrderController", urlPatterns = {"/order"})
public class AddOrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");
    if (account != null) {
        int accountId = Integer.parseInt(request.getParameter("accountId"));

        // Retrieve the product IDs, quantities, and prices from the request parameters
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("total");
        String[] colors = request.getParameterValues("color");
        String[] sizes = request.getParameterValues("size");

        // Create a list to store the order details
        OrderDAO orderDAO = new OrderDAO();
        if (productIds == null || productIds.length == 0) {
            String mess = "You have no product in cart! Please add to cart first";
            response.sendRedirect("cart.jsp?mess=" + URLEncoder.encode(mess, "UTF-8"));
        } else {
            // Iterate over the product IDs and retrieve the corresponding quantity and price
            for (int i = 0; i < productIds.length; i++) {
                int productId = Integer.parseInt(productIds[i]);
                int quantity = Integer.parseInt(quantities[i]);
                double price = Double.parseDouble(prices[i]);
                int color = Integer.parseInt(colors[i]);
                int size = Integer.parseInt(sizes[i]);

                // Create an Order object and add it to the list
                Order order = new Order(accountId, productId,size,color, quantity, price);

                orderDAO.addOrder(order);
                session.removeAttribute("cartMap");
                session.removeAttribute("cartSize");
                System.out.println("Order added successfully");
            }
        response.sendRedirect("thank");
        }
    } else {
        response.sendRedirect("home");
    }
}


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
