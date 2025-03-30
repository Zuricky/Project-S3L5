package it.epicode.util;

import jakarta.persistence.*;

public class JPAUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("CatalogoPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}