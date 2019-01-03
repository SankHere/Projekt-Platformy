package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "kierunki")
@Getter @Setter
public class Kierunki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 30)
    @InvalidValues(ignoreCase = true, values = {"Informatyka","Matematyka", "Chemia"})
    private String name;

    @Positive
    private int liczbaMiejsc;

    @Column(name="created_date", nullable = false)
    private Date createdDate;

    public Kierunki(long id, String name, int liczbaMiejsc, Date createdDate) {
        this(name, liczbaMiejsc, createdDate);
        this.id = id;
    }

    public Kierunki(String name, int liczbaMiejsc, Date createdDate) {
        this.name = name;
        this.liczbaMiejsc = liczbaMiejsc;
        this.createdDate = createdDate;

    }
}