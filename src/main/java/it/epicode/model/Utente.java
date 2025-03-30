package it.epicode.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "utenti")
public class Utente {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Setter
    @Getter
    @Column(name = "nome", nullable = false)
    private String nome;

    @Setter
    @Getter
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Setter
    @Getter
    @Column(name = "data_nascita", nullable = false)
    private Date dataNascita;

    @Setter
    @Getter
    @Column(name = "numero_tessera", nullable = false)
    private String numeroTessera;

    public Utente() {}

    public Utente(String nome, String cognome, Date dataNascita, String numeroTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroTessera = numeroTessera;
    }

    public Utente(String mario, String rossi, LocalDate of, String tessera123) {
    }
}