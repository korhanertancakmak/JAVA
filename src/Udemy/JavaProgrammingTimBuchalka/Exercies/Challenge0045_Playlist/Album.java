package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0045_Playlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Album {
/*
    Album:
        -  It has three fields, two Strings called name and artist, and an ArrayList that holds objects of type Song called
        songs.
        -  A constructor that accepts two Strings (name of the album and artist). It initialises the fields and instantiates
        songs.
*/

    private String name, artist;

    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
/*
        addSong(), has two parameters of type String (title of song), double (duration of song) and returns a boolean.
        Returns true if the song was added successfully or false otherwise.
*/
        if (findSong(title) != null) {
            return false;
        }
        songs.add(new Song(title, duration));
        return true;
    }

    private Song findSong(String title) {
/*
        findSong(), has one parameter of type String (title of song) and returns a Song. Returns the Song if it
        exists, null if it doesn't exist.
*/
        for (Song i : songs) {
            if (i.getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
/*
        addToPlayList(), has two parameters of type int (track number of song in album) and LinkedList (the playlist)
        that holds objects of type Song, and returns a boolean. Returns true if it exists, and it was added successfully
        using the track number, or false otherwise.
*/
        if (trackNumber == 0) {

            // get the song from the album
            Song songToAdd = songs.get(0);
            String songToAddTitle = songToAdd.getTitle();

            // See if the song has already been added to the playList
            ListIterator<Song> playListIterator = playList.listIterator();
            while (playListIterator.hasNext()) {
                if  (playListIterator.next().getTitle().compareTo(songToAddTitle) == 0) {
                    // The song is already in the playlist
                    return false;
                }
            }

            playList.add(songs.get(0));
            return true;
        } else if (trackNumber > 0 && trackNumber <= songs.size()) {

            // get the song from the album
            Song songToAdd = songs.get(trackNumber-1);
            String songToAddTitle = songToAdd.getTitle();

            // See if the song has already been added to the playList
            ListIterator<Song> playListIterator = playList.listIterator();
            while (playListIterator.hasNext()) {
                if  (playListIterator.next().getTitle().compareTo(songToAddTitle) == 0) {
                    // The song is already in the playlist
                    return false;
                }
            }

            playList.add(songs.get(trackNumber - 1));
            return true;
        } else {
            return false;
        }
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
/*
        addToPlayList(), has two parameters of type String (title of song) and LinkedList (the playlist) that holds
        objects of type Song, and returns a boolean. Returns true if it exists and it was added successfully using the
        name of the song, or false otherwise.
*/
        if (findSong(title) != null) {
            playList.add(findSong(title));
            return true;
        }
        return false;
    }
}

