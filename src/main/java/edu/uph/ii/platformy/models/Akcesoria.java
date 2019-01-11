package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@Table(name = "akcesoria")
@Getter @Setter
@NoArgsConstructor
public class Akcesoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;

    @ManyToMany(mappedBy = "akcesorias")
    private Set<Produkt> produkts;

    public Akcesoria(long id, String name) {
        this(name);
        this.id = id;
    }

    public Akcesoria(String name) {
        this.name = name;
    }
}
