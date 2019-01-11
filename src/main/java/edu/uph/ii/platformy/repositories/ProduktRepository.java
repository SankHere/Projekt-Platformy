package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Produkt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProduktRepository extends JpaRepository<Produkt, Long>, JpaSpecificationExecutor<Produkt> {
    //nazwa metody jest jednocześnie zapytaniem
    Page<Produkt> findByNameContaining(String phrase, Pageable pageable);


    //nad klasą Vehicle znajduje się definicja zapytania (@NamedQuery) powiązana z tą metodą
    Page<Produkt> findAllProductsUsingNamedQuery(String phrase, Pageable pageable);

    //@Query("SELECT p FROM Produkt p WHERE (:phrase is null OR :phrase = '' OR upper(p.name) LIKE upper(:phrase) OR upper(p.opis) LIKE upper(:phrase)) AND (:cena is null OR :cena >= p.price)")
    @Query("SELECT p FROM Produkt p WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(p.name) LIKE upper(:phrase) OR " +
            "upper(p.opis) LIKE upper(:phrase) OR " +
            "upper(p.kategoria.name) LIKE upper(:phrase)" +
            ") " +
            "AND " +
            "(:cena is null OR :cena >= p.price) " )
           // "AND (:max is null OR :max >= v.price)")
    Page<Produkt> findAllProductUsingFilter(@Param("phrase") String p, @Param("cena") double cena, Pageable pageable);
}
