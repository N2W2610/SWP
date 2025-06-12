package model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Double price;

    private String address;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "property")
    private List<PropertyImage> images;

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "property")
    private List<Review> reviews;

    @OneToMany(mappedBy = "property")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "property")
    private List<PropertyCategory> categories;

    @OneToMany(mappedBy = "property")
    private List<PropertyAmenity> amenities;

    @OneToMany(mappedBy = "property")
    private List<Report> reports;

    @OneToMany(mappedBy = "property")
    private List<Contract> contracts;
}