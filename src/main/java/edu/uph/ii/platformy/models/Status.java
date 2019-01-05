package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "status")
@Getter @Setter
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    private String name;


    public Status(long id, String name) {
        this(name);
        this.id = id;
    }

    public Status(String name) {
        this.name = name;
    }
}
