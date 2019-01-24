package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AkcesoriaRepository akcesoriaRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;
    @Autowired
    private ProduktRepository produktRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ZamowienieRepository zamowienieRepository;
    @Autowired
    private ProduktZamowienieRepository produktZamowienie;

    @Bean
    InitializingBean init() {

        return () -> {

            if(roleRepository.findAll().isEmpty()==true){
                try {

                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));


                    User user = new User("user", true, "imieUser", "nazUzer", "emailUser", new Date());
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", true, "imieAdmin", "nazAdmin", "emailAdmin", new Date());
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User test = new User("test", true, "imieTest", "nazTest", "emailTest", new Date());
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                    test.setPassword(passwordEncoder.encode("test"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);

                    //tworzymy statusy
                    Status st1 = new Status("Odrzucono");
                    statusRepository.save(st1);
                    Status st2 = new Status("Oczekuje na akceptacje");
                    statusRepository.save(st2);
                    Status st3 = new Status("Zrealizowano");
                    statusRepository.save(st3);

                    //tworzymy kategorie
                    Kategoria Ka1 = new Kategoria("Zwierzęta");
                    kategoriaRepository.save(Ka1);
                    Kategoria Ka2 = new Kategoria("Pokarm");
                    kategoriaRepository.save(Ka2);
                    Kategoria Ka3 = new Kategoria("Akcesoria");
                    kategoriaRepository.save(Ka3);

                    //tworzymy akcesoria
                    Akcesoria Ak1 = new Akcesoria("Karma na miesiac czasu");
                    akcesoriaRepository.save(Ak1);
                    Akcesoria Ak2 = new Akcesoria("Legowisko");
                    akcesoriaRepository.save(Ak2);
                    Akcesoria Ak3 = new Akcesoria("Specjalna zabawka");
                    akcesoriaRepository.save(Ak3);
                    Akcesoria Ak4 = new Akcesoria("Obroża");
                    akcesoriaRepository.save(Ak4);
                    Akcesoria Ak5 = new Akcesoria("Brak");
                    akcesoriaRepository.save(Ak5);


                    //tworzymy produkty
                    Produkt Pr1 = new Produkt("Owczarek niemiecki", 1, 1200.00,
                            "Owczarek niemiecki jest typem sportowca i niełatwo dotrzymać mu kroku. Poradzi " +
                                    "sobie niemal w każdej dziedzinie pracy i sportu, jego właściciel powinien " +
                                    "więc być energiczny i chętny do współdziałania. Pies tej rasy jest " +
                                    "oddany właścicielowi i lojalny wobec wszystkich członków rodziny."
                            ,Ka1);
                    Pr1.setAkcesorias(new HashSet<>(Arrays.asList(Ak2, Ak3)));
                    produktRepository.save(Pr1);

                    Produkt Pr2 = new Produkt("Beagle", 1, 800.0,
                            "Beagle'e robią furorę jako psy rodzinne, choć nie zostały stworzone do " +
                                    "tej roli. Ich urok sprawia jednak, że właściciele wiele im wybaczają - " +
                                    "czasem zbyt wiele... Beagle nie lubi samotności, pozostawiony sam na " +
                                    "długie godziny może psocić lub wyciem uprzykrzać życie sąsiadom."
                            , Ka1);
                    Pr2.setAkcesorias(new HashSet<>(Arrays.asList(Ak1, Ak4)));
                    produktRepository.save(Pr2);

                    Produkt Pr3 = new Produkt("Golden Retriver", 1, 950.0,
                            "Golden retriever może się nauczyć praktycznie wszystkiego, bo nie ma dla " +
                                    "niego większego szczęścia niż nowe zadanie od ukochanego pana. Trzeba tylko" +
                                    " pamiętać, by nie popsuć sobie z nim stosunków brakiem delikatności."
                            ,Ka1);
                    Pr3.setAkcesorias(new HashSet<>(Arrays.asList(Ak1)));
                    produktRepository.save(Pr3);

                    Produkt Pr4 = new Produkt("Karma - Zdrowy pies", 45, 13.50,
                            "Najlepsza i najzdrowasza karma dla twojego psa", Ka2);
                    Pr4.setAkcesorias(new HashSet<>(Arrays.asList(Ak5)));
                    produktRepository.save(Pr4);

                    Produkt Pr5 = new Produkt("Karma - Zdrowy kot", 25, 11.50,
                            "Najlepsza i najzdrowasza karma dla twojego kota", Ka2);
                    Pr5.setAkcesorias(new HashSet<>(Arrays.asList(Ak5)));
                    produktRepository.save(Pr5);

                    Produkt Pr6 = new Produkt("Legowisko", 10, 90.00,
                            "Legowisko dla twojego psa", Ka3);
                    Pr6.setAkcesorias(new HashSet<>(Arrays.asList(Ak5)));
                    produktRepository.save(Pr6);

                    Produkt Pr7 = new Produkt("Obroża", 15, 20.00,
                            "Obroża dla twojego psa", Ka3);
                    Pr7.setAkcesorias(new HashSet<>(Arrays.asList(Ak5)));
                    produktRepository.save(Pr7);

                    Produkt Pr8 = new Produkt("Smycz", 20, 15.00,
                            "Smycz dla twojego psa", Ka3);
                    Pr8.setAkcesorias(new HashSet<>(Arrays.asList(Ak5)));
                    produktRepository.save(Pr8);


                    //Tworzymy zamówienie
                    Zamowienie za1 = new Zamowienie(user, st1);
                    zamowienieRepository.save(za1);

                    Zamowienie za2 = new Zamowienie(user, st2);
                    zamowienieRepository.save(za2);

                    Zamowienie za3 = new Zamowienie(user, st3);
                    zamowienieRepository.save(za3);


                    //Tworzymy ProduktZamowienie
                    ProduktZamowienie prza1 = new ProduktZamowienie(1 ,Pr1, za1);
                    produktZamowienie.save(prza1);
                    ProduktZamowienie prza2 = new ProduktZamowienie(2, Pr4, za1);
                    produktZamowienie.save(prza2);
                    ProduktZamowienie prza3 = new ProduktZamowienie(1, Pr2, za2);
                    produktZamowienie.save(prza3);
                    ProduktZamowienie prza4 = new ProduktZamowienie( 1, Pr1, za2);
                    produktZamowienie.save(prza4);
                    ProduktZamowienie prza5 = new ProduktZamowienie( 1, Pr3, za3);
                    produktZamowienie.save(prza5);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
    }

}
