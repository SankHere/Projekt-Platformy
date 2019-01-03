package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.Ubezpieczenie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbezpieczenieRepository extends JpaRepository<Role, Integer> {

    //Ubezpieczenie findByUbezpieczenie(Role.Types type);
}
