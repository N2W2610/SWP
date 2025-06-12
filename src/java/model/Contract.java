package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Lớp Contract ánh xạ bảng Contracts, lưu hợp đồng thuê nhà.
 */
@Data
@Entity
@Table(name = "Contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã hợp đồng

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người thuê

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ

    @Column(name = "start_date")
    private LocalDate startDate; // Ngày bắt đầu

    @Column(name = "end_date")
    private LocalDate endDate; // Ngày kết thúc

    private String status; // Trạng thái hợp đồng
}