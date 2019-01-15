package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Kategoria;
import edu.uph.ii.platformy.models.Produkt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KategoriaRepository extends JpaRepository<Kategoria, Long>, JpaSpecificationExecutor<Kategoria> {


}
