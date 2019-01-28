package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.repositories.AkcesoriaRepository;
import edu.uph.ii.platformy.repositories.KategoriaRepository;
import edu.uph.ii.platformy.repositories.ProduktRepository;
import edu.uph.ii.platformy.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Files;
import java.util.Random;

@Controller
@SessionAttributes(names={"searchCommand"})
public class ProduktListFormController {

    private final AkcesoriaRepository akcesoriaRepository;
    private final ProduktRepository produktRepository;
    private final KategoriaRepository kategoriaRepository;
    private final StorageService storageService;

    @Autowired
    public ProduktListFormController(AkcesoriaRepository akcesoriaRepository, ProduktRepository produktRepository, KategoriaRepository kategoriaRepository, StorageService storageService) {
        this.akcesoriaRepository = akcesoriaRepository;
        this.produktRepository = produktRepository;
        this.kategoriaRepository = kategoriaRepository;
        this.storageService = storageService;
    }

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

        String[] array = StringUtils.cleanPath(produktForm.getFile().getOriginalFilename()).split("\\.");

        if(array.length < 2) return "redirect:/";

        String externsion = array[array.length - 1];

        String t = produktForm.getName().split(" ")[0];

        Random rand = new Random();
        String name;

        do {
            String r = String.valueOf(rand.nextInt(99999999));
            name = t + "_" + r +"."+ externsion;
        }while(storageService.exist(name));

        storageService.store(produktForm.getFile(), name);
        produktForm.setZdjecie(name);
        produktForm.setFile(null);
        produktRepository.save(produktForm);
        return "redirect:produktList.html";

    }

}
