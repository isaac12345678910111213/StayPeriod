package ucr.ac.cr.StayPeriod.model.DTO;

import java.time.LocalDate;

public class PublicationDTO {
    private Integer publication_id;
    private LocalDate publication_publicationDate;
    private Integer publication_publisher_id;
    private String publication_publisher_name;
    private Integer publication_rental_id;
    private String publication_rental_name;
    private Integer publication_request_id;

    public PublicationDTO() {
    }

    public PublicationDTO(Integer id, LocalDate publicationDate, Integer publisherId,
                          String publisherName, Integer rentalId, String rentalName,
                          Integer requestId) {
        this.publication_id = id;
        this.publication_publicationDate = publicationDate;
        this.publication_publisher_id = publisherId;
        this.publication_publisher_name = publisherName;
        this.publication_rental_id = rentalId;
        this.publication_rental_name = rentalName;
        this.publication_request_id = requestId;
    }

    public Integer getPublication_id() { return publication_id; }
    public void setPublication_id(Integer publication_id) { this.publication_id = publication_id; }

    public LocalDate getPublication_publicationDate() { return publication_publicationDate; }
    public void setPublication_publicationDate(LocalDate publication_publicationDate) {
        this.publication_publicationDate = publication_publicationDate;
    }

    public Integer getPublication_publisher_id() { return publication_publisher_id; }
    public void setPublication_publisher_id(Integer publication_publisher_id) {
        this.publication_publisher_id = publication_publisher_id;
    }

    public String getPublication_publisher_name() { return publication_publisher_name; }
    public void setPublication_publisher_name(String publication_publisher_name) {
        this.publication_publisher_name = publication_publisher_name;
    }

    public Integer getPublication_rental_id() { return publication_rental_id; }
    public void setPublication_rental_id(Integer publication_rental_id) {
        this.publication_rental_id = publication_rental_id;
    }

    public String getPublication_rental_name() { return publication_rental_name; }
    public void setPublication_rental_name(String publication_rental_name) {
        this.publication_rental_name = publication_rental_name;
    }

    public Integer getPublication_request_id() { return publication_request_id; }
    public void setPublication_request_id(Integer publication_request_id) {
        this.publication_request_id = publication_request_id;
    }
}