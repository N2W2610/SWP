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
import model.Area;
import model.Property;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Dung Thuy
 */
public class HouseSearchServlet extends HttpServlet {
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
            out.println("<title>Servlet HouseSearchServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HouseSearchServlet at " + request.getContextPath () + "</h1>");
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
        EntityManager em = emf.createEntityManager();
        try {
            // Lấy danh sách khu vực cho dropdown
            List<Area> areas = em.createQuery("SELECT a FROM Area a", Area.class).getResultList();
            request.setAttribute("areas", areas);

            // Lấy tham số tìm kiếm
            String areaId = request.getParameter("areaId");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");

            // Xây dựng truy vấn động
            String query = "SELECT p FROM Property p WHERE 1=1";
            if (areaId != null && !areaId.isEmpty()) {
                query += " AND p.area.id = :areaId";
            }
            if (minPrice != null && !minPrice.isEmpty()) {
                query += " AND p.price >= :minPrice";
            }
            if (maxPrice != null && !maxPrice.isEmpty()) {
                query += " AND p.price <= :maxPrice";
            }

            var jpqlQuery = em.createQuery(query, Property.class);
            if (areaId != null && !areaId.isEmpty()) {
                jpqlQuery.setParameter("areaId", Integer.parseInt(areaId));
            }
            if (minPrice != null && !minPrice.isEmpty()) {
                jpqlQuery.setParameter("minPrice", Double.parseDouble(minPrice));
            }
            if (maxPrice != null && !maxPrice.isEmpty()) {
                jpqlQuery.setParameter("maxPrice", Double.parseDouble(maxPrice));
            }

            List<Property> properties = jpqlQuery.getResultList();
            request.setAttribute("properties", properties);
            request.getRequestDispatcher("/views/house_search.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tìm kiếm nhà trọ: " + e.getMessage());
            request.getRequestDispatcher("/views/house_search.jsp").forward(request, response);
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
