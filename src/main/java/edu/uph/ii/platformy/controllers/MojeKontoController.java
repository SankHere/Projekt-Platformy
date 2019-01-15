package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.ProduktFilter;
import edu.uph.ii.platformy.models.Akcesoria;
import edu.uph.ii.platformy.models.Produkt;
import edu.uph.ii.platformy.repositories.AkcesoriaRepository;
import edu.uph.ii.platformy.repositories.KategoriaRepository;
import edu.uph.ii.platformy.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
public class MojeKontoController {

    @Autowired
    AkcesoriaRepository akcesoriaRepository;
    @Autowired
    ProduktRepository produktRepository;

    @RequestMapping(value = "/mojeKonto.html", method = RequestMethod.GET)
    public String showAkcesoria(Model model, @RequestParam("id") Long id){

        return "mojeKonto";
    }
}