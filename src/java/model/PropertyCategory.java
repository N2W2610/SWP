package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp PropertyCategory ánh xạ bảng PropertyCategories, lưu quan hệ nhà trọ-danh mục.
 */
@Data
@Entity
@Table(name = "PropertyCategories")
@IdClass(PropertyCategoryId.class)
public class PropertyCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Danh mục
}

/**
 * Lớp PropertyCategoryId định nghĩa khóa chính tổng hợp.
 */
@Embeddable
class PropertyCategoryId implements java.io.Serializable {
    private Integer property; // Mã nhà trọ
    private Integer category; // Mã danh mục
}