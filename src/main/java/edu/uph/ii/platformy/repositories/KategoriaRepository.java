package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kategoria;
import edu.uph.ii.platformy.models.Produkt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KategoriaRepository extends JpaRepository<Kategoria, Integer>, JpaSpecificationExecutor<Kategoria> {


        @Query("SELECT p FROM Produkt p WHERE " +
                "(" +
   //             ":phrase is null OR :phrase = '' OR "+
   //             "upper(p.name) LIKE upper(:phrase) OR " +
   //             "upper(p.opis) LIKE upper(:phrase) OR " +
                "upper(p.kategoria.name) LIKE upper(:phrase)" +
                ") " )//+
    //            "AND " +
   //             "(:cena is null OR :cena <= p.price) " )
                // "AND (:max is null OR :max >= v.price)")
        Page<Produkt> findAllKategoriaUsingFilter(@Param("phrase") String p, Pageable pageable);
}
