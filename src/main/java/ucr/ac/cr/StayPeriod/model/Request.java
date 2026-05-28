package ucr.ac.cr.StayPeriod.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Request {
    @Id
    private Integer id;
    private String startDate;
    private String endDate;

    @ManyToOne /*establecemos una relación de muchos a uno*/
    @JoinColumn(name = "applicant_id") /*cambiamos el nombre que se muestra en la tabla*/
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    public Request() {
    }

    public Request(Integer id, String startDate, String endDate, User applicant, Rental rental) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicant = applicant;
        this.rental = rental;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
