package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;


@Entity
@Table(name = "produktKupiony")
@Getter @Setter
public class ProduktKupiony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produktu", nullable = false)
    private Produkt produkt;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usera", nullable = false)
    private User user;

    public ProduktKupiony(long id, Produkt produkt, User user) {
        this(produkt, user);
        this.id = id;
    }

    public ProduktKupiony(Produkt produkt, User user) {
        this.produkt = produkt;
        this.user = user;
    }
}
