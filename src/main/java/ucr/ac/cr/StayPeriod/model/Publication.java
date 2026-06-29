package ucr.ac.cr.StayPeriod.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private User publisher;

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    public Publication() {
    }

    public Publication(Integer id, LocalDate publicationDate, User publisher, Rental rental, Request request) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.rental = rental;
        this.request = request;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getPublicationDate() { return publicationDate; }
    public void setPublicationDate(LocalDate publicationDate) { this.publicationDate = publicationDate; }

    public User getPublisher() { return publisher; }
    public void setPublisher(User publisher) { this.publisher = publisher; }

    public Rental getRental() { return rental; }
    public void setRental(Rental rental) { this.rental = rental; }

    public Request getRequest() { return request; }
    public void setRequest(Request request) { this.request = request; }
}