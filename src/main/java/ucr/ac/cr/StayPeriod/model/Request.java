package ucr.ac.cr.StayPeriod.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @Column(name = "status", length = 20)
    private String status;

    public Request() {
        this.status = "PENDIENTE";
    }

    public Request(Integer id, LocalDate startDate, LocalDate endDate, User applicant, Rental rental) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicant = applicant;
        this.rental = rental;
        this.status = "PENDIENTE";
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public User getApplicant() { return applicant; }
    public void setApplicant(User applicant) { this.applicant = applicant; }
    public Rental getRental() { return rental; }
    public void setRental(Rental rental) { this.rental = rental; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}