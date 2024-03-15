package PatikaDev.Java102.JAVA.BookList;

public class Book {

    private final String bookName;
    private final int numberOfPgs;
    private final String authorName;
    private final int publicationDate;

    public Book(String bookName, int numberOfPgs, String authorName, int publicationDate) {
        this.bookName = bookName;
        this.numberOfPgs = numberOfPgs;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
    }

    public String getBookName() {
        return bookName;
    }

    public int getNumberOfPgs() {
        return numberOfPgs;
    }

    public String getAuthorName() {
        return authorName;
    }
}
