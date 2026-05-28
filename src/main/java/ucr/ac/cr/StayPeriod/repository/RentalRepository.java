package ucr.ac.cr.StayPeriod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.StayPeriod.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
