package ucr.ac.cr.StayPeriod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.StayPeriod.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUserByRol(String rol);
    User findByEmail(String email);
    User findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email= :email AND u.password = :password")
    User verificarCredenciales(@Param("email") String email,
                               @Param("password") String password);
}