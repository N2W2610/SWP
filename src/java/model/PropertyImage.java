package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp PropertyImage ánh xạ bảng PropertyImages, lưu hình ảnh nhà trọ.
 */
@Data
@Entity
@Table(name = "PropertyImages")
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã hình ảnh

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ liên quan

    @Column(name = "image_url")
    private String imageUrl; // URL hình ảnh
}