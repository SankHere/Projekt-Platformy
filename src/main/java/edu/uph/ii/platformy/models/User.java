package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.UniqueUsername;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2, max = 36)
    @UniqueUsername
    private String username;
    private String password;
    @Transient//pole nie będzie odwzorowane w db
    private String passwordConfirm;
    private boolean enabled = false;

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

    @NotBlank
    @Length(min = 2, max = 30)
    private String name;

    @NotBlank
    @Length(min = 2, max = 30)
    private String surname;


    @NotBlank
    @Length(min = 2, max = 30)
    private String email;

    @Column(name="data_rejestracji", nullable = false)
    private Date dataRejestracji;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String username, String name, String surname, String email, Date dataRejestracji){
        this(username, false, name, surname, email, dataRejestracji);
    }

    public User(String username, boolean enabled, String name, String surname, String email, Date dataRejestracji){
        this.username = username;
        this.enabled = enabled;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dataRejestracji = dataRejestracji;
    }

}