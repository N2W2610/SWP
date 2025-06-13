/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author VietHoang
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Feedback;

public class FeedbackDAO1 {
    private Connection conn;

    public FeedbackDAO(Connection conn) {
        this.conn = conn;
    }

    public void addFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO Feedbacks (user_id, content, status) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, feedback.getUserId());
        ps.setString(2, feedback.getContent());
        ps.setString(3, feedback.getStatus());
        ps.executeUpdate();
    }
}
