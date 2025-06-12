package model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "PropertyAmenities")
@IdClass(PropertyAmenityId.class)
public class PropertyAmenity {
    @Id
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Id
    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;
}

@Embeddable
class PropertyAmenityId implements java.io.Serializable {
    private Integer property;
    private Integer amenity;
}