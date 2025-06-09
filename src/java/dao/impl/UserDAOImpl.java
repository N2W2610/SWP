/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.UserDAO;
import db.DBContext;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Dung
 */
public class UserDAOImpl extends DBContext implements UserDAO {

    @Override
    public User login(String email, String password) throws Exception {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ? AND status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email.trim());
            ps.setString(2, password.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getInt("role_id"),
                        rs.getBoolean("status"),
                        rs.getString("oauth_provider"),
                        rs.getString("oauth_id"),
                        rs.getString("id_card_url")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean checkEmailExists(String email) throws Exception {
        String sql = "SELECT id FROM Users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public void register(String fullName, String email, String password) throws Exception {
        String sql = "INSERT INTO Users (full_name, email, password, role_id, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, 3); // role_id = 3 -> student
            ps.setBoolean(5, true);
            ps.executeUpdate();
        }
    }

    @Override
    public void insertUser(User user) throws Exception {
        String sql = "INSERT INTO Users (full_name, email, password, phone, role_id, status, oauth_provider, oauth_id, id_card_url) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setInt(5, user.getRoleId());
            ps.setBoolean(6, user.isStatus());
            ps.setString(7, user.getOauthProvider());
            ps.setString(8, user.getOauthId());
            ps.setString(9, user.getIdCardUrl());
            ps.executeUpdate();
        }
    }
}
