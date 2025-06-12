package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp Amenity ánh xạ bảng Amenities, lưu tiện ích nhà trọ.
 */
@Data
@Entity
@Table(name = "Amenities")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã tiện ích

    private String name; // Tên tiện ích
}