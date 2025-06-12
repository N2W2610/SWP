package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Lớp User ánh xạ bảng Users, lưu thông tin người dùng (khách, chủ trọ, nhân viên, quản trị viên).
 */
@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã người dùng

    @Column(name = "full_name")
    private String fullName; // Họ và tên

    @Column(unique = true)
    private String email; // Email duy nhất

    private String password; // Mật khẩu (nên mã hóa)

    private String phone; // Số điện thoại

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role; // Vai trò người dùng

    private Boolean status = true; // Trạng thái hoạt động

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Thời gian tạo

    @Column(name = "oauth_provider")
    private String oauthProvider; // Nhà cung cấp OAuth (google, facebook)

    @Column(name = "oauth_id")
    private String oauthId; // ID OAuth

    @Column(name = "id_card_url")
    private String idCardUrl; // URL giấy tờ tùy thân

    @Column(name = "id_card_front_url")
    private String idCardFrontUrl; // URL mặt trước CCCD

    @Column(name = "id_card_back_url")
    private String idCardBackUrl; // URL mặt sau CCCD

    @OneToMany(mappedBy = "landlord")
    private List<Property> properties; // Danh sách nhà trọ do chủ trọ đăng

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings; // Danh sách đặt phòng

    @OneToMany(mappedBy = "user")
    private List<Review> reviews; // Danh sách đánh giá

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications; // Danh sách thông báo

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites; // Danh sách yêu thích

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages; // Tin nhắn gửi

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages; // Tin nhắn nhận

    @OneToMany(mappedBy = "reporter")
    private List<Report> reports; // Báo cáo gửi

    @OneToMany(mappedBy = "user")
    private List<Contract> contracts; // Hợp đồng thuê

    @OneToMany(mappedBy = "user")
    private List<Log> logs; // Nhật ký hoạt động

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks; // Phản hồi hệ thống
}