package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.controllers.commands.ProduktyWKoszyku;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@Scope("session")
public class KoszykController {

    @Autowired
    private ProduktRepository produktRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ZamowienieRepository zamowienieRepository;
    @Autowired
    private ProduktZamowienieRepository produktZamowienieRepository;
    @Autowired
    private StatusRepository statusRepository;

    Set<ProduktyWKoszyku> listaWybranychProduktów = new HashSet<>();

    double kosztcalosc;
    @Secured("ROLE_USER")
    @RequestMapping(value = "/koszyk.html", method = RequestMethod.GET)
    public String mojKoszyk(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){

        if(id != -1) {
            Produkt wybrany = produktRepository.findById(id).get();
            ProduktyWKoszyku produktyWKoszyku = new ProduktyWKoszyku(wybrany, 1);

            listaWybranychProduktów.add(produktyWKoszyku);
        }
            for (ProduktyWKoszyku p : listaWybranychProduktów) {
                kosztcalosc = kosztcalosc + (p.getProdukt().getPrice() * p.getIlosc());
            }

            model.addAttribute("listaProduktow", listaWybranychProduktów);
            model.addAttribute("kosztcalosc", kosztcalosc);

            kosztcalosc = 0;

            return "koszyk";
        /*}
        else
            {
            for (ProduktyWKoszyku p : listaWybranychProduktów) {
                kosztcalosc = kosztcalosc + (p.getProdukt().getPrice() * p.getIlosc());
            }
            model.addAttribute("listaProduktow", listaWybranychProduktów);
            model.addAttribute("kosztcalosc", kosztcalosc);
            kosztcalosc = 0;
            return "koszyk";


        }*/
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "/koszyk.html")
    public String zmienIlosc(int id, int ilosc){

        if(ilosc >= 1){
            for (ProduktyWKoszyku p : listaWybranychProduktów) {
                if(p.getProdukt().getId() == id){
                    p.setIlosc(ilosc);
                }
            }
            return  "redirect:koszyk.html";
        }else{
            return "redirect:koszyk.html";
        }
    }
    @Secured("ROLE_USER")
    @RequestMapping(value = "/wyczyscKoszyk.html", method = RequestMethod.GET)
    public String wyczyscKoszyk() {
        listaWybranychProduktów.clear();
        return "redirect:koszyk.html";
    }
    @Secured("ROLE_USER")
    @RequestMapping(value = "/usunzKoszyka.html", method = RequestMethod.GET)
    public String usunzKoszyka(@RequestParam(name = "id") Long id) {

        for (ProduktyWKoszyku p : listaWybranychProduktów) {
            if(p.getProdukt().getId() == id){
                //p.setIlosc(1);
                listaWybranychProduktów.remove(p);
                break;
            }
        }
        return "redirect:koszyk.html";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/zlozZamowienie.html", method = RequestMethod.GET)
    public String zlozZamowienie(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);

        Status status = statusRepository.findByName("Oczekuje na akceptacje");

        for (ProduktyWKoszyku p : listaWybranychProduktów) {
            kosztcalosc = kosztcalosc + (p.getProdukt().getPrice() * p.getIlosc());
        }
        Zamowienie zamowienie = new Zamowienie(kosztcalosc, zalogowany, status, new Date());

        zamowienieRepository.save(zamowienie);

        for (ProduktyWKoszyku p : listaWybranychProduktów) {
            double cena = p.getIlosc() * p.getProdukt().getPrice();
            ProduktZamowienie przamowienie = new ProduktZamowienie(p.getIlosc(), cena, p.getProdukt(), zamowienie);
            produktZamowienieRepository.save(przamowienie);
        }

        listaWybranychProduktów.clear();
        kosztcalosc = 0;
        return  "redirect:mojeZamowienia.html";
    }
}
