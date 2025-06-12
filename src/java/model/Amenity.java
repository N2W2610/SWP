package model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Amenities")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}