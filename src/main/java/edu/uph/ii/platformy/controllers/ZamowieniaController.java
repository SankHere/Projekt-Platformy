package edu.uph.ii.platformy.controllers;



import edu.uph.ii.platformy.models.ProduktZamowienie;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.models.Zamowienie;
import edu.uph.ii.platformy.repositories.ProduktZamowienieRepository;
import edu.uph.ii.platformy.repositories.UserRepository;
import edu.uph.ii.platformy.repositories.ZamowienieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;
import java.util.Set;


@Controller
public class ZamowieniaController {

    @Autowired
    private ZamowienieRepository zamowienieRepository;
    @Autowired
    private ProduktZamowienieRepository produktZamowienieRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/mojeZamowienia.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showMojeZamowienia(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);


        model.addAttribute("zalogowany", zalogowany);
       // model.addAttribute("zamowienia", zam);
        return "mojeZamowienia";

    }

}
