package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.ProduktZamowienieRepository;
import edu.uph.ii.platformy.repositories.StatusRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.repositories.ZamowienieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller
public class ZamowieniaController {

    @Autowired
    private ZamowienieRepository zamowienieRepository;
    @Autowired
    private ProduktZamowienieRepository produktZamowienieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value="/mojeZamowienia.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showMojeZamowienia(Model model, SecurityContextHolderAwareRequestWrapper requestWrapper){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);

        //Role rola = zalogowany.getRoles().;
        //if(requestWrapper.isUserInRole(Role.Types.ROLE_ADMIN.toString())){
        if(requestWrapper.isUserInRole(Role.Types.ROLE_ADMIN.toString())){
            List<Zamowienie> zamowienie = zamowienieRepository.findAll();
            model.addAttribute("zamowienie", zamowienie);
        }else {
            List<Zamowienie> zamowienie = zamowienieRepository.findAllByUser(zalogowany);
            model.addAttribute("zamowienie", zamowienie);
        }

        return "mojeZamowienia";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value="/szczegolyZamowienia.html", method = RequestMethod.GET)
    public String showSzczegolyZamowienia(Model model, @RequestParam("id") Long id){

        Zamowienie zamowienie = zamowienieRepository.findById(id).get();

        List<ProduktZamowienie> przamowienia = produktZamowienieRepository.findAllByZamowienie(zamowienie);

        model.addAttribute("przedmiotZamowienie", przamowienia);

        return "szczegolyZamowienia";

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/akceptacjaZamowienia.html", method = RequestMethod.GET)
    public String akceptujZamowienie(@RequestParam(name = "id", defaultValue = "-1", required = false) Long id){

        if(id != -1){
            Status status = statusRepository.findByName("Zrealizowano");
            Zamowienie zamowienie = zamowienieRepository.findById(id).get();
            zamowienie.setStatus(status);
            zamowienieRepository.save(zamowienie);
            return "redirect:mojeZamowienia.html";
        }else{
            return "redirect:mojeZamowienia.html";
        }

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/odrzucenieZamowienia.html", method = RequestMethod.GET)
    public String odrzućZamówienie(@RequestParam(name = "id", defaultValue = "-1", required = false) Long id){

        if(id != -1){
            Status status = statusRepository.findByName("Odrzucono");
            Zamowienie zamowienie = zamowienieRepository.findById(id).get();
            zamowienie.setStatus(status);
            zamowienieRepository.save(zamowienie);
            return "redirect:mojeZamowienia.html";
        }else{
            return "redirect:mojeZamowienia.html";
        }

    }
}
