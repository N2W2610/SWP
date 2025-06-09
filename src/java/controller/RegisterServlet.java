/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import model.User;

/**
 *
 * @author Dung Thuy
 */
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,  // 1MB
    maxFileSize = 5 * 1024 * 1024,    // 5MB
    maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class RegisterServlet extends HttpServlet {
    
    private final UserDAO userDAO = new UserDAOImpl();
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
            out.println("<title>Servlet RegisterServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath () + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String phone = request.getParameter("phone").trim();
        String role = request.getParameter("role"); // "student" or "landlord"

        int roleId = role.equals("landlord") ? 4 : 3;
        String idCardPath = null;

        // Kiểm tra email trùng
        try {
            if (userDAO.checkEmailExists(email)) {
                request.setAttribute("error", "Email đã được đăng ký.");
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
                return;
            }

            // Nếu là chủ trọ → xử lý file CCCD
            if (role.equals("landlord")) {
                Part cccdPart = request.getPart("cccd");
                if (cccdPart != null && cccdPart.getSize() > 0) {
                    String fileName = Paths.get(cccdPart.getSubmittedFileName()).getFileName().toString();
                    String uploadsDir = request.getServletContext().getRealPath("/uploads");
                    File uploadsFolder = new File(uploadsDir);
                    if (!uploadsFolder.exists()) uploadsFolder.mkdirs();

                    String filePath = uploadsDir + File.separator + fileName;
                    cccdPart.write(filePath);

                    idCardPath = "uploads/" + fileName; // dùng cho hiển thị lại sau này
                } else {
                    request.setAttribute("error", "Vui lòng tải lên CCCD cho chủ trọ.");
                    request.getRequestDispatcher("views/register.jsp").forward(request, response);
                    return;
                }
            }

            // Đăng ký user
            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setRoleId(roleId);
            user.setStatus(true);
            user.setOauthProvider(null);      // tài khoản thường
            user.setOauthId(null);            // không dùng oauth_id
            user.setIdCardUrl(idCardPath);    // nếu là chủ trọ thì có

            userDAO.insertUser(user);

            response.sendRedirect("views/login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi đăng ký.");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
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
