package edu.uph.ii.platformy.models;
import edu.uph.ii.platformy.validators.annotations.InvalidValues;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "ubezpieczenie")
@Getter @Setter
public class Ubezpieczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    @InvalidValues(ignoreCase = true, values = {"PZU", "Warta", "Axa"})
    private String name;

    @Positive
    @Max(1000000)
    private Float price;

    public Ubezpieczenie(long id, String name, Float price) {
        this(name, price);
        this.id = id;
    }

    public Ubezpieczenie(String name, Float price) {
        this.name = name;
        this.price = price;

    }
}
