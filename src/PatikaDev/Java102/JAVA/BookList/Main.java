package PatikaDev.Java102.JAVA.BookList;


import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 The Book class consists of book name, number of pages, author's name, and publication date variables.

 Create 10 objects from the Book class and store them in an ArrayList structure.
 Using the stream structure and lambda expressions,
 write it to create a new Map<String, String> with the author name opposite the book name.

 Please develop the option to filter out books with more than 100 pages from this 10-item Book list
 and create a new list. (You can use Stream and Lambda expressions.)
**/
public class Main{
    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<>();
        Book book1 = new Book("Brave New World", 311,
                "Aldous Huxley", 1932);
        bookList.add(book1);
        Book book2 = new Book("1984", 328,
                "George Orwell", 1949);
        bookList.add(book2);
        Book book3 = new Book("The Great Gatsby", 180,
                "F. Scott Fitzgerald,", 1925);
        bookList.add(book3);
        Book book4 = new Book("Pride and Prejudice", 279,
                "Jane Austen", 1813);
        bookList.add(book4);
        Book book5 = new Book("The Catcher in the Rye", 277,
                "J.D. Salinger", 1951);
        bookList.add(book5);
        Book book6 = new Book("To Kill a Mockingbird", 336,
                "J.K. Rowling", 1997);
        bookList.add(book6);
        Book book7 = new Book("The Hobbit", 310,
                "J.R.R. Tolkien", 1937);
        bookList.add(book7);
        Book book8 = new Book("The Da Vinci Code", 454,
                "Dan Brown", 2003);
        bookList.add(book8);
        Book book9 = new Book("Harry Potter and the Philosopher's Stone", 309,
                "J.K. Rowling", 1997);
        bookList.add(book9);
        Book book10 = new Book("Animal Farm", 112,
                "George Orwell", 1945);
        bookList.add(book10);

        // Writing an arraylist as a map by using stream and lambda expression
        Map<String, String> bookMap = bookList.stream()
                .collect(Collectors.toMap(Book::getBookName, Book::getAuthorName));

        // Printing the map with respect to book name vs author's name
        bookMap.forEach((key, val) -> System.out.println(key + " - by - " + val));

        System.out.println();

        // Writing second map for the books have more pages than 400
        Map<String, Integer> bookMap2 = bookList.stream()
                .filter(book -> book.getNumberOfPgs() > 400)
                .collect(Collectors.toMap(Book::getBookName, Book::getNumberOfPgs));

        // Printing the new map
        bookMap2.forEach((key, val) -> System.out.println(key + " - pages - " + val));
    }
}
