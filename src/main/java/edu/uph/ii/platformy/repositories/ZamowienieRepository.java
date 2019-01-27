package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.models.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {

    List<Zamowienie> findAllByUser(User user);
}
