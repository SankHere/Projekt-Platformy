package edu.uph.ii.platformy.repositories;


import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.models.ProduktZamowienie;
import edu.uph.ii.platformy.models.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProduktZamowienieRepository extends JpaRepository<ProduktZamowienie, Long> {

    Set<ProduktZamowienie> findAllByProdukt(Produkt produkt);

    //List<ProduktZamowienie> findByZamowienie(Zamowienie zamowienie);
    //List<ProduktZamowienie> findAllByZamowienie(List<Zamowienie> zamowienie);
    List<ProduktZamowienie> findAllByZamowienie(Zamowienie zamowienie);
}
