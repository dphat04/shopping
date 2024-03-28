/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.ProductDAO;
import model.Account;
import model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Color;
import model.Size;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddCartContronller", urlPatterns = {"/add-cart"})
public class AddCartContronller extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCartContronller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCartContronller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Check if the user is authenticated
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            // User is not logged in, handle the authentication flow
            // Redirect the user to the login page or display an error message
            response.sendRedirect("login.jsp");
            return;
        }

        // User is authenticated, proceed with adding the product to their cart
        ProductDAO productDAO = new ProductDAO();

        // Retrieve the user's cart from the session or create a new one if it doesn't exist
        Map<Integer, List<Product>> cartMap = (Map<Integer, List<Product>>) session.getAttribute("cartMap");
        if (cartMap == null) {
            cartMap = new HashMap<>();
            session.setAttribute("cartMap", cartMap);
        }

        // Retrieve or create the cart for the current user
        List<Product> cart = cartMap.get(account.getId());
        if (cart == null) {
            cart = new ArrayList<>();
            cartMap.put(account.getId(), cart);
        }

        // Add the product to the cart or increment quantity if it already exists
       int cid = Integer.parseInt(request.getParameter("color"));
       int sid = Integer.parseInt(request.getParameter("size"));
        boolean productExists = false;
        for (Product p : cart) {
            if (p.getId() == productId && p.getSizeId() ==sid && p.getColorId() == cid) {
                p.setQuantity(p.getQuantity() + 1);
                productExists = true;
                break;
            }
        }
        Color c = productDAO.getColorbyId(cid);
        Size s = productDAO.getSizesbyId(sid);
        
        if (!productExists) {
            Product product = productDAO.getProductByID(productId);
            product.setQuantity(1);
            product.setColorName(c.getName());// Set selected color
            product.setColorId(cid);// Set selected color
            product.setSizeId(sid);// Set selected color
            product.setSizeName(s.getName());// Set selected size
            cart.add(0, product); // Add the product at the beginning of the list
        }

        // Update the "cart" attribute in the session
        // Redirect to the cart page
        session.setAttribute("cartMap", cartMap);
//      int cartSize = cart.stream()
//                       .mapToInt(Product::getQuantity)
//                       .sum();
        int cartSize = 0;
        for (Product product : cart) {
            cartSize += product.getQuantity();
        }
        session.setAttribute("cartSize", cartSize);
        response.sendRedirect("cart.jsp");
        
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
