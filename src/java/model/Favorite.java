package model;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Lớp Favorite ánh xạ bảng Favorites, lưu nhà trọ yêu thích của người dùng.
 */
@Data
@Entity
@Table(name = "Favorites")
@IdClass(FavoriteId.class)
public class Favorite {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người dùng

    @Id
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Nhà trọ
}

/**
 * Lớp FavoriteId định nghĩa khóa chính tổng hợp.
 */
@Embeddable
class FavoriteId implements java.io.Serializable {
    private Integer user; // Mã người dùng
    private Integer property; // Mã nhà trọ
}