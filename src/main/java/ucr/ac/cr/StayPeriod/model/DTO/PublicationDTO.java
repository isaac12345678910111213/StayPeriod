package ucr.ac.cr.StayPeriod.model.DTO;

public class PublicationDTO {
    private Integer publication_id;
    private String publication_publicationDate;
    private Integer publication_user_id;
    private Integer publication_rental_id;
    private Integer publication_request_id;

    public PublicationDTO() {
    }

    public PublicationDTO(Integer publication_id, String publication_publicationDate, Integer publication_user_id, Integer publication_rental_id, Integer publication_request_id) {
        this.publication_id = publication_id;
        this.publication_publicationDate = publication_publicationDate;
        this.publication_user_id = publication_user_id;
        this.publication_rental_id = publication_rental_id;
        this.publication_request_id = publication_request_id;
    }

    public Integer getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(Integer publication_id) {
        this.publication_id = publication_id;
    }

    public String getPublication_publicationDate() {
        return publication_publicationDate;
    }

    public void setPublication_publicationDate(String publication_publicationDate) {
        this.publication_publicationDate = publication_publicationDate;
    }

    public Integer getPublication_user_id() {
        return publication_user_id;
    }

    public void setPublication_user_id(Integer publication_user_id) {
        this.publication_user_id = publication_user_id;
    }

    public Integer getPublication_rental_id() {
        return publication_rental_id;
    }

    public void setPublication_rental_id(Integer publication_rental_id) {
        this.publication_rental_id = publication_rental_id;
    }

    public Integer getPublication_request_id() {
        return publication_request_id;
    }

    public void setPublication_request_id(Integer publication_request_id) {
        this.publication_request_id = publication_request_id;
    }

    @Override
    public String toString() {
        return "Publication: " +
                "\n publication_id: " + publication_id +
                "\n publication_publicationDate: " + publication_publicationDate +
                "\n publication_user_id: " + publication_user_id +
                "\n publication_rental_id: " + publication_rental_id +
                "\n publication_request_id: " + publication_request_id;
    }
}
