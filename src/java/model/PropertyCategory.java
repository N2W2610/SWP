package model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "PropertyCategories")
@IdClass(PropertyCategoryId.class)
public class PropertyCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

@Embeddable
class PropertyCategoryId implements java.io.Serializable {
    private Integer property;
    private Integer category;
}