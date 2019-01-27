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

    private int iloscProduktu;

    private double price;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produkt", nullable = false)
    private Produkt produkt;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_zamowienie", nullable = false)
    private Zamowienie zamowienie;

    public ProduktZamowienie(long id, int iloscProduktu, double price, Produkt produkt, Zamowienie zamowienie) {
        this(iloscProduktu, price, produkt, zamowienie);
        this.id = id;
    }

    public ProduktZamowienie(int iloscProduktu, double price, Produkt produkt, Zamowienie zamowienie) {
        this.iloscProduktu = iloscProduktu;
        this.price=price;
        this.produkt = produkt;
        this.zamowienie = zamowienie;
    }
}
