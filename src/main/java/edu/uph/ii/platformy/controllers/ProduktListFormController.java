package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.exceptions.ProduktNotFoundException;
import edu.uph.ii.platformy.models.Akcesoria;
import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AkcesoriaRepository;
import edu.uph.ii.platformy.repositories.KategoriaRepository;
import edu.uph.ii.platformy.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Controller
public class ProduktListFormController {

    @Autowired
    AkcesoriaRepository akcesoriaRepository;
    @Autowired
    ProduktRepository produktRepository;
    @Autowired
    KategoriaRepository kategoriaRepository;

//    @RequestMapping(value = "/produktListForm.html", method = {RequestMethod.GET, RequestMethod.POST})
//    public String showProductForm(Model model, @RequestParam(value = "id", required = false) Optional<Long> id) {
//        if (id.isPresent())
//        {
//            Produkt przedmiotNowy = produktRepository.findById(id.get()).get();
//            model.addAttribute("przedmiotNowy", przedmiotNowy);
//         }else{
////            produktRepository.saveAndFlush(produktForm);
//              return  "produktListForm";
////            Akcesoria akcesoria = akcesoriaRepository.find
////            Role userRole = roleRepository.findRoleByType(Role.Types.ROLE_USER);
////            List roles = Arrays.asList(userRole);
////            user.setRoles(new HashSet<>(roles));
////            user.setPassword(passwordEncoder.encode(user.getPassword()));
////            user.setPasswordConfirm(null);//wyzerowanie jest potrzebne ze względu na walidację
////            userRepository.saveAndFlush(user);
//        }
//        model.addAttribute("kategorie", kategoriaRepository.findAll());
//        model.addAttribute("a", akcesoriaRepository.findAll());
//        return "produktListForm";
//    }
//    @GetMapping(value = "/produktListForm.html")
//    public String showProduktForm(Model model, @RequestParam(value = "id", required = false) Optional<Long>id){
//
//        Optional<Produkt> o = produktRepository.findById(id.get());
//
//        if(!o.isPresent()){
//            return "error";
//        }
//
//        Produkt przedmiotNowy = o.get();
//        model.addAttribute("przedmiotNowy", przedmiotNowy);
//        model.addAttribute("kategorie", kategoriaRepository.findAll());
//        model.addAttribute("a", akcesoriaRepository.findAll());
//        return  "produktListForm";
//
//
//
//    }

    @RequestMapping(value="/produktListForm.html", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("przedmiotNowy",
                id.isPresent()?
                        produktRepository.findById(id.get()):
                        new Produkt());

        model.addAttribute("kategorie", kategoriaRepository.findAll());
        model.addAttribute("a", akcesoriaRepository.findAll());
        return "produktListForm";
    }

    @PostMapping(value = "/produktListForm.html")
    public String showProduktForm(Model model, @Valid @ModelAttribute("przedmiotNowy") Produkt produktForm, BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            produktRepository.saveAndFlush(produktForm);
            return "produktList";
        }
            model.addAttribute("kategorie", kategoriaRepository.findAll());
            model.addAttribute("a", akcesoriaRepository.findAll());

        return  "produktListForm";
    }

}
