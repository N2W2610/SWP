package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp UserRequest ánh xạ bảng UserRequests, lưu yêu cầu đăng ký nhân viên.
 */
@Data
@Entity
@Table(name = "UserRequests")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã yêu cầu

    @Column(name = "full_name")
    private String fullName; // Họ và tên

    private String email; // Email

    private String password; // Mật khẩu

    private String phone; // Số điện thoại

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role; // Vai trò (Nhân viên)

    private String status = "pending"; // Trạng thái (pending, approved, rejected)

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo

    @Column(name = "portrait_url")
    private String portraitUrl; // URL ảnh chân dung

    @Column(name = "id_card_front_url")
    private String idCardFrontUrl; // URL mặt trước CCCD

    @Column(name = "id_card_back_url")
    private String idCardBackUrl; // URL mặt sau CCCD
}