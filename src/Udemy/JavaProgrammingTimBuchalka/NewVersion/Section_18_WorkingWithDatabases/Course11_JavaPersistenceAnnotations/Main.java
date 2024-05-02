package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        String persistenceUnitName = "Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
/*
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
                     EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(new Artist("Muddy Water"));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();

            Artist artist = entityManager.find(Artist.class, 203);
            System.out.println(artist);
            //entityManager.persist(new Artist("Muddy Water"));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();

            Artist artist = entityManager.find(Artist.class, 203);
            System.out.println(artist);
            entityManager.remove(artist);
            //entityManager.persist(new Artist("Muddy Water"));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();

            //Artist artist = entityManager.find(Artist.class, 203);
            Artist artist = entityManager.find(Artist.class, 202);
            System.out.println(artist);
            artist.setArtistName("Muddy Waters");
            //entityManager.remove(artist);
            //entityManager.persist(new Artist("Muddy Water"));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();

            Artist artist = new Artist(202, "Muddy Water");
            System.out.println(artist);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();

            Artist artist = new Artist(202, "Muddy Water");
            System.out.println(artist);
            entityManager.merge(artist);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/


        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();

            Artist artist = entityManager.find(Artist.class, 202);
            //Artist artist = new Artist(202, "Muddy Water");
            artist.setArtistName("Muddy Waters");
            System.out.println(artist);
            //entityManager.merge(artist);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
