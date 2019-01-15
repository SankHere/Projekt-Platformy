package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.ProduktFilter;

import edu.uph.ii.platformy.exceptions.ProduktNotFoundException;
import edu.uph.ii.platformy.models.Akcesoria;
import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.models.ProduktZamowienie;
import edu.uph.ii.platformy.repositories.AkcesoriaRepository;
import edu.uph.ii.platformy.repositories.KategoriaRepository;
import edu.uph.ii.platformy.repositories.ProduktRepository;
import edu.uph.ii.platformy.repositories.ProduktZamowienieRepository;
import edu.uph.ii.platformy.services.ProduktService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@SessionAttributes("searchCommand")
@Log4j2
public class ProduktListController {

    @Autowired
    private ProduktService produktService;
    @Autowired
    private KategoriaRepository kategoriaRepository;
    @Autowired
    private ProduktRepository produktRepository;
    @Autowired
    private AkcesoriaRepository akcesoriaRepository;
    @Autowired
    private ProduktZamowienieRepository produktZamowienieRepository;


    @Secured("IS_AUTHENTICATED_FULLY")
    @RequestMapping(value="/produktList.html", params = "id", method = RequestMethod.GET)
    public String showProduktDetails(Model model, Long id){
        log.info("Pokazywanie szczegółów");
        model.addAttribute("produkt", produktService.getProdukt(id));
        return "produktDetails";
    }

    @GetMapping(value="/error")
    public String resetProduktList(){
        return "redirect:produktList.html";
    }


    @ModelAttribute("searchCommand")
    public ProduktFilter getSimpleSearch(){
        return new ProduktFilter();
    }

    @GetMapping(value="/produktList.html", params = {"all"})
    public String resetProduktList(@ModelAttribute("searchCommand") ProduktFilter search){
        search.clear();
        return "redirect:produktList.html";
    }

    public List<Akcesoria> loadAkcesoria(){
        List<Akcesoria> akcesorias = akcesoriaRepository.findAll();
        return akcesorias;
    }

    @RequestMapping(value="/produktList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showProduktList(Model model, @Valid @ModelAttribute("searchCommand") ProduktFilter search, BindingResult result, Pageable pageable){

        model.addAttribute("produktListPage", produktService.getAllProdukts(search, pageable));

        model.addAttribute("kategoriaListPage", kategoriaRepository.findAll());
        return "produktList";
    }

    @GetMapping(value = "/produktList.html", params = "kat")
    public String showProduktListKategory(Model model, Pageable pageable, @RequestParam("kat") String kat){

        model.addAttribute("produktListPage", produktService.getAllProduktsKategory(kat, pageable));
        model.addAttribute("kategoriaListPage", kategoriaRepository.findAll());
        return "produktList";
    }


    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        CustomNumberEditor priceEditor = new CustomNumberEditor(Float.class, numberFormat, true);
        binder.registerCustomEditor(Float.class, "minPrice", priceEditor);
        binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);

    }

    @GetMapping(value = "/delete.html")
    public String deleteProdukt(Model model, @RequestParam(value = "id", required = false) Long id) {
        produktService.deleteProdukt(id);
        return "redirect:produktList.html";
    }
}




