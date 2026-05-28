package ucr.ac.cr.StayPeriod.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Request {
    @Id
    private Integer id;
    private String startDate;
    private String endDate;
    private User applicant;
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
