package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp SystemSettings ánh xạ bảng SystemSettings, lưu cài đặt hệ thống.
 */
@Data
@Entity
@Table(name = "SystemSettings")
public class SystemSettings {
    @Id
    @Column(name = "key")
    private String key; // Khóa cài đặt

    @Column(name = "value")
    private String value; // Giá trị cài đặt
}