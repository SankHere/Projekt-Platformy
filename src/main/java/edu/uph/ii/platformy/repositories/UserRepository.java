package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    User findUserByusername(String name);
}
