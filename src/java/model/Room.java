package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Room ánh xạ bảng Rooms, lưu thông tin phòng trọ.
 */
@Data
@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã phòng

    private String title; // Tiêu đề

    private String image; // URL hình ảnh

    private String address; // Địa chỉ

    private Double price; // Giá thuê

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo
}