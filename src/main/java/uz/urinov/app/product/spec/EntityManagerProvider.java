package uz.urinov.app.product.spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityManagerProvider {

    private static EntityManagerFactory entityManagerFactory;


    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public static EntityManagerProvider setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        EntityManagerProvider.entityManagerFactory = entityManagerFactory;
        return new EntityManagerProvider();
    }

}
