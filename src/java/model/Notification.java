package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Notification ánh xạ bảng Notifications, lưu thông báo người dùng.
 */
@Data
@Entity
@Table(name = "Notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã thông báo

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người nhận

    private String message; // Nội dung thông báo

    private Boolean seen = false; // Đã xem

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo
}