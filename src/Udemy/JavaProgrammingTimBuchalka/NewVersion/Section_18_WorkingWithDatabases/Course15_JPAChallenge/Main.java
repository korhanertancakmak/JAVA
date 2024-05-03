package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course15_JPAChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        String persistenceUnitName = "Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";


        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();

            //Artist artist = entityManager.find(Artist.class, 202);
            Artist artist = entityManager.find(Artist.class, 103);
            System.out.println(artist);
            //artist.addAlbum("The Best of Muddy Waters");
            //System.out.println(artist);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
