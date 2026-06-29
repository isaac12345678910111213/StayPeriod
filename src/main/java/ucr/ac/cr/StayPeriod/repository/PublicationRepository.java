package ucr.ac.cr.StayPeriod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.StayPeriod.model.Publication;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
    List<Publication> findByPublicationDate(LocalDate date);
    List<Publication> findByPublisherId(Integer publisherId);
    List<Publication> findByRentalId(Integer rentalId);
    List<Publication> findByPublicationDateBetween(LocalDate start, LocalDate end);
}