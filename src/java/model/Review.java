package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Review ánh xạ bảng Reviews, lưu đánh giá nhà trọ.
 */
@Data
@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã đánh giá

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người đánh giá

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ

    private Integer rating; // Điểm đánh giá (1-5)

    private String comment; // Bình luận

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo
}