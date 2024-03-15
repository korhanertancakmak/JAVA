package JAVA.BookSorter;
import java.util.*;
class Book implements Comparable<Book> {

    private final String bookName;
    private final Integer numsOfPgs;
    private final String authorsName;
    private final Integer publicationYear;

    public Book(String bookName, int numsOfPgs, String authorsName, int publicationYear) {
        this.bookName = bookName;
        this.numsOfPgs = numsOfPgs;
        this.authorsName = authorsName;
        this.publicationYear = publicationYear;
    }

    @Override
    public int compareTo(Book otherBook) {
        return Comparator.comparing(Book::getBookName)
                .thenComparing(Book::getNumsOfPgs)
                .thenComparing(Book::getAuthorsName)
                .compare(this, otherBook);
    }

    public String getBookName() {
        return bookName;
    }

    public int getNumsOfPgs() {
        return numsOfPgs;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}

