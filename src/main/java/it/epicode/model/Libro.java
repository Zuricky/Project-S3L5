package it.epicode.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "libri")
@Getter
@Setter
public class Libro extends ElementoCatalogo {

    @Column(name = "autore", nullable = false)
    private String autore;

    @Column(name = "genere", nullable = false)
    private String genere;

    public Libro() {}

    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro(String titolo, int anno, int i, String autore, String isbn) {
    }
}