package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;


@Entity
@Table(name = "produkt")
@NamedQuery(name = "Produkt.findAllProductsUsingNamedQuery",
        query = ("SELECT p FROM Produkt p WHERE upper(p.name) LIKE upper(:phrase) or upper(p.opis) LIKE upper(:phrase)"))
@Getter @Setter
@NoArgsConstructor
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

    @Length(min = 5, max = 1000)
    private String opis;


    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kategorii", nullable = false)
    private Kategoria kategoria;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "produkt_akcesoria",
            joinColumns = @JoinColumn(name = "produkt_id"),
            inverseJoinColumns = @JoinColumn(name = "akcesoria_id"))
    private Set<Akcesoria> akcesorias;


    public Produkt(long id, String name, double price, String opis, Kategoria kategoria) {
        this(name,price, opis, kategoria);
        this.id = id;
    }

    public Produkt(String name, double price, String opis, Kategoria kategoria) {
        this.name = name;
        this.price = price;
        this.opis = opis;
        this.kategoria = kategoria;
    }
}
