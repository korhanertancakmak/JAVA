package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course13_JPQL;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;

import java.util.List;
import java.util.stream.Stream;

public class MainQuery {

    public static void main(String[] args) {

        String persistenceUnitName = "Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";

        List<Artist> artists = null;

/*
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager em = emf.createEntityManager();
        ) {

            var transaction = em.getTransaction();
            transaction.begin();

            artists = getArtistsJPQL(em, "");
            artists.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager em = emf.createEntityManager();) {

            var transaction = em.getTransaction();
            transaction.begin();
            artists = getArtistsJPQL(em, "%Stev%");
            artists.forEach(System.out::println);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager em = emf.createEntityManager();
        ) {

            var transaction = em.getTransaction();
            transaction.begin();

            artists = getArtistsJPQL(em, "%Stev%");
            artists.forEach(System.out::println);
            var names = getArtistNames(em, "%Stev%");
            names.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager em = emf.createEntityManager();
        ) {

            var transaction = em.getTransaction();
            transaction.begin();

            artists = getArtistsJPQL(em, "%Stev%");
            artists.forEach(System.out::println);
            var names = getArtistNames(em, "%Stev%");
            //names.forEach(System.out::println);
            names.map(a -> new Artist(
                            a.get(0, Integer.class),
                            (String) a.get(1)))
                    .forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

/*
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager em = emf.createEntityManager();
        ) {

            var transaction = em.getTransaction();
            transaction.begin();

            artists = getArtistsJPQL(em, "%Stev%");
            artists.forEach(System.out::println);
            var names = getArtistNames(em, "%Stev%");
            //names.map(a -> new Artist(
                    //a.get(0, Integer.class),
                    //(String) a.get(1)))
                    //.forEach(System.out::println);

            names.map(a -> new Artist(
                            a.get("id", Integer.class),
                            (String) a.get("name")))
                    .forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/


        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager em = emf.createEntityManager();
        ) {

            var transaction = em.getTransaction();
            transaction.begin();

            //artists = getArtistsJPQL(em, "%Stev%");
            artists = getArtistsJPQL(em, "%Greatest Hits%");
            artists.forEach(System.out::println);

            //var names = getArtistNames(em, "%Stev%");
            //names.map(a -> new Artist(
            //a.get("id", Integer.class),
            //(String) a.get("name")))
            //.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Artist> getArtistsJPQL(EntityManager em, String matchedValue) {

/*
        String jpql = "SELECT a FROM Artist a";
        var query = em.createQuery(jpql, Artist.class);
*/

/*
        String jpql = "SELECT a FROM Artist a WHERE a.artistName LIKE :partialName";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter("partialName", matchedValue);
*/

/*
        String jpql = "SELECT a FROM Artist a WHERE a.artistName LIKE ?1";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
*/

/*
        //String jpql = "SELECT a FROM Artist a WHERE a.artistName LIKE ?1";
        String jpql = "SELECT a FROM Artist a JOIN albums album WHERE album.albumName LIKE ?1";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
*/


        //String jpql = "SELECT a FROM Artist a JOIN albums album WHERE album.albumName LIKE ?1";
        String jpql = "SELECT a FROM Artist a JOIN albums album WHERE album.albumName LIKE ?1" +
                "OR album.albumName LIKE ?2";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
        query.setParameter(2, "%Best of%");

        return query.getResultList();
    }

    //private static List<Artist> getArtistNames(EntityManager em, String matchedValue) {
    //private static List<String> getArtistNames(EntityManager em, String matchedValue) {
    //private static List<Tuple> getArtistNames(EntityManager em, String matchedValue) {
    private static Stream<Tuple> getArtistNames(EntityManager em, String matchedValue) {

/*
        String jpql = "SELECT a FROM Artist a WHERE a.artistName LIKE ?1";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
*/

/*
        String jpql = "SELECT a.artistName FROM Artist a WHERE a.artistName LIKE ?1";
        var query = em.createQuery(jpql, String.class);
        query.setParameter(1, matchedValue);
*/

/*
        String jpql = "SELECT a.artistId, a.artistName FROM Artist a WHERE a.artistName LIKE ?1";
        //var query = em.createQuery(jpql, String.class);
        var query = em.createQuery(jpql, Tuple.class);
        query.setParameter(1, matchedValue);
        return query.getResultList();
*/

        String jpql = "SELECT a.artistId, a.artistName FROM Artist a WHERE a.artistName LIKE ?1";
        var query = em.createQuery(jpql, Tuple.class);
        query.setParameter(1, matchedValue);
        return query.getResultStream();
    }
}
