package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;
import javafx.css.StyleableObjectProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "stypendia")
@Getter @Setter
public class Stypendia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    @InvalidValues(ignoreCase = true, values = {"Male", "Srednie", "Duze"})
    private String name;

    @Positive
    @Max(1000000)
    private Float kwota;

    public Stypendia(long id, String name, Float kwota) {
        this(name, kwota);
        this.id = id;
    }

    public Stypendia(String name, Float kwota) {
        this.name = name;
        this.kwota = kwota;

    }
}