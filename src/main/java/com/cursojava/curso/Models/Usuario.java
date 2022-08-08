package com.cursojava.curso.Models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
@ToString @EqualsAndHashCode
public class Usuario {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Id
    private Long id;

    @Column(name = "nombre")
    @Getter @Setter
    private String nombre;

    @Column(name = "apellido")
    @Getter @Setter
    private String apellido;

    @Column(name = "email")
    @Getter @Setter
    private String email;

    @Column(name = "telefono")
    @Getter @Setter
    private String telefono;

    @Column(name = "password")
    @Getter @Setter
    private String password;



}
