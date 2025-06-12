package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Feedback ánh xạ bảng Feedbacks, lưu phản hồi hệ thống.
 */
@Data
@Entity
@Table(name = "Feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã phản hồi

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người gửi

    private String message; // Nội dung phản hồi

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo
}