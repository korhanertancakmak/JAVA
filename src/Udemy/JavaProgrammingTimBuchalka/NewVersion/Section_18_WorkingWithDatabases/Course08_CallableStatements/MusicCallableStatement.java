package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course08_CallableStatements;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Map;
import java.util.stream.Collectors;

public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
/*
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN],
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN],
                                    Collectors.mapping(s -> s[SONG_COLUMN],
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });
*/

/*
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN],
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN],
                                    Collectors.mapping(s -> s[SONG_COLUMN],
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
        ) {
            CallableStatement cs = connection.prepareCall("CALL music.addAlbum(?,?,?)");

            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        cs.execute();

                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

/*
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN],
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN],
                                    Collectors.mapping(s -> s[SONG_COLUMN],
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
        ) {
            //CallableStatement cs = connection.prepareCall("CALL music.addAlbum(?,?,?)");
            CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?,?)");

            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        cs.execute();

                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

/*
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN],
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN],
                                    Collectors.mapping(s -> s[SONG_COLUMN],
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
        ) {
            CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?,?)");

            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);

                        cs.registerOutParameter(4, Types.INTEGER);
                        cs.execute();
                        System.out.printf("%d songs were added for %s%n", cs.getInt(4), album);

                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/


        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN],
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN],
                                    Collectors.mapping(s -> s[SONG_COLUMN],
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
        ) {
            //CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?,?)");
            CallableStatement cs = connection.prepareCall("CALL music.addAlbumInOutCounts(?,?,?,?)");

            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);

                        cs.setInt(4, 10);
                        cs.registerOutParameter(4, Types.INTEGER);
                        cs.execute();
                        System.out.printf("%d songs were added for %s%n", cs.getInt(4), album);

                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
