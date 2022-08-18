package com.sid.secservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // this is to prevent (bloquer) the password from being sent to the client
    private String password;
    private boolean actived;
    //@ManyToMany(fetch = FetchType.LAZY) // lazy loading : load only when needed
    @ManyToMany(fetch = FetchType.EAGER) // eager loading : load all at once
    private Collection<Role> roles = new ArrayList<>(); // new ArrayList<>() pour l'inistialisation de la collection par défaut (vide)
}
