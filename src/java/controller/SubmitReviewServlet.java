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
import model.Property;
import model.Review;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Dung Thuy
 */
public class SubmitReviewServlet extends HttpServlet {
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
            out.println("<title>Servlet SubmitReviewServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitReviewServlet at " + request.getContextPath () + "</h1>");
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
        String propertyId = request.getParameter("propertyId");
        EntityManager em = emf.createEntityManager();
        try {
            // Lấy danh sách đánh giá cho nhà trọ
            List<Review> reviews = em.createQuery("SELECT r FROM Review r WHERE r.property.id = :propertyId", Review.class)
                    .setParameter("propertyId", Integer.parseInt(propertyId))
                    .getResultList();
            request.setAttribute("reviews", reviews);
            request.getRequestDispatcher("/views/view_reviews.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tải đánh giá: " + e.getMessage());
            request.getRequestDispatcher("/views/view_reviews.jsp").forward(request, response);
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
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/views/guest_login.jsp");
            return;
        }

        // Lấy thông tin đánh giá
        String propertyId = request.getParameter("propertyId");
        String rating = request.getParameter("rating");
        String comment = request.getParameter("comment");

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Review review = new Review();
            review.setUser(user);
            review.setProperty(em.find(Property.class, Integer.parseInt(propertyId)));
            review.setRating(Integer.parseInt(rating));
            review.setComment(comment);
            review.setCreatedAt(LocalDateTime.now());
            em.persist(review);
            em.getTransaction().commit();
            response.sendRedirect("/views/view_reviews.jsp?propertyId=" + propertyId);
        } catch (Exception e) {
            request.setAttribute("error", "Gửi đánh giá thất bại: " + e.getMessage());
            request.getRequestDispatcher("/views/view_reviews.jsp").forward(request, response);
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
