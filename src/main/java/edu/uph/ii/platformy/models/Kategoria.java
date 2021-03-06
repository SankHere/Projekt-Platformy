package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "kategoria")
@Getter @Setter
@NoArgsConstructor
public class Kategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Length(min = 2, max = 30)
    private String name;


    public Kategoria(long id, String name) {
        this(name);
        this.id = id;
    }

    public Kategoria(String name) {
        this.name = name;
    }
}
