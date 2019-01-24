package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.repositories.AkcesoriaRepository;
import edu.uph.ii.platformy.repositories.KategoriaRepository;
import edu.uph.ii.platformy.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@SessionAttributes(names={"searchCommand"})
public class ProduktListFormController {

    @Autowired
    AkcesoriaRepository akcesoriaRepository;
    @Autowired
    ProduktRepository produktRepository;
    @Autowired
    KategoriaRepository kategoriaRepository;

    @RequestMapping(value="/produktListForm.html", method= RequestMethod.GET)
    public String showForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Long id){


        if(id > 0){
            Produkt o = produktRepository.findById(id).get();
            model.addAttribute("przedmiotNowy", o);

        }else{
            model.addAttribute("przedmiotNowy", new Produkt());
        }

        model.addAttribute("kategorie", kategoriaRepository.findAll());
        model.addAttribute("a", akcesoriaRepository.findAll());
        return "produktListForm";
    }

    @PostMapping(value = "/produktListForm.html")
    public String showProduktForm(Model model, @Valid @ModelAttribute("przedmiotNowy") Produkt produktForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("kategorie", kategoriaRepository.findAll());
            model.addAttribute("a", akcesoriaRepository.findAll());

            return "produktListForm";
        }

        produktRepository.save(produktForm);
        return "produktList.html";
    }

}
