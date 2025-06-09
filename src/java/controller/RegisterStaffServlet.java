/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import db.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.PreparedStatement;


/**
 *
 * @author Dung Thuy
 */
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 15 * 1024 * 1024
)
public class RegisterStaffServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet RegisterStaffServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterStaffServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String fullName = request.getParameter("fullName").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String phone = request.getParameter("phone").trim();

        // Upload thư mục
        String uploadPath = request.getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // Upload files
        Part portrait = request.getPart("portrait");
        Part cccdFront = request.getPart("cccdFront");
        Part cccdBack = request.getPart("cccdBack");

        String portraitName = Paths.get(portrait.getSubmittedFileName()).getFileName().toString();
        String cccdFrontName = Paths.get(cccdFront.getSubmittedFileName()).getFileName().toString();
        String cccdBackName = Paths.get(cccdBack.getSubmittedFileName()).getFileName().toString();

        String portraitUrl = "uploads/" + System.currentTimeMillis() + "_portrait_" + portraitName;
        String frontUrl = "uploads/" + System.currentTimeMillis() + "_front_" + cccdFrontName;
        String backUrl = "uploads/" + System.currentTimeMillis() + "_back_" + cccdBackName;

        portrait.write(uploadPath + File.separator + portraitUrl.replace("uploads/", ""));
        cccdFront.write(uploadPath + File.separator + frontUrl.replace("uploads/", ""));
        cccdBack.write(uploadPath + File.separator + backUrl.replace("uploads/", ""));

        // Ghi vào DB
        try (PreparedStatement ps = new DBContext().getConnection().prepareStatement(
                "INSERT INTO UserRequests (full_name, email, password, phone, role_id, status, portrait_url, id_card_front_url, id_card_back_url) " +
                "VALUES (?, ?, ?, ?, ?, 'pending', ?, ?, ?)"
        )) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);
            ps.setInt(5, 2); // role_id = 2 (staff)
            ps.setString(6, portraitUrl);
            ps.setString(7, frontUrl);
            ps.setString(8, backUrl);
            ps.executeUpdate();

            session.setAttribute("message", "Gửi yêu cầu đăng ký thành công! Vui lòng chờ admin duyệt.");
            response.sendRedirect("views/login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            request.getRequestDispatcher("views/registerStaff.jsp").forward(request, response);
        }
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
