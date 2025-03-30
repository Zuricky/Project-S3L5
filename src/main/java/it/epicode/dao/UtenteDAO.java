package it.epicode.dao;

import it.epicode.model.Utente;
import it.epicode.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class UtenteDAO {

    public void aggiungiUtente(Utente utente) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(utente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Utente ricercaPerNumeroTessera(String numeroTessera) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT u FROM Utente u WHERE u.numeroTessera = :numeroTessera", Utente.class)
                    .setParameter("numeroTessera", numeroTessera)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
