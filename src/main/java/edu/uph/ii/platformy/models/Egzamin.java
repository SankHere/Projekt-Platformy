package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "egzamin")
@Getter @Setter
public class Egzamin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    @InvalidValues(ignoreCase = true, values = {"Napisz kod","Rózniczkowanie", "Probówka i ja"})
    private String name;


    public Egzamin(long id, String name) {
        this(name);
        this.id = id;
    }

    public Egzamin(String name) {
        this.name = name;
    }
}
