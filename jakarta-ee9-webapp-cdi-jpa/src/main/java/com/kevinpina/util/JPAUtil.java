package com.kevinpina.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("persistanceApp");
        // Defined in ./jakarta-ee9-webapp-cdi-jpa/src/main/webapp/META-INF/context.xml:
        // <persistence-unit name="persistanceApp" ... />
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
