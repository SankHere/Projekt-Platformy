package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;


@Controller
public class UserRegistrationFormController {
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userCommand", new User());
        return "registrationForm";
    }

    @PostMapping("/registrationForm.html")
    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        userForm.setDataRejestracji(new Date());

        userService.save(userForm);

        String wiadomosc = "http://localhost:8080/aktywujKonto.html?id=" + userForm.getId();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userForm.getEmail());
        message.setSubject("Rejestracja na stronie");
        message.setText("Aktywuj swoje konto tutaj: "+ wiadomosc);
        emailSender.send(message);

        return "registrationSuccess";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");
    }

}