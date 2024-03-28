/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.AccountDAO;
import dal.ProductDAO;
import model.Account;
import jakarta.servlet.RequestDispatcher;
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
import java.util.List;
import model.Color;
import model.Size;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "checkOutController", urlPatterns = {"/check-out"})
public class checkOutController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkOutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkOutController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming you have the cart data available in a map called "cartMap" and the total in a variable called "total"

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
    // Retrieve the cart information from the request parameters
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");
    if (account != null) {
        String[] productIds = request.getParameterValues("productId[]");
          if (productIds==null) {
            response.sendRedirect("cart.jsp?mess=You must add product to cart first!");
            return;
        }
        String[] productNames = request.getParameterValues("productName[]");
        String[] productPrices = request.getParameterValues("productPrice[]");
        String[] productQuantities = request.getParameterValues("productQuantity[]");
        String[] productSizeIds = request.getParameterValues("productSize[]");
        String[] productColorIds = request.getParameterValues("productColor[]");
        String total = request.getParameter("total");
      
        // Fetch color and size names for each product
        ProductDAO productDAO = new ProductDAO();
        List<String> colorNames = new ArrayList<>();
        List<String> sizeNames = new ArrayList<>();
        for (String colorId : productColorIds) {
            int cid = Integer.parseInt(colorId);
            Color color = productDAO.getColorbyId(cid);
            colorNames.add(color.getName());
        }
        for (String sizeId : productSizeIds) {
            int sid = Integer.parseInt(sizeId);
            Size size = productDAO.getSizesbyId(sid);
            sizeNames.add(size.getName());
        }

        // Set the cart and color/size information as attributes in the request
        request.setAttribute("productIds", productIds);
        request.setAttribute("productNames", productNames);
        request.setAttribute("productPrices", productPrices);
        request.setAttribute("productQuantities", productQuantities);
        request.setAttribute("productColorIds", productColorIds);
        request.setAttribute("productSizeIds", productSizeIds);
        request.setAttribute("colorNames", colorNames);
        request.setAttribute("sizeNames", sizeNames);
        request.setAttribute("productSizeIds", productSizeIds);
        request.setAttribute("productColorIds", productColorIds);
        request.setAttribute("total", total);
        AccountDAO aDao = new AccountDAO();
        Account a = aDao.getAccountByID(account.getId());
        request.setAttribute("acc", a);
        request.getRequestDispatcher("check-out.jsp").forward(request, response);

        // Forward the request to check-out.jsp
    } else {
        response.sendRedirect("home");
    }
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
