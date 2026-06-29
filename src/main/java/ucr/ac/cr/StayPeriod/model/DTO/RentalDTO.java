package ucr.ac.cr.StayPeriod.model.DTO;

public class RentalDTO {
    private Integer rental_id;
    private String rental_name;
    private String rental_location;
    private Integer rental_capacity;
    private Double rental_price;
    private String rental_details;
    private Integer rental_owner_id;
    private String rental_owner_name;

    public RentalDTO() {
    }

    public RentalDTO(Integer id, String name, String location, Integer capacity,
                     Double price, String details, Integer ownerId, String ownerName) {
        this.rental_id = id;
        this.rental_name = name;
        this.rental_location = location;
        this.rental_capacity = capacity;
        this.rental_price = price;
        this.rental_details = details;
        this.rental_owner_id = ownerId;
        this.rental_owner_name = ownerName;
    }

    public Integer getRental_id() { return rental_id; }
    public void setRental_id(Integer rental_id) { this.rental_id = rental_id; }

    public String getRental_name() { return rental_name; }
    public void setRental_name(String rental_name) { this.rental_name = rental_name; }

    public String getRental_location() { return rental_location; }
    public void setRental_location(String rental_location) { this.rental_location = rental_location; }

    public Integer getRental_capacity() { return rental_capacity; }
    public void setRental_capacity(Integer rental_capacity) { this.rental_capacity = rental_capacity; }

    public Double getRental_price() { return rental_price; }
    public void setRental_price(Double rental_price) { this.rental_price = rental_price; }

    public String getRental_details() { return rental_details; }
    public void setRental_details(String rental_details) { this.rental_details = rental_details; }

    public Integer getRental_owner_id() { return rental_owner_id; }
    public void setRental_owner_id(Integer rental_owner_id) { this.rental_owner_id = rental_owner_id; }

    public String getRental_owner_name() { return rental_owner_name; }
    public void setRental_owner_name(String rental_owner_name) { this.rental_owner_name = rental_owner_name; }
}