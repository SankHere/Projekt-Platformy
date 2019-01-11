package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.ProduktFilter;
import edu.uph.ii.platformy.models.Akcesoria;
import edu.uph.ii.platformy.models.Produkt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProduktService {

    List<Akcesoria> getAllAccessories();

    Page<Produkt> getAllProdukts(ProduktFilter filter, Pageable pageable);

    Produkt getProdukt(Long id);

    void deleteProdukt(Long id);

    void saveProdukt(Produkt produkt);
}
