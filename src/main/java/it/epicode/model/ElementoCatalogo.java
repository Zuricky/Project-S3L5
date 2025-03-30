package it.epicode.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "elementi_catalogo")
@Getter
@Setter
public class ElementoCatalogo implements Serializable {

    @Id
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @Column(name = "anno_pubblicazione", nullable = false)
    private int annoPubblicazione;

    @Column(name = "numero_pagine", nullable = false)
    private int numeroPagine;

    public ElementoCatalogo() {}

    public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }
}