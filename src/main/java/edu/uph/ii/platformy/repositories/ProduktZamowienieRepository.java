package edu.uph.ii.platformy.repositories;


import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.models.ProduktZamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProduktZamowienieRepository extends JpaRepository<ProduktZamowienie, Integer> {

    Set<ProduktZamowienie> findAllByProdukt(Produkt produkt);
}
