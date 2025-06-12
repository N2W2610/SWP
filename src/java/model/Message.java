package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Lớp Message ánh xạ bảng Messages, lưu tin nhắn giữa người dùng.
 */
@Data
@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã tin nhắn

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender; // Người gửi

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver; // Người nhận

    private String message; // Nội dung tin nhắn

    @Column(name = "sent_at")
    private LocalDateTime sentAt = LocalDateTime.now(); // Thời gian gửi
}