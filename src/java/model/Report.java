package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Report ánh xạ bảng Reports, lưu báo cáo về nhà trọ.
 */
@Data
@Entity
@Table(name = "Reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã báo cáo

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter; // Người báo cáo

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ

    private String reason; // Lý do báo cáo

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo
}