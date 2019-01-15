package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {

    @Query("select z from Zamowienie z where :id = z.user.id")
    Zamowienie findAllByUserId(@Param("id") long id);
}
