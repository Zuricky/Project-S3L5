package it.epicode.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "riviste")
public class Rivista extends ElementoCatalogo {

    public enum Periodicita {
        SETTIMANALE,
        MENSILE,
        ANNUALE
    }

    @Getter
    @Enumerated
    @Column(name = "periodicita", nullable = false)
    private Periodicita periodicita;

    public Rivista() {}

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}