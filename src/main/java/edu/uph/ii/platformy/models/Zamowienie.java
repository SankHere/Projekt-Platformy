package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;


@Entity
@Table(name = "zamowienie")
@Getter @Setter
@NoArgsConstructor
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double cenaZamowienia;

    private Date dataZlozeniaZamowienia;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;


    public Zamowienie(long id, double cenaZamowienia, User user, Status status, Date dataZlozeniaZamowienia) {
        this(cenaZamowienia, user, status, dataZlozeniaZamowienia);
        this.id = id;
    }

    public Zamowienie(double cenaZamowienia, User user, Status status, Date dataZlozeniaZamowienia) {
        this.cenaZamowienia=cenaZamowienia;
        this.user = user;
        this.status = status;
        this.dataZlozeniaZamowienia = dataZlozeniaZamowienia;
    }
}
