package it.epicode.model;

import it.epicode.util.JPAUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @Getter
    @ManyToOne
    @JoinColumn(name = "elemento_catalogo_id", nullable = false)
    private ElementoCatalogo elementoCatalogo;

    @Getter
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inizio_prestito", nullable = false)
    private Date dataInizioPrestito;

    @Getter
    @Temporal(TemporalType.DATE)
    @Column(name = "data_restituzione_prevista", nullable = false)
    private Date dataRestituzionePrevista;

    @Setter
    @Getter
    @Temporal(TemporalType.DATE)
    @Column(name = "data_restituzione_effettiva")
    private Date dataRestituzioneEffettiva;

    public Prestito() {}

    public Prestito(Utente utente, ElementoCatalogo elementoCatalogo, Date dataInizioPrestito, Date dataRestituzionePrevista, Date dataRestituzioneEffettiva) {
        this.utente = utente;
        this.elementoCatalogo = elementoCatalogo;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = calcolaDataRestituzionePrevista(dataInizioPrestito);
}

    public Prestito(Utente utente, Libro libro, LocalDate now, LocalDate localDate) {
    }

    private Date calcolaDataRestituzionePrevista(Date dataInizioPrestito) {
        return new Date(dataInizioPrestito.getTime() + 7 * 24 * 60 * 60 * 1000);
    }

    public Utente setUtente(Utente utente) {
        this.utente = utente;
        return utente;
    }

    public ElementoCatalogo setElementoCatalogo(ElementoCatalogo elementoCatalogo) {
        this.elementoCatalogo = elementoCatalogo;
        return elementoCatalogo;
    }
}