package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.ProduktFilter;
import edu.uph.ii.platformy.exceptions.ProduktNotFoundException;
import edu.uph.ii.platformy.models.Akcesoria;
import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.models.ProduktZamowienie;
import edu.uph.ii.platformy.repositories.AkcesoriaRepository;
import edu.uph.ii.platformy.repositories.ProduktRepository;
import edu.uph.ii.platformy.repositories.ProduktZamowienieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProduktServiceImpl implements ProduktService {

    @Autowired
    private AkcesoriaRepository akcesoriaRepository;

    @Autowired
    private ProduktRepository produktRepository;

    @Autowired
    private ProduktZamowienieRepository produktZamowienieRepository;



    @Override
    public List<Akcesoria> getAllAccessories() {
        return akcesoriaRepository.findAll();
    }


    @Override
    public Page<Produkt> getAllProdukts(ProduktFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = produktRepository.findAll(pageable);
        }else{
            page = produktRepository.findAllProductUsingFilter(search.getPhraseLIKE(), search.getCena(), pageable);
        }

        return page;

    }

    @Override
    public Page<Produkt> getAllProduktsKategory(String kat, Pageable pageable) {
        Page page;

        page = produktRepository.findAllProductUsingKategory(kat, pageable);

        return page;

    }


    @Transactional
    @Override
    public Produkt getProdukt(Long id) {
        Optional<Produkt> optionalProdukt = produktRepository.findById(id);
        Produkt produkt = optionalProdukt.orElseThrow(()->new ProduktNotFoundException(id));
        produkt.getAkcesorias().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return produkt;
    }

    @Override
    public void deleteProdukt(Long id) {
        // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(produktRepository.existsById(id)){
            Set<ProduktZamowienie> przam = produktZamowienieRepository.findAllByProdukt(produktRepository.findById(id).get());
            for(ProduktZamowienie p : przam)
                produktZamowienieRepository.delete(p);
            produktRepository.deleteById(id);
        }else{
            throw new ProduktNotFoundException(id);
        }
    }

    @Override
    public void saveProdukt(Produkt produkt) {
        produktRepository.save(produkt);
    }
}
