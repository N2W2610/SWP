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
import model.Role;
import model.UserRequest;

import java.io.IOException;
import java.io.PrintWriter;
import model.User;

/**
 *
 * @author Dung Thuy
 */
public class RegisterStaffServlet extends HttpServlet {
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
        // Lấy thông tin từ form đăng ký nhân viên
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String portraitUrl = request.getParameter("portraitUrl");
        String idCardFrontUrl = request.getParameter("idCardFrontUrl");
        String idCardBackUrl = request.getParameter("idCardBackUrl");

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Kiểm tra email đã tồn tại
            if (em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email).getResultList().isEmpty()) {
                UserRequest userRequest = new UserRequest();
                userRequest.setFullName(fullName);
                userRequest.setEmail(email);
                userRequest.setPassword(password); // TODO: Mã hóa mật khẩu
                userRequest.setPhone(phone);
                userRequest.setPortraitUrl(portraitUrl);
                userRequest.setIdCardFrontUrl(idCardFrontUrl);
                userRequest.setIdCardBackUrl(idCardBackUrl);
                Role role = em.find(Role.class, 2); // Role ID 2: Nhân viên
                userRequest.setRole(role);
                userRequest.setStatus("pending");
                em.persist(userRequest);
                em.getTransaction().commit();
                response.sendRedirect("/views/guest_login.jsp?message=Yêu cầu đăng ký nhân viên đã được gửi.");
            } else {
                request.setAttribute("error", "Email đã được sử dụng.");
                request.getRequestDispatcher("/views/guest_register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Đăng ký nhân viên thất bại: " + e.getMessage());
            request.getRequestDispatcher("/views/guest_register.jsp").forward(request, response);
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
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
