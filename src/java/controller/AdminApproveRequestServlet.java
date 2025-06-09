/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import db.DBContext;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
/**
 *
 * @author Dung Thuy
 */
public class AdminApproveRequestServlet extends HttpServlet {
   
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
        List<String[]> requests = new ArrayList<>();
        try (PreparedStatement ps = new DBContext().getConnection().prepareStatement(
                "SELECT * FROM UserRequests WHERE status = 'pending'"
        ); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] row = new String[6];
                row[0] = rs.getString("id");
                row[1] = rs.getString("full_name");
                row[2] = rs.getString("email");
                row[3] = rs.getString("phone");
                row[4] = rs.getString("portrait_url");
                row[5] = rs.getString("id_card_front_url");
                requests.add(row);
            }
            request.setAttribute("requests", requests);
            request.getRequestDispatcher("views/adminRequestList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
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
        String action = request.getParameter("action");
        int requestId = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = new DBContext().getConnection()) {
            if ("approve".equals(action)) {
                // Copy sang bảng Users
                PreparedStatement sel = conn.prepareStatement("SELECT * FROM UserRequests WHERE id = ?");
                sel.setInt(1, requestId);
                ResultSet rs = sel.executeQuery();
                if (rs.next()) {
                    PreparedStatement ins = conn.prepareStatement(
                        "INSERT INTO Users (full_name, email, password, phone, role_id, status, oauth_provider, oauth_id, id_card_front_url, id_card_back_url, portrait_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    );
                    ins.setString(1, rs.getString("full_name"));
                    ins.setString(2, rs.getString("email"));
                    ins.setString(3, rs.getString("password"));
                    ins.setString(4, rs.getString("phone"));
                    ins.setInt(5, rs.getInt("role_id"));
                    ins.setBoolean(6, true);
                    ins.setString(7, null);
                    ins.setString(8, null);
                    ins.setString(9, rs.getString("id_card_front_url"));
                    ins.setString(10, rs.getString("id_card_back_url"));
                    ins.setString(11, rs.getString("portrait_url"));
                    ins.executeUpdate();
                }

                PreparedStatement upd = conn.prepareStatement("UPDATE UserRequests SET status = 'approved' WHERE id = ?");
                upd.setInt(1, requestId);
                upd.executeUpdate();

            } else if ("reject".equals(action)) {
                PreparedStatement upd = conn.prepareStatement("UPDATE UserRequests SET status = 'rejected' WHERE id = ?");
                upd.setInt(1, requestId);
                upd.executeUpdate();
            }
            response.sendRedirect("admin-requests");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
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
