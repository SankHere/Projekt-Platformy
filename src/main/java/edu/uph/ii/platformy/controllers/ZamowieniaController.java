package edu.uph.ii.platformy.controllers;



import edu.uph.ii.platformy.repositories.ZamowienieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ZamowieniaController {

    @Autowired
    private ZamowienieRepository zamowienieRepository;

    @RequestMapping(value="/mojeZamowienia.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showMojeZamowienia(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("zamowienia", zamowienieRepository.findAll());
        return "mojeZamowienia";
    }

}
