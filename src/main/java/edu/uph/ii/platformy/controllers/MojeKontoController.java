package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
public class MojeKontoController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/mojeKonto.html", method = RequestMethod.GET)
    public String mojeKonto(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User zalogowany = userRepository.findByUsername(currentPrincipalName);

        model.addAttribute("zalogowany", zalogowany);
        return "mojeKonto";
    }
}