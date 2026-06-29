package ucr.ac.cr.StayPeriod.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rental_name", length = 150, nullable = false)
    private String name;

    @Column(name = "location", length = 255, nullable = false)
    private String location;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "details", length = 255)
    private String details;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public Rental() {
    }

    public Rental(Integer id, String name, String location, Integer capacity,
                  Double price, String details, User owner) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.price = price;
        this.details = details;
        this.owner = owner;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}
