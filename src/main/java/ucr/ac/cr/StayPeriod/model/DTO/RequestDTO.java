package ucr.ac.cr.StayPeriod.model.DTO;

import java.time.LocalDate;

public class RequestDTO {
    private Integer request_id;
    private LocalDate request_startDate;
    private LocalDate request_endDate;
    private Integer request_applicant_id;
    private String request_applicant_name;
    private Integer request_rental_id;
    private String request_rental_name;
    private String request_status;

    public RequestDTO() {}

    public RequestDTO(Integer id, LocalDate startDate, LocalDate endDate,
                      Integer applicantId, String applicantName,
                      Integer rentalId, String rentalName, String status) {
        this.request_id = id;
        this.request_startDate = startDate;
        this.request_endDate = endDate;
        this.request_applicant_id = applicantId;
        this.request_applicant_name = applicantName;
        this.request_rental_id = rentalId;
        this.request_rental_name = rentalName;
        this.request_status = status != null ? status : "PENDIENTE";
    }

    public Integer getRequest_id() { return request_id; }
    public void setRequest_id(Integer request_id) { this.request_id = request_id; }

    public LocalDate getRequest_startDate() { return request_startDate; }
    public void setRequest_startDate(LocalDate request_startDate) { this.request_startDate = request_startDate; }

    public LocalDate getRequest_endDate() { return request_endDate; }
    public void setRequest_endDate(LocalDate request_endDate) { this.request_endDate = request_endDate; }

    public Integer getRequest_applicant_id() { return request_applicant_id; }
    public void setRequest_applicant_id(Integer request_applicant_id) { this.request_applicant_id = request_applicant_id; }

    public String getRequest_applicant_name() { return request_applicant_name; }
    public void setRequest_applicant_name(String request_applicant_name) { this.request_applicant_name = request_applicant_name; }

    public Integer getRequest_rental_id() { return request_rental_id; }
    public void setRequest_rental_id(Integer request_rental_id) { this.request_rental_id = request_rental_id; }

    public String getRequest_rental_name() { return request_rental_name; }
    public void setRequest_rental_name(String request_rental_name) { this.request_rental_name = request_rental_name; }

    public String getRequest_status() { return request_status; }
    public void setRequest_status(String request_status) { this.request_status = request_status; }
}