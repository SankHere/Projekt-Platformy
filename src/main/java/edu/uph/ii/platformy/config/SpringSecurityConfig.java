package edu.uph.ii.platformy.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("user1").password(encoder.encode("user")).roles("USER");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("admin1").password(encoder.encode("admin")).roles("ADMIN");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("useradmin1").password(encoder.encode("useradmin")).roles("USER","ADMIN");
    }*/


    @Bean
    @Profile(ProfileNames.INMEMORY)
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        User.UserBuilder userBuilder = User.builder();

        UserDetails user = userBuilder
                .username("user1")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        UserDetails admin = userBuilder
                .username("admin1")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails test = userBuilder
                .username("useradmin1")
                .password(passwordEncoder.encode("useradmin"))
                .roles("USER", "ADMIN")
                .build();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);
        manager.createUser(admin);
        manager.createUser(test);

        return manager;
    }

    @Bean
    @Profile(ProfileNames.DATABASE)
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AccessDeniedHandler createAccessDeniedHandler(){
        AccessDeniedHandlerImpl impl = new AccessDeniedHandlerImpl();
        impl.setErrorPage("/error403");//url
        return impl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/statics/**", "/webjars/**", "/**","/mojezamowie.html", "/produktList.html", "/registrationForm.html", "/error.html").permitAll()
                .antMatchers( "/produktListForm.html").hasRole("ADMIN")
                .anyRequest().authenticated();//każde żądanie ma być uwierzytelnione
        http
                .formLogin()//pozwól użytkownikom uwierzytelniać się poprzez formularz
                .loginPage("/login")//formularz logowania dostępny jest pod URL
                .permitAll();//przyznaj dostęp wszystkim użytkownikom dla wszystkich URL powiązanych z logowaniem opartym na formularzu.
        http
                .logout()//pozwól wszystkim użykownikom się wylogować
                .permitAll();//

        http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());
    }
}


