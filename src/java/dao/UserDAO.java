/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Dung Thuy
 */
import model.User;

public interface UserDAO {
    User login(String email, String password) throws Exception;
    boolean checkEmailExists(String email) throws Exception;
    void register(String fullName, String email, String password) throws Exception;
    void insertUser(User user) throws Exception;
}