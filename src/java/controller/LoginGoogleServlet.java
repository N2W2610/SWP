/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Role;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Dung Thuy
 */
public class LoginGoogleServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }
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
            out.println("<title>Servlet LoginGoogleServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginGoogleServlet at " + request.getContextPath () + "</h1>");
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
        // Lấy thông tin từ Google OAuth (giả định nhận từ query params)
        String googleId = request.getParameter("googleId");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");

        // TODO: Xác thực Google OAuth token với Google API
        // Hiện tại giả định thông tin hợp lệ từ client-side
        if (googleId == null || email == null) {
            request.setAttribute("error", "Thông tin Google không hợp lệ.");
            request.getRequestDispatcher("/views/guest_login.jsp").forward(request, response);
            return;
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Kiểm tra người dùng đã tồn tại
            User user = em.createQuery("SELECT u FROM User u WHERE u.oauthId = :oauthId AND u.oauthProvider = 'google'", User.class)
                    .setParameter("oauthId", googleId)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                // Tạo người dùng mới
                user = new User();
                user.setEmail(email);
                user.setFullName(fullName);
                user.setOauthProvider("google");
                user.setOauthId(googleId);
                Role role = em.find(Role.class, 3); // Role ID 3: Sinh viên
                user.setRole(role);
                user.setStatus(true);
                em.persist(user);
            }

            em.getTransaction().commit();

            // Lưu thông tin người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/house-list");
        } catch (Exception e) {
            request.setAttribute("error", "Đăng nhập Google thất bại: " + e.getMessage());
            request.getRequestDispatcher("/views/guest_login.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
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
        processRequest(request, response);
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
