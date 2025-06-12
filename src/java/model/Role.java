package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp Role ánh xạ bảng Roles, lưu thông tin vai trò người dùng.
 */
@Data
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    private Integer id; // Mã vai trò

    private String name; // Tên vai trò (Quản trị viên, Nhân viên, Sinh viên, Chủ trọ)
}