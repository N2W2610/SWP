package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp Category ánh xạ bảng Categories, lưu danh mục nhà trọ.
 */
@Data
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã danh mục

    private String name; // Tên danh mục
}