package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByUsername(String username);
}
