/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author Dung Thuy
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    protected Connection connection;

    public DBContext() {
    try {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=HouseRentalDB";
        String user = "sa";
        String password = "12345";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Kết nối cơ sở dữ liệu thành công!");
    } catch (Exception e) {
        System.err.println("Lỗi kết nối: " + e.getMessage());
        e.printStackTrace();
    }
}
}