package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp PropertyAmenity ánh xạ bảng PropertyAmenities, lưu quan hệ nhà trọ-tiện ích.
 */
@Data
@Entity
@Table(name = "PropertyAmenities")
@IdClass(PropertyAmenityId.class)
public class PropertyAmenity {
    @Id
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ

    @Id
    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private Amenity amenity; // Tiện ích
}

/**
 * Lớp PropertyAmenityId định nghĩa khóa chính tổng hợp.
 */
@Embeddable
class PropertyAmenityId implements java.io.Serializable {
    private Integer property; // Mã nhà trọ
    private Integer amenity; // Mã tiện ích
}