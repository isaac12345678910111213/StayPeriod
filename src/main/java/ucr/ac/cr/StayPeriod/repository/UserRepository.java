package ucr.ac.cr.StayPeriod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.StayPeriod.model.User;
@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    User findUserByRol (String rol);
}
