/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import model.Category;
import model.Account;
import model.Product;
import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.Color;
import model.Size;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditProductController", urlPatterns = {"/edit-product"})
public class EditProductController extends HttpServlet {

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
            out.println("<title>Servlet EditProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProductController at " + request.getContextPath() + "</h1>");
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
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductByID(productId);
        System.out.println(product);
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAllCategory();
        List<Color> color = productDAO.getAllColors();
        List<Size> size = productDAO.getAllSizes();
        for (Size size1 : size) {
            System.out.println(size1);
        }
        request.setAttribute("categories", categories);
        request.setAttribute("color", color);
        request.setAttribute("size", size);
        request.setAttribute("product", product);
        request.getRequestDispatcher("edit-product.jsp").forward(request, response);
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
    int productId = Integer.parseInt(request.getParameter("id"));
    String productName = request.getParameter("name");
    double productPrice = Double.parseDouble(request.getParameter("price"));
    int productQuantity = Integer.parseInt(request.getParameter("quantity"));
    String productImage = request.getParameter("image");
    int categoryId = Integer.parseInt(request.getParameter("category"));
    String productDescription = request.getParameter("description");

    // Get the selected sizes
    String[] selectedSizes = request.getParameterValues("sizes");

    // Get the selected colors
    String[] selectedColors = request.getParameterValues("colors");

    // Update the product with the new information
    ProductDAO productDAO = new ProductDAO();
    Product product = productDAO.getProductByID(productId);
    product.setName(productName);
    product.setPrice(productPrice);
    product.setQuantity(productQuantity);
    product.setImage(productImage);
    product.setCategory(categoryId);
    product.setDescription(productDescription);
    // Set the selected sizes
    if (selectedSizes != null) {
        List<Size> sizes = new ArrayList<>();
        for (String sizeId : selectedSizes) {
            int sizeIdInt = Integer.parseInt(sizeId);
            Size size = new Size(sizeIdInt, ""); // Set the appropriate name if available
            sizes.add(size);
        }
        product.setListSize(sizes);
    }
    // Set the selected colors
    if (selectedColors != null) {
        List<Color> colors = new ArrayList<>();
        for (String colorId : selectedColors) {
            int colorIdInt = Integer.parseInt(colorId);
            Color color = new Color(colorIdInt, ""); // Set the appropriate name if available
            colors.add(color);
        }
        product.setListColor(colors);
    }

    // Update the product in the database
    productDAO.updateProduct(product);

    // Redirect to the product details page or any other appropriate page
    response.sendRedirect("product-management?mess=Update Success");
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
