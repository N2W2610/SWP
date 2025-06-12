package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Log ánh xạ bảng Logs, lưu nhật ký hoạt động người dùng.
 */
@Data
@Entity
@Table(name = "Logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã nhật ký

    private String action; // Hành động

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người thực hiện

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo
}