package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;


@Entity
@Table(name = "zamowienie")
@Getter @Setter
@NoArgsConstructor
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;


    public Zamowienie(long id, User user, Status status) {
        this(user, status);
        this.id = id;
    }

    public Zamowienie(User user, Status status) {
        this.user = user;
        this.status = status;
    }
}
