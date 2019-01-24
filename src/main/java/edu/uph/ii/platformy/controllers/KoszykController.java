package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.controllers.commands.ProduktyWKoszyku;
import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@Scope("session")
public class KoszykController {

    @Autowired
    private ProduktRepository produktRepository;

    Set<ProduktyWKoszyku> listaWybranychProduktów = new HashSet<>();

    double kosztcalosc;

    @RequestMapping(value = "/koszyk.html", method = RequestMethod.GET)
    public String mojKoszyk(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){

        if(id != -1){
            Produkt wybrany = produktRepository.findById(id).get();
            ProduktyWKoszyku produktyWKoszyku = new ProduktyWKoszyku(wybrany, 1);

            listaWybranychProduktów.add(produktyWKoszyku);

            for (ProduktyWKoszyku p : listaWybranychProduktów) {
                kosztcalosc = kosztcalosc + (p.getProdukt().getPrice() * p.getIlosc());
            }

            model.addAttribute("listaProduktow", listaWybranychProduktów);
            model.addAttribute("kosztcalosc", kosztcalosc);

            kosztcalosc = 0;

            return "koszyk";
        }else {
            for (ProduktyWKoszyku p : listaWybranychProduktów) {
                kosztcalosc = kosztcalosc + (p.getProdukt().getPrice() * p.getIlosc());
            }
            model.addAttribute("listaProduktow", listaWybranychProduktów);
            model.addAttribute("kosztcalosci", kosztcalosc);
            kosztcalosc = 0;
            return "koszyk";


        }
    }


    @PostMapping(value = "/koszyk.html")
    public String zmienIlosc(Model model, int id, int ilosc){

        for (ProduktyWKoszyku p : listaWybranychProduktów) {
            if(p.getProdukt().getId() == id){
                p.setIlosc(ilosc);
            }
        }
        return  "redirect:koszyk.html";
    }


}
