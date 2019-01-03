package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
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
    private Long id;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;




    public User(String username){
        this(username, false);
    }

    public User(String username, boolean enabled){
        this.username = username;
        this.enabled = enabled;
    }

}