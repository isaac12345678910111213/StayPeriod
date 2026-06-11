package ucr.ac.cr.StayPeriod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.StayPeriod.model.Publication;
import ucr.ac.cr.StayPeriod.model.User;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
    Publication findPublicationByPublicationDate(String publicationDate);
}
