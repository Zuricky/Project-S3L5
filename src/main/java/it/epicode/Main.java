package it.epicode;

import it.epicode.dao.ElementoCatalogoDAO;
import it.epicode.dao.PrestitoDAO;
import it.epicode.dao.UtenteDAO;
import it.epicode.model.ElementoCatalogo;
import it.epicode.model.Libro;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UtenteDAO utenteDAO = new UtenteDAO();
        PrestitoDAO prestitoDAO = new PrestitoDAO();
        ElementoCatalogoDAO catalogoDAO = new ElementoCatalogoDAO();

        ElementoCatalogo libro1 = new Libro();
        libro1.setIsbn("978-1234567890");
        libro1.setTitolo("Il Signore degli Anelli");
        libro1.setAnnoPubblicazione(1954);
        ((Libro) libro1).setAutore("J.R.R. Tolkien");

        ElementoCatalogo libro2 = new Libro();
        libro2.setIsbn("978-9876543210");
        libro2.setTitolo("1984");
        libro2.setAnnoPubblicazione(1949);
        ((Libro) libro2).setAutore("George Orwell");

        ElementoCatalogo libro3 = new Libro();
        libro3.setIsbn("978-1111111111");
        libro3.setTitolo("Il Piccolo Principe");
        libro3.setAnnoPubblicazione(1943);
        ((Libro) libro3).setAutore("Antoine de Saint-Exupéry");

        ElementoCatalogoDAO elementoDAO = new ElementoCatalogoDAO();
        elementoDAO.aggiungiElemento(libro1);
        elementoDAO.aggiungiElemento(libro2);
        elementoDAO.aggiungiElemento(libro3);

        while (true) {
            System.out.println("\n--- MENU ARCHIVIO BIBLIOTECA ---");
            System.out.println("1. Aggiunta di un elemento del catalogo");
            System.out.println("2. Rimozione di un elemento dato un codice ISBN");
            System.out.println("3. Ricerca per ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Ricerca per titolo");
            System.out.println("7. Ricerca degli elementi in prestito per numero tessera utente");
            System.out.println("8. Ricerca prestiti scaduti e non restituiti");
            System.out.println("0. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci titolo: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Inserisci anno pubblicazione: ");
                    int anno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci autore: ");
                    String autore = scanner.nextLine();
                    System.out.print("Inserisci ISBN: ");
                    String isbn = scanner.nextLine();
                    catalogoDAO.aggiungiElemento(new Libro(titolo, anno, 100, autore, isbn));
                    System.out.println("Elemento aggiunto con successo.");
                    break;

                case 2:
                    System.out.print("Inserisci ISBN dell'elemento da rimuovere: ");
                    String isbnRimuovi = scanner.nextLine();
                    catalogoDAO.rimuoviElemento(isbnRimuovi);
                    System.out.println("Elemento rimosso.");
                    break;

                case 3:
                    System.out.print("Inserisci ISBN per la ricerca: ");
                    String isbnRicerca = scanner.nextLine();
                    System.out.println(catalogoDAO.ricercaPerISBN(isbnRicerca));
                    break;

                case 4:
                    System.out.print("Inserisci anno di pubblicazione: ");
                    int annoRicerca = scanner.nextInt();
                    System.out.println(catalogoDAO.ricercaPerAnno(annoRicerca));
                    break;

                case 5:
                    System.out.print("Inserisci autore: ");
                    String autoreRicerca = scanner.nextLine();
                    System.out.println(catalogoDAO.ricercaPerAutore(autoreRicerca));
                    break;

                case 6:
                    System.out.print("Inserisci titolo o parte del titolo: ");
                    String titoloRicerca = scanner.nextLine();
                    System.out.println(catalogoDAO.ricercaPerTitolo(titoloRicerca));
                    break;

                case 7:
                    System.out.print("Inserisci numero di tessera utente: ");
                    String tessera = scanner.nextLine();
                    System.out.println(prestitoDAO.ricercaPrestitiAttiviPerTessera(tessera));
                    break;

                case 8:
                    System.out.println(prestitoDAO.ricercaPrestitiScadutiNonRestituiti());
                    break;

                case 0:
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        }
    }
}

