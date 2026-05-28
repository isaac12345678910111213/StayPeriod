package ucr.ac.cr.StayPeriod.model.DTO;

public class RentalDTO {
    private Integer rental_id;
    private String rental_location;
    private Integer rental_capacity;
    private Double rental_price;

    public RentalDTO() {
    }

    public RentalDTO(Integer rental_id, String rental_location, Integer rental_capacity, Double rental_price) {
        this.rental_id = rental_id;
        this.rental_location = rental_location;
        this.rental_capacity = rental_capacity;
        this.rental_price = rental_price;
    }

    public Integer getRental_id() {
        return rental_id;
    }

    public void setRental_id(Integer rental_id) {
        this.rental_id = rental_id;
    }

    public String getRental_location() {
        return rental_location;
    }

    public void setRental_location(String rental_location) {
        this.rental_location = rental_location;
    }

    public Integer getRental_capacity() {
        return rental_capacity;
    }

    public void setRental_capacity(Integer rental_capacity) {
        this.rental_capacity = rental_capacity;
    }

    public Double getRental_price() {
        return rental_price;
    }

    public void setRental_price(Double rental_price) {
        this.rental_price = rental_price;
    }

    @Override
    public String toString() {
        return "Rental: " +
                "\n rental_id: " + rental_id +
                "\n rental_location: " + rental_location +
                "\n rental_capacity: " + rental_capacity +
                "\n rental_price: " + rental_price;
    }
}
