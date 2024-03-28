/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dal.AccountDAO;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditProfileController", urlPatterns = {"/edit-profile", "/change-password"})
public class EditProfileController extends HttpServlet {

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
            out.println("<title>Servlet EditProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileController at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {

            AccountDAO dao = new AccountDAO();
            if ("/edit-profile".equals(servletPath)) {
                // Handle edit profile form submission

                // Retrieve form data
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                boolean isUpdate = dao.updateAccount(a.getId(), name, email, address);
                if (isUpdate) {
                    request.setAttribute("successMessage", "Profile information updated!");
                } else {
                    request.setAttribute("errorMessage", "Failed to update profile information");
                }
            } else if ("/change-password".equals(servletPath)) {
                // Handle change password form submission

                // Retrieve form data
                String currentPassword = request.getParameter("currentPassword");
                String newPassword = request.getParameter("newPassword");
                String confirmNewPassword = request.getParameter("confirmNewPassword");

                // TODO: Validate the current password and new password
                if (currentPassword.equals(a.getPassword())) {
                    if (newPassword.equals(confirmNewPassword)) {
                        // TODO: Update the user's password in the database
                        boolean isUpdate = dao.updatePassword(a.getId(), newPassword);
                        if (isUpdate) {
                            request.setAttribute("successMessage", "Password changed successfully");
                        } else {
                            request.setAttribute("errorMessage", "Failed to change password");
                        }
                    } else {
                        // Passwords do not match
                        request.setAttribute("errorMessage", "Passwords do not match");
                    }
                } else {
                    request.setAttribute("errorMessage", "Wrong password");
                }
            }
        } else {
            response.sendRedirect("home");
            return;
        }

        // Forward the request to the user-profile page
        request.getRequestDispatcher("user-profile").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
