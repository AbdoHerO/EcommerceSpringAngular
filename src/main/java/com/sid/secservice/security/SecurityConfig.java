package com.sid.secservice.security;

import com.sid.secservice.filters.JWTAuthenticationFilter;
import com.sid.secservice.filters.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.sid.secservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  // AuthenticationManagerBuilder is a class that is used to build the authentication manager

        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // loadUserByUsername is a method that is used to load the user details
                com.sid.secservice.entities.User user = accountService.loadUserByUsername(username); // get the user from the database
                Collection<GrantedAuthority> authorities = new ArrayList<>(); // create a collection of authorities
                user.getRoles().forEach(role -> { // for each role in the user
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName())); // add the role to the collection of authorities
                });
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities); // return the user details
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.authorizeRequests().anyRequest().permitAll(); // allow all requests
//        http.formLogin(); // enable form login

        http.csrf().disable(); // disable csrf for this service
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // disable session for this service
        http.headers().frameOptions().disable(); // disable frame options for this service
        http.authorizeRequests().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").permitAll(); // allow all requests to the account service


        // * Give access to the requests service to the following endpoints (1 - First solution)
        // * (2 - Second solution) @PostAuthorize("hasAuthority('ADMIN')") in Controller (ex : UserController)
        // * And @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) in racingConfig class ( run class )
        // ** Il faut écrire ce code avant "http.authorizeRequests().anyRequest().authenticated();" (la priorité)
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("ADMIN"); // allow all requests to the account service
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("USER"); // allow all requests to the account service


        http.authorizeRequests().anyRequest().authenticated(); // allow all requests by authenticated users
        http.addFilter(new JWTAuthenticationFilter(authenticationManagerBean())); // add the JWT authentication filter
        http.addFilterBefore(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class); // add the JWT authorization filter




    }

    @Bean // this bean is used to build the authentication manager
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
