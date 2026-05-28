package ucr.ac.cr.StayPeriod.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Publication {
    @Id
    private Integer id;
    private String publicationDate;
    @ManyToOne  // ← AGREGAR ESTO
    @JoinColumn(name = "publisher_id")
    private User publisher;

    @ManyToOne  // ← AGREGAR ESTO
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne  // ← AGREGAR ESTO
    @JoinColumn(name = "request_id")
    private Request request;

    public Publication() {
    }

    public Publication(Integer id, String publicationDate, User publisher, Rental rental, Request request) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.rental = rental;
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
