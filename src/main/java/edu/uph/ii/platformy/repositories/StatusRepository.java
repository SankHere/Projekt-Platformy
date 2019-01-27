package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByName(String name);
}
