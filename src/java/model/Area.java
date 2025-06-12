package model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Areas")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}