package model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Favorites")
@IdClass(FavoriteId.class)
public class Favorite {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}

@Embeddable
class FavoriteId implements java.io.Serializable {
    private Integer user;
    private Integer property;
}