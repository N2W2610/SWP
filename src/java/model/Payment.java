package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Payment ánh xạ bảng Payments, lưu thông tin thanh toán.
 */
@Data
@Entity
@Table(name = "Payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã thanh toán

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking; // Đặt phòng liên quan

    private Double amount; // Số tiền

    @Column(name = "payment_date")
    private LocalDateTime paymentDate; // Ngày thanh toán

    private String status; // Trạng thái thanh toán
}