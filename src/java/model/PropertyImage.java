package model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "PropertyImages")
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "image_url")
    private String imageUrl;
}