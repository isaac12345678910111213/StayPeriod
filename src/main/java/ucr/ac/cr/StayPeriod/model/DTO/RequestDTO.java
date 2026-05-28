package ucr.ac.cr.StayPeriod.model.DTO;

public class RequestDTO {
    private Integer request_id;
    private String request_startDate;
    private String request_endDate;
    private Integer request_user_id;
    private Integer request_rental_id;

    public RequestDTO() {
    }

    public RequestDTO(Integer request_id, String request_startDate, String request_endDate, Integer request_user_id, Integer request_rental_id) {
        this.request_id = request_id;
        this.request_startDate = request_startDate;
        this.request_endDate = request_endDate;
        this.request_user_id = request_user_id;
        this.request_rental_id = request_rental_id;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getRequest_startDate() {
        return request_startDate;
    }

    public void setRequest_startDate(String request_startDate) {
        this.request_startDate = request_startDate;
    }

    public String getRequest_endDate() {
        return request_endDate;
    }

    public void setRequest_endDate(String request_endDate) {
        this.request_endDate = request_endDate;
    }

    public Integer getRequest_user_id() {
        return request_user_id;
    }

    public void setRequest_user_id(Integer request_user_id) {
        this.request_user_id = request_user_id;
    }

    public Integer getRequest_rental_id() {
        return request_rental_id;
    }

    public void setRequest_rental_id(Integer request_rental_id) {
        this.request_rental_id = request_rental_id;
    }

    @Override
    public String toString() {
        return "Request: " +
                "\n request_id: " + request_id +
                "\n request_startDate: " + request_startDate +
                "\n request_endDate: " + request_endDate +
                "\n request_user_id: " + request_user_id +
                "\n request_rental_id: " + request_rental_id;
    }
}
