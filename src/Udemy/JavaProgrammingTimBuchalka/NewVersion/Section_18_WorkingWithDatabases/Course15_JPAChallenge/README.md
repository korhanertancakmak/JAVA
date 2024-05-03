# [JPA Challenge]()
<div align="justify">

I've got the JPA Project open, and the first thing I'll do is 
create a new **Song** class in JPA Challenge package.
I'll start by setting this class up as a simple plain old java Object.
These include song ID, song title and track number.

```java  
@Entity
@Table(name = "songs")
public class Song {
    
    @Id
    @Column(name="song_id")
    private int songId;

    @Column(name="song_title")
    private String songTitle;
    
    @Column(name="track_number")
    private int trackNumber;
    
    public Song() {
        
    }
    
    public String getSongTitle() {
        return songTitle;
    }
    
    public int getTrackNumber() {
        return trackNumber;
    }
    
    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songTitle='" + songTitle + '\'' +
                ", trackNumber=" + trackNumber +
                '}';
    }
}
```

I'll start with private int. 
Note that I'll make each of these privates but not final.
The JPA code will be setting the values on these classes, 
so I don't want any of these to be final. 
Here I'll create an attribute called _songId_. 
Next private string then _songTitle_. 
Finally, an int, _truckNumber_.
Next, I'll generate a constructor, a no args constructor.
Hopefully, you remembered that a no args constructor is required 
to exist for a JPA entity.
Then I'll generate getters for _songTitle_ and _trackNumber_.
I don't need one for _songId_ 
because I'm not going to be using it in my own code. 
If you included it, though, that's fine.
Finally, I'll generate a _toString_ method and output all three fields.
Now that I have a **POJO** I can turn it into an **Entity** with annotations.
Starting at the top, directly above the class declaration.
The first annotation is simply **Entity**.
Next, I use the table annotation and in parentheses 
I specify the name of the table this entity will be mapped to, so `name = "songs"`.
I'll choose `Jakarta.persistence` here.
For each field, I need at least one annotation.
First, the _songId_ column requires the `Id` annotation. 
This tells JPA this is the primary key for this table. 
After this, I'll add the `column` annotation and again in parentheses 
I specify `name=` and this time I need to put in the column name 
from the table and that's `song_id`.
In this case, I'm not adding the `GeneratedValue` annotation 
which I had on the **Album** entity's `Id` field.
Since I won't be adding _songs_ in this challenge, I don't really need it.
If you added that, that's fine, especially if you want to take this code a bit
further on your own time and add or remove songs.
But you should know if you're only reading data from tables, 
some annotations aren't required.
You can simplify your code by leaving them out if that's the case.
I'll finish the **Entity** by adding the column annotations.
First to the song title field so `@Column` and in parentheses `name="song_title"`.
Finally, I need a `@Column` for track number.
And that's the same except the column `name="song_title"`.
So that's it for the **Song** entity.
This was a bit tedious, and you can see why code generation tools are useful 
to help mitigate this boilerplate part of the work.
Moving on, I'll edit the **Albums** entity class next.
I'll add another attribute, a **List** of **Songs** 
which I'm going to call _playList_.

```java  
@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="album_id")
    private int albumId;

    @Column(name="album_name")
    private String albumName;
    
    @OneToMany
    @JoinColumn(name="album_id")
    private List<Song> playList = new ArrayList<>();

    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<Song> getPlayList() {
        return playList;
    }
    
    @Override
    public String toString() {
        
        playList.sort(Comparator.commparing(Song::getTrackNumber));
        StringBuilder sb = new StringBuilder();
        for (Song s : playList) {
            sb.append("\n\t").append(s);
        }
        sb.append("\n");
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", songs = " + sb +
                '}';
    }

    @Override
    public int compareTo(Album o) {
        ///return 0;
        return this.albumName.compareTo(o.getAlbumName());
    }
}
```

I'll insert that before the no args constructor on **Album**.
It's again private, and I'll initialize it to a new **ArrayList**.
I'll add annotations to this.
First, the relationship is `one-to-many`.
An album will have many songs, presumably.
Again, since I'll only read song data, 
I can omit any cascade instructions which was included on **Artist**.
Next the `@JoinColumn`, so here, the tables, 
albums and songs are joined by `album_id`.
I need a getter for this, 
so I'll set my cursor just above the _toString_ method and generate that.
Finally, I want to edit the _toString_ method on this class.
First I'll sort the _playList_.
There are different ways to do this, 
but the simplest I think is to call the _sort_ method and pass a _comparator_, 
derived from **Song**'s _getTrackNumber_ method. 
I'll initialize a **StringBuilder** variable. 
I'll loop through each song in the playlist. 
Each song will be on its own line then indented with a tab 
then the song data will get printed. 
I'll end the song list with a new line.
Lastly, I'll include the string builder's value in the text
that's returned from this method.
So `, songs = ` and I'll concatenate the string builder here.
This configuration will just make it easier to read the songs.
I've completed everything needed to get song data 
that's related to an album when I retrieve an artist.
To test this, I'll switch over to the **Main** class.

```java  
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
```

In the _main_ method, I want to first comment out `artist.addAlbum` there,
and the `System.out.println` that follows that.
I'll change the artist id from `202` in this code to any artist id that's not 202.
You'll remember that `202` was `Muddy Waters`, and we inserted one album 
for that artist, but the album had no related songs.
I'll just change this to `103` which is the artist id for `Madonna`.
I can run my **Main** class at this point.
This code runs:

```html  
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Hibernate: select a1_0.artist_id,a1_0.album_id,a1_0.album_name from albums a1_0 where a1_0.artist_id=?
Hibernate: select pl1_0.album_id,pl1_0.song_id,pl1_0.song_title,pl1_0.track_number from songs pl1_0 where pl1_0.album_id=?
Hibernate: select pl1_0.album_id,pl1_0.song_id,pl1_0.song_title,pl1_0.track_number from songs pl1_0 where pl1_0.album_id=?
Artist{artistId=103, artistName='Madonna', albums =[Album{albumId=268, albumName='Ray Of Light', songs =
    Song{songId=5191, songTitle='Drowned World Substitute for Love', trackNumber=1}
    Song{songId=5241, songTitle='Swim', trackNumber=2}
    Song{songId=3947, songTitle='Ray of Light', trackNumber=3}
    Song{songId=1614, songTitle='Candy Perfume Girl', trackNumber=4}
    Song{songId=2634, songTitle='Skin', trackNumber=5}
    Song{songId=889, songTitle='Nothing Really Matters', trackNumber=6}
    Song{songId=2117, songTitle='Sky Fits Heaven', trackNumber=7}
    Song{songId=406, songTitle='Shanti Ashtangi', trackNumber=8}
    Song{songId=298, songTitle='Frozen', trackNumber=9}
    Song{songId=1226, songTitle='The Power of Good-Bye', trackNumber=10}
    Song{songId=670, songTitle='To Have and Not to Hold', trackNumber=11}
    Song{songId=1897, songTitle='Little Star', trackNumber=12}
    Song{songId=1380, songTitle='Mer Girl', trackNumber=13}
}, Album{albumId=707, albumName='Ray Of Light', songs =
}]}
```

And I can see the songs on the album `Ray of Light` listed there.
I can also see the duplicate album with no songs listed as well.
I talked about this problem in my **music** database in an earlier section.
I hope you can see how easy it is to add an entity in your hierarchy
and get data using these JPA entities.
So now, the final part of the challenge was to create a new class 
that's similar to the **MainQuery** class from the last section.
I'll create a new class, and call it **SongQuery**.
I'll create a _main_ method.

```java  
public class SongQuery {

    public static void main(String[] args) {

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
```

Before I start adding code to that, I'll quickly go to the **MainQuery** class 
and copy the _getArtistsJPQL_ method.
I'll paste this method into my **SongQuery** class below the _main_ method.
First, I'll change the name of this method to _getMatchedSongs_.
After that, I'll remove the `OR` condition in the `WHERE` clause.
So now I'll set up another `join` in this statement.
After the album alias I'll type join playList.
Remember, we join to the collection field and not the table, 
so that's the playlist.
After that I'll give that an alias, the letter `p`.
Aliases are required because the JPA provider uses them as variable names.
Finally, in the `WHERE` clause I'll match to `p.songTitle`.
I need to remove the second call to setParameter, which we don't need anymore.
That's all I need for this method.
It searches for artists whose albums have songs 
that contain the matched value passed to this method.
Next I'll set up the _main_ method.

```java  
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
```

I'll start with a _try-with-resources_ block.
Inside the declaration I'll get an **EntityManager** factory from the **Persistence** type,
which you've seen me do a few times now.
I need to use my persistence unit. 
If you forget what that value was, 
you can find it in the `persistence.xml` file. 
Next, I get the entity manager from the entity manager factory. 
I'll come back to complete the _try_ block in just a second.
I just want to add the usual _catch_ clause here.
I'll print this data out in tabular form, so I'll do a little setup here.
I want dashes to be printed under my headers, 
so I'll just set up a dashed String. 
After this, I'll set up a variable for the word I want to match on.
I'll set that to `Storm` to start with. 
My results will go in a variable called matches. 
I'll call _getMatchedSongs_, passing that my entity manager instance. 
My second parameter needs to be a string that has a pattern, 
because my query uses the `like` clause, 
so I'll surround my word variable with `%` signs there. 
Next, I'll print my header columns out, 
formatted each lined up in left justified columns. 
I'll print my dashes under those columns using the same placement.
My query data gets returned and assigned to the matches variable.
This data returns the matching artist with all of their albums and songs in the data,
not just the matched album or song.
In other words, it returns artists who have an album, 
that contains a song that contains the text `Storm`.
Each artist still has their full set of data retrieved.
This is important to know when processing the data.
I'll do this with a series of _forEach_ loops.
First, I loop through the list of artists I got back. 
I'll set a local variable to the _artistName_. 
I'll loop through each of the albums that matched. 
Here I'll set a variable for the _albumName_ this time. 
Now I'll loop through each album's playlist. 
I'll set another variable for the _songTitle_. 
So here I need to check if the song contains the _word_ I used in the query.
Remember the match was to the artist 
but all data for that artist still gets returned. 
Because of this, I have to again test the song title, 
this time I can use the contains method to do it. 
I'll print the _artistName_, _albumName_ and _songTitle_, 
using _printf_ and the variables I set up.
Before I run this I'm going to edit the `persistence.xml` file.

~~~~xml  
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">

    <persistence-unit name="Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music">

        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
            <property name="jakarta.persistence.jdbc.url"    value="jdbc:mysql://localhost:3335/music" />
            <property name="jakarta.persistence.jdbc.user"   value="devUser" />
            <property name="jakarta.persistence.jdbc.password" value="KsKorteaGs1905." />
            <property name="hibernate.show_sql"     value="true" />
        </properties>

    </persistence-unit>
</persistence>

~~~~

I'll remove the last property there,
`<property name="hibernate.show_sql"     value="true" />`
which I had added in a previous section.
Doing this will make the output a bit easier to read.
I'll run this:

```html  
Artist                         Album                                                             Song Title
-------------------            -------------------                                               -------------------
Mumford & Sons                 Sigh No More                                                      After the Storm
Ted Nugent                     Ted Nugent                                                        Stormtroopin' (Live At Hammersmith Odeon London 1977)
Ted Nugent                     Ted Nugent                                                        Stormtroopin'
Queen                          Sheer Heart Attack                                                She Makes Me (Stormtrooper In Stilettos)
Horslips                       Aliens                                                            Before The Storm
Doors                          Best Of The Doors                                                 Riders On The Storm
Deep Purple                    Made In Europe                                                    Stormbringer
Deep Purple                    Stormbringer                                                      Stormbringer
Blue Öyster Cult               Mirrors                                                           I Am The Storm
Jakatta                        Visions                                                           Ride The Storm
DragonForce                    Sonic Firestorm                                                   Fury Of The Storm
```

Here, in tabular form are all songs that have `Storm` in the name, 
with the artist name, album name and song title printed.
My formatting looks a little weird 
because I left a lot of space for the _Album_ column,
but if you re-run this code for the word `Soul` you'll see why.
If you took the bonus challenge and wrote a **CriteriaBuilder** query,
I'll be demonstrating this next.
This challenge was a little bit tricky 
because I never covered `joins` in this code with you.
Part of the challenge was to have you research this 
and implement it on your own.
If you want to see how my solution compares to yours, 
or you're just curious how to include `joins` in a **CriteriaBuilder** query, 
then meet me in the next section.
</div>