package it.epicode.dao;

import it.epicode.model.ElementoCatalogo;
import it.epicode.model.Prestito;
import it.epicode.util.JPAUtil;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class ElementoCatalogoDAO {

    public void aggiungiElemento(ElementoCatalogo elemento) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(elemento);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void rimuoviElemento(String isbn) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
            if (elemento != null) {
                em.remove(elemento);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public ElementoCatalogo ricercaPerISBN(String isbn) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();

        try {
            return em.find(ElementoCatalogo.class, isbn);
        } finally {
            em.close();
        }
    }

    public List<ElementoCatalogo> ricercaPerAnno (int anno) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();

        try {
            TypedQuery<ElementoCatalogo> query = em.createQuery(
                    "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
            query.setParameter("anno", anno);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public  List<ElementoCatalogo>  ricercaPerTitolo(String titolo) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();

        try {
            TypedQuery<ElementoCatalogo> query = em.createQuery(
                    "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE LOWER(:titolo)", ElementoCatalogo.class);
            query.setParameter("titolo", titolo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<ElementoCatalogo> ricercaPerAutore(String autore) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<ElementoCatalogo> query = em.createQuery(
                    "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.autore) LIKE LOWER(:autore)",
                    ElementoCatalogo.class
            );
            query.setParameter("autore", "%" + autore + "%"); // Usa il wildcard "%" per cercare parzialmente
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}