package model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    private Integer id;

    private String name;
}