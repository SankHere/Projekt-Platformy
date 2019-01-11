package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;


@Entity
@Table(name = "produkt_zamowienie")
@Getter @Setter
@NoArgsConstructor
public class ProduktZamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Positive
    @Max(1000000)
    private Double price;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produkt", nullable = false)
    private Produkt produkt;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_zamowienie", nullable = false)
    private Zamowienie zamowienie;

    public ProduktZamowienie(long id, double price, Produkt produkt, Zamowienie zamowienie) {
        this(price, produkt, zamowienie);
        this.id = id;
    }

    public ProduktZamowienie(double price, Produkt produkt, Zamowienie zamowienie) {
        this.price = price;
        this.produkt = produkt;
        this.zamowienie = zamowienie;
    }
}
