package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course14_CriteriaBuilder;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.TreeMap;

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

            artists = getArtistsJPQL(em, "%Greatest Hits%");
            artists.forEach(System.out::println);

            System.out.println("------------------------------------");
            Stream<Artist> sartists = getArtistsBuilder(em, "");
            var map = sartists
                    .limit(10)
                    .collect(Collectors.toMap(
                            Artist::getArtistName,
                            (a) -> a.getAlbums().size(),
                            Integer::sum,
                            TreeMap::new
                    ));
            map.forEach((k, v) -> System.out.println(k + " : " + v));

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

            artists = getArtistsJPQL(em, "%Greatest Hits%");
            artists.forEach(System.out::println);

            System.out.println("------------------------------------");
            //Stream<Artist> sartists = getArtistsBuilder(em, "");
            Stream<Artist> sartists = getArtistsBuilder(em, "Bl%");
            var map = sartists
                    .limit(10)
                    .collect(Collectors.toMap(
                            Artist::getArtistName,
                            (a) -> a.getAlbums().size(),
                            Integer::sum,
                            TreeMap::new
                    ));
            map.forEach((k, v) -> System.out.println(k + " : " + v));

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Artist> getArtistsJPQL(EntityManager em, String matchedValue) {

        String jpql = "SELECT a FROM Artist a JOIN albums album WHERE album.albumName LIKE ?1" +
                "OR album.albumName LIKE ?2";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
        query.setParameter(2, "%Best of%");

        return query.getResultList();
    }

    private static Stream<Tuple> getArtistNames(EntityManager em, String matchedValue) {

        String jpql = "SELECT a.artistId, a.artistName FROM Artist a WHERE a.artistName LIKE ?1";
        var query = em.createQuery(jpql, Tuple.class);
        query.setParameter(1, matchedValue);
        return query.getResultStream();
    }

    private static Stream<Artist> getArtistsBuilder(EntityManager em, String matchedValue) {

/*
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = builder.createQuery (Artist.class);
        Root<Artist> root = criteriaQuery.from(Artist.class);
        criteriaQuery.select(root);
*/

/*
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = builder.createQuery (Artist.class);
        Root<Artist> root = criteriaQuery.from(Artist.class);
        criteriaQuery.select(root);
        criteriaQuery.orderBy(builder.asc(root.get("artistName")));
*/

/*
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = builder.createQuery (Artist.class);
        Root<Artist> root = criteriaQuery.from(Artist.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.like(root.get("artistName"), matchedValue));
        criteriaQuery.orderBy(builder.asc(root.get("artistName")));
*/

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = builder.createQuery (Artist.class);
        Root<Artist> root = criteriaQuery.from(Artist.class);
        criteriaQuery
                .select(root)
                .where(builder.like(root.get("artistName"), matchedValue))
                .orderBy(builder.asc(root.get("artistName")));

        return em.createQuery(criteriaQuery).getResultStream();
    }
}
