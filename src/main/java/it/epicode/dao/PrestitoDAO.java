package it.epicode.dao;

import it.epicode.model.Prestito;
import it.epicode.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PrestitoDAO {

    public void registraPrestito(Prestito prestito) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(prestito);
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

    public List<Prestito> ricercaPrestitiAttiviPerTessera(String numeroTessera) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL", Prestito.class);
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL", Prestito.class);
        query.setParameter("oggi", new java.util.Date());
        return query.getResultList();
    }
}