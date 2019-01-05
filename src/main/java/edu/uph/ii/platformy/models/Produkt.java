package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;



@Entity
@Table(name = "produkt")
@Getter @Setter
public class Produkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;

    @Positive
    @Max(1000000)
    private Double price;

    @Positive
    @Max(1000000)
    private int ilosc;

    ///ewentualne zdjecie ?>>?>??>

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kategorii", nullable = false)
    private Kategoria kategoria;


    public Produkt(long id, String name, int ilosc, double price, Kategoria kategoria) {
        this(name, ilosc, price, kategoria);
        this.id = id;
    }

    public Produkt(String name, int ilosc, double price, Kategoria kategoria) {
        this.name = name;
        this.ilosc = ilosc;
        this.price = price;
        this.kategoria = kategoria;
    }
}
