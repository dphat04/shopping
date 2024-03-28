/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import model.Category;
import model.Product;
import model.Account;
import dal.ProductDAO;
import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Color;
import model.Size;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "homeController", urlPatterns = {"/home"})
public class homeController extends HttpServlet {

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
            out.println("<title>Servlet homeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet homeController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

            // Get the page and search parameters from the request
            String pageParam = request.getParameter("page");
            String searchParam = request.getParameter("search");
            int page = 1; // Default to the first page
            int pageSize = 6; // Set the desired page size
            String categoryParam = request.getParameter("category");
            String sortOption = request.getParameter("sort");
            String colorId = request.getParameter("color"); // New color parameter
            String sizeId =request.getParameter("size"); // New size parameter

            if (pageParam != null && !pageParam.isEmpty()) {
                // Parse the page parameter to an integer
                page = Integer.parseInt(pageParam);
            }
            if (sortOption == null) {
                sortOption = "";
            }

            ProductDAO productDAO = new ProductDAO();
            List<Product> list3 = productDAO.Get3Product();
            List<Product> products = productDAO.searchProductsWithConditions(searchParam, categoryParam, sortOption, colorId, sizeId); // Include color and size parameters
            List<Product> productList = productDAO.Paging(products, page, pageSize);

            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categories = categoryDAO.getAllCategory();

            List<Color> colors = productDAO.getAllColors(); // Retrieve all available colors

            List<Size> sizes = productDAO.getAllSizes(); // Retrieve all available sizes

            request.setAttribute("searchParam", searchParam);
            request.setAttribute("listC", categories);
            request.setAttribute("listP", productList);
            request.setAttribute("list3", list3);
            request.setAttribute("listColors", colors); // Pass the list of colors to the JSP
            request.setAttribute("listSizes", sizes); // Pass the list of sizes to the JSP
            request.setAttribute("totalPages", products.size() % pageSize == 0 ? (products.size() / pageSize) : (products.size() / pageSize + 1));
            request.setAttribute("currentPage", page);
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
