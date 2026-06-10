package ucr.ac.cr.StayPeriod.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Rental {
    @Id
    private Integer id;
    private String name;
    private String location;
    private Integer capacity; //capacidad por personas
    private Double price;
    private String details; // detalles de la vivienda/departamento

    public Rental() {
    }

    public Rental(Integer id, String name, String location, Integer capacity, Double price, String details) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.price = price;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
