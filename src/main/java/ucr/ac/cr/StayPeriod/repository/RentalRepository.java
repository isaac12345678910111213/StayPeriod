package ucr.ac.cr.StayPeriod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.StayPeriod.model.Rental;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByNameContainingIgnoreCase(String name);
    List<Rental> findByLocationContainingIgnoreCase(String location);
    List<Rental> findByPriceLessThanEqual(Double price);
    List<Rental> findByCapacityGreaterThanEqual(Integer capacity);
    List<Rental> findByOwnerId(Integer ownerId);
    Rental findByNameAndLocation(String name, String location);
}