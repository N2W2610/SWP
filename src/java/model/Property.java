package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Lớp Property ánh xạ bảng Properties, lưu thông tin nhà trọ.
 */
@Data
@Entity
@Table(name = "Properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã nhà trọ

    private String title; // Tiêu đề

    private String description; // Mô tả

    private Double price; // Giá thuê

    private String address; // Địa chỉ

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area; // Khu vực

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord; // Chủ trọ

    private String status; // Trạng thái (Đang cho thuê, Còn trống)

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo

    @OneToMany(mappedBy = "property")
    private List<PropertyImage> images; // Hình ảnh nhà trọ

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings; // Đặt phòng

    @OneToMany(mappedBy = "property")
    private List<Review> reviews; // Đánh giá

    @OneToMany(mappedBy = "property")
    private List<Favorite> favorites; // Yêu thích

    @OneToMany(mappedBy = "property")
    private List<PropertyCategory> categories; // Danh mục

    @OneToMany(mappedBy = "property")
    private List<PropertyAmenity> amenities; // Tiện ích

    @OneToMany(mappedBy = "property")
    private List<Report> reports; // Báo cáo

    @OneToMany(mappedBy = "property")
    private List<Contract> contracts; // Hợp đồng
}