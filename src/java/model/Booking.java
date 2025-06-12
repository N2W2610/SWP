package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Lớp Booking ánh xạ bảng Bookings, lưu thông tin đặt phòng.
 */
@Data
@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã đặt chỗ

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người đặt

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ

    @Column(name = "booking_date")
    private LocalDateTime bookingDate; // Ngày đặt

    private String status; // Trạng thái (Chờ xác nhận, Đã xác nhận, Hủy)

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments; // Thanh toán
}