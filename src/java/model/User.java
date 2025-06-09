/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dung Thuy
 */
public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private int roleId;
    private boolean status;
    private String oauthProvider;
    private String oauthId;
    private String idCardUrl;

    public User() {
    }

    public User(int id, String fullName, String email, String password, String phone,
                int roleId, boolean status, String oauthProvider, String oauthId, String idCardUrl) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.roleId = roleId;
        this.status = status;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.idCardUrl = idCardUrl;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOauthProvider() {
        return oauthProvider;
    }
    public void setOauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getOauthId() {
        return oauthId;
    }
    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public String getIdCardUrl() {
        return idCardUrl;
    }
    public void setIdCardUrl(String idCardUrl) {
        this.idCardUrl = idCardUrl;
    }
}
