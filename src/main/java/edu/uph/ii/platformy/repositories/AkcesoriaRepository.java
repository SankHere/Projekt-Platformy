package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Akcesoria;
import edu.uph.ii.platformy.models.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AkcesoriaRepository extends JpaRepository<Akcesoria, Long> {

    //Ubezpieczenie findByUbezpieczenie(Role.Types type);
//    @Query("select a from Akcesoria a where a.")
//    List<Akcesoria> getAkcesoriaByProdukts(@Param("produkt")Produkt produkt);
}
