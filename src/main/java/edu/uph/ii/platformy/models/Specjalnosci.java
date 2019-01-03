package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "specjalnosci")
@Getter @Setter
public class Specjalnosci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    @InvalidValues(ignoreCase = true, values = {"BazyDanych", "Sieci", "Chemia kwantowa"})
    private String name;


    public Specjalnosci(long id, String name ) {
        this(name);
        this.id = id;
    }

    public Specjalnosci(String name) {
        this.name = name;
    }
}