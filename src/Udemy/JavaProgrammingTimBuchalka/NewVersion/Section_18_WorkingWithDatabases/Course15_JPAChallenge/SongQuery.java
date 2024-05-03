package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course15_JPAChallenge;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SongQuery {

    public static void main(String[] args) {

        String persistenceUnitName = "Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";


        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager em = emf.createEntityManager();
        ) {

            String dashedString = "-".repeat(19);
            String word = "Storm";
            var matches = getMatchedSongs(em, "%" + word + "%");
            System.out.printf("%-30s %-65s %s%n", "Artist", "Album", "Song Title");
            System.out.printf("%1$-30s %1$-65s %1$s%n", dashedString);

            matches.forEach(a -> {
                String artistName = a.getArtistName();
                a.getAlbums().forEach(b -> {
                    String albumName = b.getAlbumName();
                    b.getPlayList().forEach(s -> {
                        String songTitle = s.getSongTitle();
                        if (songTitle.contains(word)) {
                            System.out.printf("%-30s %-65s %s%n", artistName, albumName, songTitle);
                        }
                    });
                });
            });

        } catch(Exception e) {

        }
    }

    private static List<Artist> getMatchedSongs(EntityManager em, String matchedValue) {

        //String jpql = "SELECT a FROM Artist a JOIN albums album WHERE album.albumName LIKE ?1 OR album.albumName LIKE ?2";
        String jpql = "SELECT a FROM Artist a JOIN albums album join playList p WHERE p.songTitle LIKE ?1";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
        //query.setParameter(2, "%Best of%");

        return query.getResultList();
    }
}
