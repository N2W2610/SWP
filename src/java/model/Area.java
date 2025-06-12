package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp Area ánh xạ bảng Areas, lưu thông tin khu vực.
 */
@Data
@Entity
@Table(name = "Areas")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mã khu vực

    private String name; // Tên khu vực
}