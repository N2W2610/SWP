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
import model.User;
import model.UserRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Dung Thuy
 */
public class AdminApproveRequestServlet extends HttpServlet {
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
            out.println("<title>Servlet AdminApproveRequestServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminApproveRequestServlet at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");
        if (admin == null || admin.getRole().getId() != 1) { // Chỉ quản trị viên
            response.sendRedirect("/views/guest_login.jsp");
            return;
        }

        EntityManager em = emf.createEntityManager();
        try {
            // Lấy danh sách yêu cầu nhân viên
            List<UserRequest> requests = em.createQuery("SELECT r FROM UserRequest r WHERE r.status = 'pending'", UserRequest.class)
                    .getResultList();
            request.setAttribute("requests", requests);
            request.getRequestDispatcher("/views/admin_requests.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tải danh sách yêu cầu: " + e.getMessage());
            request.getRequestDispatcher("/views/admin_requests.jsp").forward(request, response);
        } finally {
            em.close();
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
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");
        if (admin == null || admin.getRole().getId() != 1) {
            response.sendRedirect("/views/guest_login.jsp");
            return;
        }

        String requestId = request.getParameter("requestId");
        String action = request.getParameter("action"); // approve/reject

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            UserRequest userRequest = em.find(UserRequest.class, Integer.parseInt(requestId));
            if (userRequest != null) {
                if ("approve".equals(action)) {
                    // Tạo tài khoản nhân viên
                    User user = new User();
                    user.setFullName(userRequest.getFullName());
                    user.setEmail(userRequest.getEmail());
                    user.setPassword(userRequest.getPassword()); // Đã mã hóa từ UserRequest
                    user.setPhone(userRequest.getPhone());
                    user.setRole(userRequest.getRole());
                    user.setStatus(true);
                    em.persist(user);
                    userRequest.setStatus("approved");
                } else {
                    userRequest.setStatus("rejected");
                }
                em.merge(userRequest);
                em.getTransaction().commit();
            }
            response.sendRedirect("/admin-requests");
        } catch (Exception e) {
            request.setAttribute("error", "Xử lý yêu cầu thất bại: " + e.getMessage());
            request.getRequestDispatcher("/admin-requests").forward(request, response);
        } finally {
            em.close();
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
    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
